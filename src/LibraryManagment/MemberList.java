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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberList {
    public static final File MEMBERFILE = new File("src/Member.txt");
    // Data field:
    private ArrayList<Member> members = new ArrayList<>();

    // Methods:
    // Load the data from the file
    public void start() {
        try {
            Scanner sc = new Scanner(MEMBERFILE);
            while (sc.hasNext()) {
                String name = sc.nextLine();
                String id = sc.nextLine();
                String phone = sc.nextLine();
                String mail = sc.nextLine();
                String address = sc.nextLine();
                String date = sc.nextLine();
                String status = sc.nextLine();
                members.add(new Member(name, id, phone, mail, address, date, status));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file");
        }
    }

    public void searchMember() {
        ArrayList<Member> members2 = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the keyword in member " +
                "list you want to search: ");
        String input = scanner.nextLine(); // Enter the keyword want to search
        String pattern = ".*" + input + ".*"; // Making the pattern for searching

        boolean checkKeyWordExists = false;
        for (Member member : members) {
            if (member.toString().toLowerCase().matches(pattern.toLowerCase())) {
                members2.add(member);
                checkKeyWordExists = true;
            }
        }

        if (checkKeyWordExists) {
            displayMember(members2);
        } else {
            // Call a method helping us display a list of member in the library
            displayMember(members);
        }
    }

    // Display member
    public void displayMember(ArrayList<Member> members2) {
        Scanner scanner = new Scanner(System.in);
        int size = (int) Math.ceil(members2.size() / 10.0);
        if (size > 1) {
            Member[][] arr = new Member[size][10];
            int count = 0; // get the element from arrayList member

            // Insert the arraylist member to array
            for (int i = 0; i < size; i++) {
                for (int k = 0; k < 10; k++) {
                    if (count == members2.size()) {
                        break;
                    } else {
                        arr[i][k] = members2.get(count);
                        count++;
                    }
                }
            }

            // Display the element
            int row = 0;
            boolean check = true;
            while (check) {
                try {
                    for (int j = 0; j < 10; j++) {
                        // When it is running out of element, the program will throw an exception which prevents further error
                        try {
                            System.out.println(arr[row][j].getString());
                        } catch (Exception e) {
                        }
                    }
                    System.out.println("Enter the function:");
                    System.out.println("n is the next page \np is the " +
                            "previous page\nq for quit:");
                    String input = scanner.nextLine().toLowerCase();
                    switch (input) {
                        case "n":
                            if (row < size - 1) {
                                row++;
                            }
                            break;
                        case "p":
                            if (row > 0)
                                row--;
                            break;
                        case "q":
                            check = false;
                            break;
                    }
                } catch (Exception e) {
                    check = false;
                }
            }
        } else {
            for (Member member : members2) {
                System.out.println(member.getString());
            }
            scanner.nextLine();
        }
    }

    public void addNewMember() {
        Scanner scanner = new Scanner(System.in);
        Member member = new Member();
        // Enter the information of the member
        System.out.println("What is your name ?");
        member.setFullName(scanner.nextLine().trim());
        System.out.println("Enter the member ID: ");
        String id = scanner.nextLine().trim();

        // Check the unique of the ID
        while (true) {
            if (!checkIDUnique(id)) {
                System.out.println("The Id already exists. Please re-enter the ID:");
                id = scanner.nextLine().trim();
            } else
                break;
        }
        member.setId(id);

        System.out.println("Enter the phone number: ");
        member.setPhone(scanner.nextLine());
        System.out.println("Enter the mail:");
        member.setMail(scanner.nextLine());
        System.out.println("Enter the address:");
        member.setAddress(scanner.nextLine());
        System.out.println("Enter the expired date in integer format: \neg: 05-11-2020");
        member.setExpiredDate(scanner.nextLine());
        System.out.println("Enter the status ( active or expired): ");
        member.setStatus(scanner.nextLine());

        members.add(member);
        // Announce the statement
        System.out.println("Successfully added");
        scanner.nextLine(); // Give the user time to see user's input

    }

    // Check the ID unique of the member
    public boolean checkIDUnique(String id) {
        for (Member member : members) {
            if (id.equalsIgnoreCase(member.getId())) {
                return false;
            }
        }
        return true;
    }

    public void updateMemberInfo() {
        Scanner scanner = new Scanner(System.in);
        boolean check = true;
        boolean found = false;
        System.out.println("Which member do you want to update.\nEnter the member ID please:");
        String id = scanner.nextLine();
        for (Member member : members) {
            if (id.equalsIgnoreCase(member.getId())) {
                found = true;
                // Ask the user what information to be updated
                while (check) {
                    System.out.println("What information do you want to update ?\n(ID, name , phone , address , status ... etc)\n" +
                            "Enter 0 to quit");
                    String information = scanner.nextLine().toUpperCase();
                    switch (information) {
                        // Set new ID
                        case "ID":
                            System.out.println("What is the new ID ?");
                            String idNew = scanner.nextLine();
                            if (checkIDUnique(idNew)) {
                                member.setId(idNew);
                            } else {
                                System.out.println("This id already exists. The data will not be updated. Please try again");
                                scanner.nextLine();
                            }
                            break;
                        // Set new name
                        case "NAME":
                            System.out.println("What is the new name?");
                            member.setFullName(scanner.nextLine());
                            break;
                        // Set new phone
                        case "PHONE":
                            System.out.println("What is the new phone?");
                            member.setPhone(scanner.nextLine());
                            break;
                        // Set new Address
                        case "ADDRESS":
                            System.out.println("What is the new address?");
                            member.setAddress(scanner.nextLine());
                            break;
                        // Set new mail
                        case "MAIL":
                            System.out.println("What is the new mail?");
                            member.setMail(scanner.nextLine());
                            break;
                        // Set new Status
                        case "STATUS":
                            System.out.println("What is the new status?");
                            member.setStatus(scanner.nextLine());
                            break;
                        case "DATE":
                            System.out.println("What is the new expired date?\neg: 05-11-2020");
                            member.setExpiredDate(scanner.nextLine());
                            break;
                        case "LATE FEE":
                            System.out.println("Enter the late fee: ");
                            member.setLateFee(scanner.nextDouble());
                            scanner.nextLine();
                            break;
                        case "0":
                            check = false;
                            break;
                        default:
                            System.out.println("Cannot find the matched data field");
                            scanner.nextLine();
                    }
                }
            }
        }
        if (!found) {
            System.out.println("Cannot find this member");
            scanner.nextLine();
        }
    }

    public ArrayList<Member> getMembers() {
        return members;
    }


}
