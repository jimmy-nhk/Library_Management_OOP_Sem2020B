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

public class DVD extends Item {
    private static int numberOfDVDCreated = 0;
    // Data fields of DVD class
    private int id; // ID will be determined by the number of DVD created
    private String author;

    //Constructors of DVD class
    public DVD() {
    }

    public DVD(String title, String author, String publication, String year
            , String language, String status, String copy) {
        super(title, publication, year, language, status, copy);
        numberOfDVDCreated++;
        this.id = numberOfDVDCreated;
        this.author = author;
    }

    @Override
    public String toString() {
        return super.toString() + author + id;
    }

    // This method is used to display in the console and the text
    @Override
    public String getString() {
        return "DVD:\n" + super.getTitle() + "\n"
                + author + "\n" + super.getPublication() + "\n"
                + super.getYear() + "\n" + super.getLanguage() + "\n" +
                super.getStatus() + "\nNumber Of Copies: " + getNumberOfCopies() +
                "\nNumber of Copies on Loan: " + super.getNumberOfCopiesOnLoan() + "\n";
    }


    //  Methods:

    public int getId() {
        return id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}


