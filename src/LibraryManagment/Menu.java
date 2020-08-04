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
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {
    public static final File BORROWINGFILE = new File("src/Borrowing Record.txt");
    // Attributes:
    public static ItemList itemList = new ItemList();
    public static MemberList memberList = new MemberList();
    public static Scanner scanner = new Scanner(System.in);


    // Methods:

    public static void main(String[] args) {
        // Start the program by loading all the files in:
        start();

        while (true) {
            // Display the menu first for the user
            displayMenu();

            // If it is in the wrong format, please kindly re-enter the input
            try {
                int input = Integer.parseInt(scanner.nextLine());
                while (input < 1 || input > 10) {
                    System.out.println("The input should be the integer from 1 to 10.\nPlease kindly re-input the function:");
                    input = Integer.parseInt(scanner.nextLine());
                }

                // Choose the function:
                switch (input) {
                    case 1:
                        itemList.searchItem();
                        break;
                    case 2:
                        itemList.addNewItem();
                        break;
                    case 3:
                        itemList.updateItemInfo();
                        break;
                    case 4:
                        memberList.searchMember();
                        break;
                    case 5:
                        memberList.addNewMember();
                        break;
                    case 6:
                        memberList.updateMemberInfo();
                        break;
                    case 7:
                        borrowItems();
                        break;
                    case 8:
                        returnItems();
                        break;
                    case 9:
                        saveData();
                        break;
                    case 10:
                        System.out.println("The program exits. Bye!");
                        saveData(); // Save the data.
                        quit();
                }
            } catch (Exception e) {
                System.out.println("The input should be an integer from 1 to 10.");
                System.out.println("Press enter to go back to the menu");
                scanner.nextLine();
            }
        }
    }

    public static void start() {
        itemList.start(); // Load item from item files
        memberList.start(); // Load members from member file

        // Load the borrowing records with its member:
        try {
            Scanner sc = new Scanner(BORROWINGFILE);
            while (sc.hasNext()) {
                String id = sc.nextLine();
                String input = sc.nextLine();
                if (checkItemExists(input)) {
                    // Add the item to the method
                    int itemTh = returnItemExists(input); /** which is the item in the itemList */

                    // Check the item status , if it is not available, display a message
                    if (checkItemStatus(itemList.getItems().get(itemTh))) {
                        // Check if the member exists
                        if (checkMemberValidation(id)) {

                            /** Which is the member in the memberList: */
                            int memberTh = returnMemberExists(id);

                            /** Add the loan to the member */
                            memberList.getMembers().get(memberTh).loadLoans(new Loan(itemList.getItems().get(itemTh)));
                        } else {
                            System.out.println("The member does not exists in the system or might be expired.");
                        }
                    } else {
                        System.out.println("This item is not available yet.");
                    }
                } else {
                    System.out.println("The item cannot be found in the system.");
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file");
        }
    }

    // Display the menu:
    public static void displayMenu() {
        System.out.println("Welcome to QuickLib!!!\n" +
                "********************************\n" +
                "1. Search items by keywords\n" +
                "2. Add new item\n" +
                "3. Update item info\n" +
                "4. Search members by keywords\n" +
                "5. Register new member\n" +
                "6. Update member info\n" +
                "7. Borrow items\n" +
                "8. Return items\n" +
                "9. Save data\n" +
                "10. Quit\n" +
                "**************************************\n" +
                "Enter a function (1-10):");
    }

    // Quit method
    public static void quit() {
        System.exit(0);
    }

    //Borrow function
    public static void borrowItems() {
        System.out.println("Enter the ISBN (Book) or ID (DVD) or ISSN (Journal): ");
        String input = scanner.nextLine();

        // Check if the item exists in the library:
        if (checkItemExists(input)) {
            // Add the item to the method
            int itemTh = returnItemExists(input); /** which is the item in the itemList */

            // Check the item status , if it is not available, display a message
            if (checkItemStatus(itemList.getItems().get(itemTh))) {

                // Check the id of the member:
                System.out.println("Enter the id of the member: ");
                String id = scanner.nextLine();

                // Check if the member exists
                if (checkMemberValidation(id)) {

                    /** Which is the member in the memberList: */
                    int memberTh = returnMemberExists(id);

                    /** Add the loan to the member */
                    memberList.getMembers().get(memberTh).addLoans(new Loan(itemList.getItems().get(itemTh)));
                } else {
                    System.out.println("The member does not exists in the system or might be expired.");
                    scanner.nextLine();
                }
            } else {
                System.out.println("This item is not available yet.");
                scanner.nextLine();
            }
        } else {
            System.out.println("The item cannot be found in the system.");
            scanner.nextLine();
        }
    }

    // Check the status of the item to make sure it is available:
    public static boolean checkItemStatus(Item item) {
        return item.getStatus().equalsIgnoreCase("available");
    }

    // Return the actual item:
    public static int returnItemExists(String input) {

        for (int i = 0; i < itemList.getItems().size(); i++) {
            // Double access to get the member id to compare with the entered keyword
            if (itemList.getItems().get(i) instanceof Book) {
                if (((Book) itemList.getItems().get(i)).getIsbn().equalsIgnoreCase(input)) {
                    return i;
                }
                // Check through out the 3 cases:
            } else if (itemList.getItems().get(i) instanceof DVD) {
                // Just in case the input is not the integer so it is needed to handle the error
                try {
                    if (((DVD) itemList.getItems().get(i)).getId() == Integer.parseInt(input)) {
                        return i;
                    }
                } catch (Exception e) {
                }
            } else if (itemList.getItems().get(i) instanceof Journal) {
                if (((Journal) itemList.getItems().get(i)).getIssn().equalsIgnoreCase(input)) {
                    return i;
                }
            }
        }
        return 0;
    }

    //     Check if the item exists
    public static boolean checkItemExists(String input) {

        // Run the loop to check if the item exists
        for (int i = 0; i < itemList.getItems().size(); i++) {
            // Double access to get the member id to compare with the entered keyword
            if (itemList.getItems().get(i) instanceof Book) {
                if (((Book) itemList.getItems().get(i)).getIsbn().equalsIgnoreCase(input)) {
                    return true;
                }
            } else if (itemList.getItems().get(i) instanceof DVD) {
                try {
                    if (((DVD) itemList.getItems().get(i)).getId() == Integer.parseInt(input)) {
                        return true;
                    }
                } catch (Exception e) {
                }
            } else if (itemList.getItems().get(i) instanceof Journal) {
                if (((Journal) itemList.getItems().get(i)).getIssn().equalsIgnoreCase(input)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check if the member is already registered in the library and still active ?
    public static boolean checkMemberValidation(String id) {
        Member member = new Member();
        for (int i = 0; i < memberList.getMembers().size(); i++) {
            // Double access to get the member id to compare with the entered keyword
            if (memberList.getMembers().get(i).getId().equalsIgnoreCase(id)) {
                member = memberList.getMembers().get(i);
                if (member.getStatus().equalsIgnoreCase("Active")) {
                    return true;
                }
            }
        }
        return false;
    }

    // Return which member in the memberList wants to borrow an item:
    public static int returnMemberExists(String id) {
        for (int i = 0; i < memberList.getMembers().size(); i++) {
            // Double access to get the member id to compare with the entered keyword
            if (memberList.getMembers().get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return 0;
    }


    public static void returnItems() {
        System.out.println("Enter the ID member: ");
        String id = scanner.nextLine();

        // Check if member exists
        if (checkMemberValidation(id)) {

            int memberTh = returnMemberExists(id);
            System.out.println("Enter the ISBN (Book) or ID (DVD) or ISSN (Journal): ");
            String input = scanner.nextLine();

            // Check if item exists
            if (checkItemExists(input)) {
                int itemTh = returnItemExists(input);

                // Check the loan of this member
                int loanTh = memberList.getMembers().get(memberTh).findLoan(itemList.getItems().get(itemTh));
                // This variable shows which loan the member wants to return
                if (loanTh >= 0) {

                    // Enter the return date:
                    System.out.println("Enter the returned date:\neg: 05-11-2020");

                    // Complete the loan class:
                    memberList.getMembers().get(memberTh).getLoans()[loanTh].setReturnedDate(scanner.nextLine().trim());

                    // Calculate the late fee
                    double fee = memberList.getMembers().get(memberTh).getLoans()[loanTh].calculateFee();
                    memberList.getMembers().get(memberTh).setLateFee(fee);

                    // Update the number of copies on loan
                    itemList.getItems().get(itemTh).decreaseNumberOfCopiesOnLoan();

                    // Update status of the item:
                    itemList.getItems().get(itemTh).setStatus("Available");

                    // Remove the loan from the memberList
                    memberList.getMembers().get(memberTh).getLoans()[loanTh] = null;

                    // Announce the statement
                    System.out.println("Successfully return the item");
                    scanner.nextLine();
                } else {
                    System.out.println("This member has not borrowed this item before.");
                    System.out.println("Press enter to go back to the menu");
                    scanner.nextLine();
                }
            }
        } else {
            System.out.println("This member does not exists. Please re-select the method");
            System.out.println("Press enter to go back to the menu");
            scanner.nextLine();
        }
    }

    // Last method to saveData to file:
    public static void saveData() {
        try {
            PrintWriter printWriter = new PrintWriter("src/Output.txt");

            // Save Item to the file first
            printWriter.println("----ITEM----");
            for (int i = 0; i < itemList.getItems().size(); i++) {
                printWriter.println(itemList.getItems().get(i).getString());
            }

            printWriter.println("----MEMBER----");
            // Save member to the file:
            for (int i = 0; i < memberList.getMembers().size(); i++) {
                printWriter.println(memberList.getMembers().get(i).getString());
            }
            printWriter.close(); // Close the file to save the data

            System.out.println("Successfully save the data.");


        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file");
        }
    }

}
