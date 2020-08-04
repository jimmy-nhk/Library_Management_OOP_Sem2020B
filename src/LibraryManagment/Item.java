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

import java.util.Scanner;

public abstract class Item {
    // Data fields of Collection class
    private String title;
    private String publication;
    private int year;
    private String language;
    private String status;
    private int numberOfCopies;
    private int numberOfCopiesOnLoan;

    // Constructors of Collection class
    public Item() {
    }

    public Item(String title, String publication, String year, String language, String status, String numberOfCopies) {
        this.title = title;
        this.publication = publication;
        this.language = language;
        numberOfCopiesOnLoan = 0; // By default , nothing is on loan yet
        this.status = status;
        this.year = Integer.parseInt(year);
        // this.numberOfCopies = Integer.parseInt(numberOfCopies);
        try {
            this.numberOfCopies = Integer.parseInt(numberOfCopies);
        } catch (Exception e) {
            System.out.println("Enter the wrong format. This item has not been finished updating yet");
        }

    }

    // Method to verify the string in the correct format.
    public static boolean verifyInt(String integer) {
        try {
            int a = Integer.parseInt(integer);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return title + '\'' +
                publication + '\'' +
                year +
                language + '\'' +
                status + '\'' +
                numberOfCopies +
                numberOfCopiesOnLoan;
    }

    // Get and Set methods:

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public int getYear() {
        return year;
    }

    // Make sure the year in the correct format
    public void setYear(String year) {
        //Check the right form of the year
        Scanner scanner = new Scanner(System.in);
        if (verifyInt(year)) {
            this.year = Integer.parseInt(year);
        } else {
            System.out.println("Enter again the year in the correct format: ");
            try {
                this.year = scanner.nextInt();
                scanner.nextLine(); // This helps for the next scan
            } catch (Exception e) {
                this.year = 0;
            }
        }
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStatus() {
        return status;
    }

    // This method sets the status in the right format
    public void setStatus(String status) {
        Scanner scanner = new Scanner(System.in);
        // Check the right form for the status
        boolean check = true;
        while (check) {
            if (status.toUpperCase().equalsIgnoreCase("ON LOAN")) {
                this.status = "On Loan";
                check = false;
            } else if (status.toUpperCase().equalsIgnoreCase("AVAILABLE")) {
                this.status = "Available";
                check = false;
            } else {
                System.out.println("Enter again the status in the correct format ( Available or On Loan ): ");
                status = scanner.nextLine();
            }
        }
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(String numberOfCopies) {
        Scanner scanner = new Scanner(System.in);
        // Check the right format of numberOfCopies
        if (verifyInt(numberOfCopies)) {
            this.numberOfCopies = Integer.parseInt(numberOfCopies);
        } else {
            System.out.println("Enter again the number of copies in the correct format: ");
            try {
                this.numberOfCopies = scanner.nextInt();
                scanner.nextLine(); // This helps for the next scan
            } catch (Exception e) {
                this.numberOfCopies = 1;
            }
        }
    }

    public int getNumberOfCopiesOnLoan() {
        return numberOfCopiesOnLoan;
    }

    // This method helps when borrowing the item
    public void increaseNumberOfCopiesOnLoan() {
        numberOfCopiesOnLoan++;
    }

    // This method reduce the number of compies on loan
    public void decreaseNumberOfCopiesOnLoan() {
        numberOfCopiesOnLoan--;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public abstract String getString();
}
