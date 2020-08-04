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

public class Book extends Item {
    // Data fields of Book class
    private String author;
    private String edition;
    private String isbn;

    //Constructors of Book class
    public Book() {
    }

    public Book(String title, String author, String edition, String publication, String year, String language, String status, String copy, String isbn) {
        super(title, publication, year, language, status, copy);
        this.author = author;
        this.edition = edition;
        this.isbn = isbn;
    }

    // The method to check the validation of ISBN
    public static boolean verifyISBN(String isbn) {
        return verifyInt(isbn) && isbn.length() == 10;
    }

    @Override
    public String toString() {
        return super.toString() + author + '\'' +
                edition + '\'' +
                isbn + '\'';
    }

    @Override
    public String getString() {
        return "Book:\n" + super.getTitle() + "\n"
                + author + "\n" + edition + "\n" + super.getPublication() + "\n"
                + super.getYear() + "\n" + super.getLanguage() + "\n" +
                super.getStatus() + "\nNumber Of Copies: " + getNumberOfCopies() +
                "\nNumber of Copies on Loan: " + super.getNumberOfCopiesOnLoan() + "\n"
                + isbn + "\n";
    }

    // Methods:

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }
}

