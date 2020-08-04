/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2020B
  Assessment: Assignment 1
  Author: Nguyen Hoang Khang
  ID: s3802040
  Created  date: 29/07/2020
  Last modified:
  Acknowledgement: Acknowledge the resources that you use here.
*/

package LibraryManagment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Member {
    // Properties of Member class
    private String fullName;
    private String id;
    private String phone;
    private String mail;
    private String address;
    private LocalDate expiredDate;
    private String status;
    private double lateFee;
    private final Loan[] loans = new Loan[5];

    //Constructors of Member class
    public Member() {
    }

    public Member(String fullName, String id, String phone, String mail, String address, String date, String status) {
        this.fullName = fullName;
        this.id = id;
        this.mail = mail;
        this.address = address;
        lateFee = 0;
        setPhone(phone); // Check the validation format
        setStatus(status); // Check the validation format
        setExpiredDate(date); // Check the validation format
    }


    // This method add loan and set up status and number of copies of item.
    public void addLoans(Loan loan) {
        Scanner scanner = new Scanner(System.in);
        boolean check = false;

        // Add the loan to the array loan:
        for (int i = 0; i < 5; i++) {
            if (loans[i] == null) { // If the element is null , add the loan to the array
                loans[i] = loan;
                check = true;
                break; // If already added, stop the loop.
            }
        }

        // Set status and update the number of copies of the item
        if (check) {
            System.out.println("Successfully added loan to this member");
            scanner.nextLine(); // Spend time for user to read the message
            loan.getItem().increaseNumberOfCopiesOnLoan(); // Increase the number of item on loan
            if (loan.getItem().getNumberOfCopiesOnLoan() < loan.getItem().getNumberOfCopies()) {
                loan.getItem().setStatus("Available"); // If the number on loan is less than number of copies , available . Otherwise , on loan
            } else
                loan.getItem().setStatus("On Loan");
        } else
            System.out.println("One member can only have 5 loans at the same time\nFailure to borrow more items");
    }

    // LoadLoans in the start() method without any displayed statements.
    public void loadLoans(Loan loan) {
        boolean check = false;

        // Add the loan to the array loan:
        for (int i = 0; i < 5; i++) {
            if (loans[i] == null) { // If the element is null , add the loan to the array
                loans[i] = loan;
                check = true;
                break; // If already added, stop the loop.
            }
        }

        // Set status and update the number of copies of the item
        if (check) {
            loan.getItem().increaseNumberOfCopiesOnLoan(); // Increase the number of item on loan
            if (loan.getItem().getNumberOfCopiesOnLoan() < loan.getItem().getNumberOfCopies()) {
                loan.getItem().setStatus("Available"); // If the number on loan is less than number of copies , available . Otherwise , on loan
            } else
                loan.getItem().setStatus("On Loan");
        }
    }

    // Methods:
    // Set methods (mutators):
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhone(String phone) {
        Scanner scanner = new Scanner(System.in);
        // Check the format of phone
        if (Item.verifyInt(phone)) {
            this.phone = phone;
        } else {
            do {
                System.out.println("Enter phone number in the correct format");
                this.phone = scanner.nextLine();
            } while (!Item.verifyInt(this.phone));
        }
    }

    public void setMail(String mail) {
        String pattern = ".*@rmit\\.edu\\.vn";
        if (mail.matches(pattern)) {
            this.mail = mail;
        } else {
            do {
                Scanner scanner = new Scanner(System.in);
                System.out.println("The input format is incorrect. Please re enter the mail ...@rmit.edu.vn");
                this.mail = scanner.nextLine();
            } while (!this.mail.matches(pattern));

        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setExpiredDate(String expiredDate) {
        boolean check = false;
        String date = expiredDate;
        // Force the user to enter the information
        do {
            try {
                this.expiredDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                check = true;
            } catch (Exception e) {
                System.out.println("Failure to input the expired date. Please follow the instructed format\nThe expired date has not yet been updated");
                System.out.println("Please enter again following this format (dd-MM-yyyy): ");
                Scanner scanner = new Scanner(System.in);
                date = scanner.nextLine();
            }
        } while (!check); // The loop exits only if the format is right

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        Scanner scanner = new Scanner(System.in);
        // Check the format of status in the right form
        if (status.equalsIgnoreCase("active") || status.equalsIgnoreCase("expired")) {
            this.status = status;
        } else {
            do {
                System.out.println("Enter the status ( active or expired): ");
                this.status = scanner.nextLine();
            } while (!(this.status.equalsIgnoreCase("active")) && !(this.status.equalsIgnoreCase("expired")));

        }
    }

    public void setLateFee(double lateFee) {
        this.lateFee += lateFee;
    }

    // Return the location of the found item
    public int findLoan(Item item) {
        for (int i = 0; i < loans.length; i++) {
            if (item.equals(loans[i].getItem())) {
                return i;
            }
        }
        return -1;
    }

    public Loan[] getLoans() {
        return loans;
    }

    // This method to display the information to the console
    public String getString() {
        StringBuilder loanRecord = new StringBuilder();
        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (loans[i] != null) {
                loanRecord.append(loans[i].toString());
                count++;
            }
        }
        if (count == 0) {
            loanRecord.append("This member has not borrowed any item yet");
        }

        return fullName + "\n" + id + "\n" + phone +
                "\n" + mail + "\n" + address + "\n"
                + expiredDate.toString() + "\n" + status +
                "\nLate Fee: " + lateFee + "\n" + loanRecord.toString() + "\n";
    }

    @Override
    public String toString() {

        return fullName + id + phone + mail + address + expiredDate.toString() + status + lateFee;

    }
}
