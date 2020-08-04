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

import static LibraryManagment.Book.verifyISBN;
import static LibraryManagment.Journal.verifyISSN;

public class ItemList {
    public static final File BOOKFILE = new File("src/Book.txt");
    public static final File DVDFILE = new File("src/DVD.txt");
    public static final File JOURNALFILE = new File("src/Journal.txt");
    // Data fields:
    private ArrayList<Item> items = new ArrayList<>();

    //Methods:
    public void start() {
        try {
            Scanner sc = new Scanner(BOOKFILE);
            while (sc.hasNext()) {
                String title = sc.nextLine();
                String author = sc.nextLine();
                String edition = sc.nextLine();
                String publication = sc.nextLine();
                String year = sc.nextLine();
                String language = sc.nextLine();
                String status = sc.nextLine();
                String copy = sc.nextLine();
                String isbn = sc.nextLine();
                items.add(new Book(title, author, edition, publication, year, language, status, copy, isbn) {
                });
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file");
        }

        try {
            Scanner sc = new Scanner(JOURNALFILE);
            while (sc.hasNext()) {
                String title = sc.nextLine();
                String publication = sc.nextLine();
                String year = sc.nextLine();
                String language = sc.nextLine();
                String status = sc.nextLine();
                String copy = sc.nextLine();
                String issn = sc.nextLine();
                items.add(new Journal(title, publication, year, language, status, copy, issn) {
                });
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file");
        }

        try {
            Scanner sc = new Scanner(DVDFILE);
            while (sc.hasNext()) {
                String title = sc.nextLine();
                String author = sc.nextLine();
                String publication = sc.nextLine();
                String year = sc.nextLine();
                String language = sc.nextLine();
                String status = sc.nextLine();
                String copy = sc.nextLine();
                items.add(new DVD(title, author, publication, year, language, status, copy) {
                });
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file");
        }

    }

    public void searchItem() {
        ArrayList<Item> items2 = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the keyword you want to search: ");
        String input = scanner.nextLine(); // Enter the keyword want to search
        String pattern = ".*" + input + ".*"; // Making the pattern for searching

        boolean checkKeyWordExists = false;
        for (Item item : items) {
            if (item.toString().toLowerCase().matches(pattern.toLowerCase())) {
                items2.add(item);
                checkKeyWordExists = true;
            }
        }
        // Call a method helping us display a list of member in the library
        if (checkKeyWordExists) {
            displayItem(items2);
        } else{
            displayItem(items);
        }

    }

    public void displayItem(ArrayList<Item> items2) {
        Scanner scanner = new Scanner(System.in);
        int size = (int) Math.ceil(items2.size() / 10.0);
        if (size > 1) {
            Item[][] arr = new Item[size][10];
            int count = 0; // get the element from arrayList member

            // Insert the arraylist member to array
            for (int i = 0; i < size; i++) {
                for (int k = 0; k < 10; k++) {
                    if (count == items2.size()) {
                        break;
                    } else {
                        arr[i][k] = items2.get(count);
                        count++;
                    }
                }
            }
            int tmpt = 0;
            boolean check = true;
            while (check) {
                try {
                    for (int j = 0; j < 10; j++) {
                        // When it is running out of element, the program will throw an exception which prevents further error
                        try {
                            System.out.println(arr[tmpt][j].getString());
                        } catch (Exception e) {
                        }
                    }
                    System.out.println("Enter the function:");
                    System.out.println("n is the next page \np is the " +
                            "previous page\nq for quit:");
                    String input = scanner.nextLine().toLowerCase();
                    switch (input) {
                        case "n":
                            if (tmpt < size - 1)
                                tmpt++;
                            break;
                        case "p":
                            if (tmpt > 0)
                                tmpt--;
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
            for (Item item : items2) {
                System.out.println(item.getString());
            }
            scanner.nextLine();
        }

    }

    // Main method in menu
    public void addNewItem() {
        System.out.println("What is the item ?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toUpperCase();
        switch (input) {
            case "BOOK":
                newBook();
                break;
            case "DVD":
                newDVD();
                break;
            case "JOURNAL":
                newJournal();
                break;
            default:
                System.out.println("Nothing has been inputted yet or in the wrong type. Please select the method again if you want further action");
        }
    }

    // Method in the main menu
    public void updateItemInfo() {
        System.out.println("What is the item ?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toUpperCase();
        switch (input) {
            case "BOOK":
                bookUpdate();
                break;
            case "DVD":
                dvdUpdate();
                break;
            case "JOURNAL":
                journalUpdate();
                break;
            default:
                System.out.println("Cannot find the input item");
                scanner.nextLine();
        }
    }

    // Methods contribute for updateItemInfo method
    public void journalUpdate() {
        Scanner scanner = new Scanner(System.in);
        boolean check = true;
        boolean checkJournalExists = false; // Check the journal existence
        System.out.println("What is the ISSN of this journal?");
        String issn = scanner.nextLine();
        for (Item item : items) {
            if (item instanceof Journal) {
                if (issn.equalsIgnoreCase(((Journal) item).getIssn())) {
                    checkJournalExists = true;
                    while (check) {
                        System.out.println("Which information wanted to be updated ?\n(Title / Author / Publication / Year / ISBN / number Of Copies\n Enter 0 to finish updating )");
                        String information = scanner.nextLine().toUpperCase();
                        switch (information) {
                            case "TITLE":
                                System.out.println("What is the new title ? ");
                                item.setTitle(scanner.nextLine());
                                break;
                            case "PUBLICATION":
                                System.out.println("Who are the publications ? ");
                                item.setPublication(scanner.nextLine());
                                break;
                            case "YEAR":
                                System.out.println("What is the year ?");
                                item.setYear(scanner.nextLine());
                                break;
                            case "LANGUAGE":
                                System.out.println("What is the language of the journal?");
                                item.setLanguage(scanner.nextLine());
                            case "STATUS":
                                System.out.println("Is it on loan or available ?");
                                item.setStatus(scanner.nextLine());
                                break;
                            case "NUMBER OF COPIES":
                                System.out.println("What is the number of copies?");
                                item.setNumberOfCopies(scanner.nextLine());
                                break;
                            case "0":
                                check = false;
                                break;
                            default:
                                System.out.println("No data field like that in this Journal");
                        }
                    }
                }
            }
        }
        if (!checkJournalExists){
            System.out.println("This journal does not exist");
            scanner.nextLine();
        }
    }

    // Methods contribute for updateItemInfo method
    public void dvdUpdate() {
        Scanner scanner = new Scanner(System.in);
        boolean check = true;
        boolean checkIDExists = false; // Check if Dvd exists
        System.out.println("What is the id of this DVD?");
        // If the id is in the string format , display the error.
        try {
            int id = Integer.parseInt(scanner.nextLine());
            for (Item item : items) {
                if (item instanceof DVD) {
                    if (id == ((DVD) item).getId()) {
                        checkIDExists = true;
                        while (check) {

                            // Ask what information to be updated
                            System.out.println("Which information wanted to be updated ?\n(Title / Author / Publication / Year / ISBN / numberOfCopies etc.. \n Enter 0 to finish updating )");
                            String information = scanner.nextLine().toUpperCase();
                            switch (information) {
                                case "TITLE":
                                    System.out.println("What is the new title ? ");
                                    item.setTitle(scanner.nextLine());
                                    break;
                                case "PUBLICATION":
                                    System.out.println("Who are the publications ? ");
                                    item.setPublication(scanner.nextLine());
                                    break;
                                case "AUTHOR":
                                    System.out.println("Who are the authors? ");
                                    ((DVD) item).setAuthor(scanner.nextLine());
                                    break;
                                case "YEAR":
                                    System.out.println("What is the year ?");
                                    item.setYear(scanner.nextLine());
                                    break;
                                case "LANGUAGE":
                                    System.out.println("What is the language of the book?");
                                    item.setLanguage(scanner.nextLine());
                                case "STATUS":
                                    System.out.println("Is it on loan or available ?");
                                    item.setStatus(scanner.nextLine());
                                    break;
                                case "NUMBER OF COPIES":
                                    System.out.println("What is the number of copies?");
                                    item.setNumberOfCopies(scanner.nextLine());
                                    break;
                                case "0":
                                    check = false;
                                    break;
                                default:
                                    System.out.println("No data field like that in this DVD");
                            }
                        }
                    }
                }
            }
            if (!checkIDExists){
                System.out.println("This dvd does not exist");
                scanner.nextLine();
            }
        } catch (Exception e){
            System.out.println("The id should be integer");
            scanner.nextLine();
        }

    }

    // Methods contribute for updateItemInfo method
    public void bookUpdate() {
        Scanner scanner = new Scanner(System.in);
        boolean check = true;
        boolean checkIsbnExists = false; // Check if this book exists ?
        System.out.println("What is the ISBN of this book?");
        String isbn = scanner.nextLine();
        for (Item item : items) {
            if (item instanceof Book) {
                if (isbn.equalsIgnoreCase(((Book) item).getIsbn())) {
                    checkIsbnExists = true;
                    while (check) {
                        System.out.println("Which information wanted to be updated ?\n(Title / Author / Publication / Year / ISBN / numberOfCopies\n Enter 0 to finish updating )");
                        String information = scanner.nextLine().toUpperCase();
                        switch (information) {
                            case "TITLE":
                                System.out.println("What is the new title ? ");
                                item.setTitle(scanner.nextLine());
                                break;
                            case "PUBLICATION":
                                System.out.println("Who are the publications ? ");
                                item.setPublication(scanner.nextLine());
                                break;
                            case "AUTHOR":
                                System.out.println("Who are the authors? ");
                                ((Book) item).setAuthor(scanner.nextLine());
                                break;
                            case "EDITION":
                                System.out.println("What is the edition ?");
                                ((Book) item).setEdition(scanner.nextLine());
                                break;
                            case "YEAR":
                                System.out.println("What is the year ?");
                                item.setYear(scanner.nextLine());
                                break;
                            case "LANGUAGE":
                                System.out.println("What is the language of the book?");
                                item.setLanguage(scanner.nextLine());
                            case "STATUS":
                                System.out.println("Is it on loan or available ?");
                                item.setStatus(scanner.nextLine());
                                break;
                            case "NUMBER OF COPIES":
                                System.out.println("What is the number of copies?");
                                item.setNumberOfCopies(scanner.nextLine());
                                break;
                            case "0":
                                check = false;
                                break;
                            default:
                                System.out.println("No data field like that in this Book");
                        }
                    }
                }
            }
        }
        if (!checkIsbnExists){
            System.out.println("This book does not exist.");
            scanner.nextLine();
        }
    }


    // Methods import items:
    public void newJournal() {
        Scanner scanner = new Scanner(System.in);
        // Create a new Journal
        Journal journal = new Journal();

        // Enter the information of the Journal
        System.out.println("What is the title of the Journal? ");
        journal.setTitle(scanner.nextLine().trim());
        System.out.println("What is the publication? ");
        journal.setPublication(scanner.nextLine().trim());
        System.out.println("What is the year? ");
        journal.setYear(scanner.nextLine().trim());
        System.out.println("What is the language of this Journal? ");
        journal.setLanguage(scanner.nextLine().trim());
        System.out.println("Is it available or on loan ?");
        journal.setStatus(scanner.nextLine().trim());
        System.out.println("How many copies of this Journal?");
        journal.setNumberOfCopies(scanner.nextLine().trim());
        System.out.println("Enter the ISSN of this Journal?");
        String issn = scanner.nextLine().trim();

        // Check if the ISSN is unique and in the correct format
        while (true) {
            do {
                if (verifyISSN(issn)) {
                    break;
                } else {
                    // Re-enter the issn until it is in the right format
                    System.out.println("Enter again the ISSN in the correct format: ");
                    issn = scanner.nextLine().trim();
                    if (verifyISSN(issn))
                        break;
                }
            } while (true);
            if (!checkISSNUnique(issn)) {
                // Re-enter the ISBN until it is unique
                System.out.println("The ISSN already exists. Please re-enter the ISSN:");
                issn = scanner.nextLine().trim();
            } else
                break;
        }
        journal.setIssn(issn);

        items.add(journal);
        // Announce the done action:
        System.out.println("Successfully added");
        scanner.nextLine(); // Give the user time to see user's input
    }

    public void newDVD() {
        Scanner scanner = new Scanner(System.in);
        DVD dvd = new DVD();
        // Enter the information of the DVD
        System.out.println("What is the title of the DVD? ");
        dvd.setTitle(scanner.nextLine().trim());
        System.out.println("What is the author of the DVD?");
        dvd.setAuthor(scanner.nextLine().trim());
        System.out.println("What is the publication? ");
        dvd.setPublication(scanner.nextLine().trim());
        System.out.println("What is the year? ");
        dvd.setYear(scanner.nextLine().trim());
        System.out.println("What is the language of this DVD? ");
        dvd.setLanguage(scanner.nextLine().trim());
        System.out.println("Is it available or on loan ?");
        dvd.setStatus(scanner.nextLine().trim());
        System.out.println("How many copies of this DVD?");
        dvd.setNumberOfCopies(scanner.nextLine().trim());

        items.add(dvd);
        // Announce the done action:
        System.out.println("Successfully added");
        scanner.nextLine(); // Give the user time to see user's input
    }

    public void newBook() {
        Scanner scanner = new Scanner(System.in);
        Book book = new Book();

        // Enter the information of the book
        System.out.println("What is the title of the book? ");
        book.setTitle(scanner.nextLine().trim());
        System.out.println("What is the author of the book?");
        book.setAuthor(scanner.nextLine().trim());
        System.out.println("What is the edition ?");
        book.setEdition(scanner.nextLine().trim());
        System.out.println("What is the publication? ");
        book.setPublication(scanner.nextLine().trim());
        System.out.println("What is the year? ");
        book.setYear(scanner.nextLine().trim());
        System.out.println("What is the language of this book? ");
        book.setLanguage(scanner.nextLine().trim());
        System.out.println("Is it available or on loan ?");
        book.setStatus(scanner.nextLine().trim());
        System.out.println("How many copies of this book?");
        book.setNumberOfCopies(scanner.nextLine().trim());
        System.out.println("Enter the ISBN of this book?");
        String isbn = scanner.nextLine().trim();
        // Check if isbn is unique and in the correct format
        while (true) {
            do {
                if (verifyISBN(isbn)) {
                    break;
                } else {
                    // Re-enter the ISBN until it is in the right format
                    System.out.println("Enter again the isbn in the correct format: ");
                    isbn = scanner.nextLine().trim();
                    if (verifyISBN(isbn))
                        break;
                }
            } while (true);
            if (!checkISBNUnique(isbn)) {
                // Re-enter the ISBN until it is unique
                System.out.println("The ISBN already exists. Please re-enter the ISBN:");
                isbn = scanner.nextLine().trim();
            } else
                break;
        }
        book.setIsbn(isbn);

        items.add(book);
        // Announce the done action:
        System.out.println("Successfully added");
        scanner.nextLine(); // Give the user time to see user's input
    }

    // Check if the book already exists
    public boolean checkISBNUnique(String isbn) {
        for (Item item : items) {
            if (item instanceof Book) {
                if (isbn.equalsIgnoreCase(((Book) item).getIsbn())) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if the journal already exists
    public boolean checkISSNUnique(String isbn) {
        for (Item item : items) {
            if (item instanceof Journal) {
                if (isbn.equalsIgnoreCase(((Journal) item).getIssn())) {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
