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

public class Journal extends Item {
    // Data fields for the Journal class
    private String issn;

    //Constructors of the Journal class
    public Journal() {
    }

    public Journal(String title, String publication, String year,
                   String language, String status, String copy, String issn) {
        super(title, publication, year, language, status, copy);
        this.issn = issn;
    }

    // Check the format of ISSN
    public static boolean verifyISSN(String issn) {
        return verifyInt(issn) && issn.length() == 8;
    }

    @Override
    public String toString() {
        return super.toString() + issn;
    }

    // This method displays the information on the console and file
    @Override
    public String getString() {
        return "Journal:\n" + super.getTitle() + "\n"
                + super.getPublication() + "\n"
                + super.getYear() + "\n" + super.getLanguage() + "\n" +
                super.getStatus() + "\nNumber Of Copies: " + getNumberOfCopies() +
                "\nNumber of Copies on Loan: " + super.getNumberOfCopiesOnLoan() + "\n"
                + issn + "\n";
    }

    // Methods get and set
    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }
}



