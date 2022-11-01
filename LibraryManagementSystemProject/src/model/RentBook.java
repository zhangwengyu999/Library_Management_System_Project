package model;

public class RentBook {
    private String accountID;
    private String bookID;
//    private String rentDate;
    private int rentYear;
    private int rentMonth;
    private int rentDay;

    public RentBook(String inAccountID, String inBookID, int inYear, int inMonth, int inDay) {
        this.accountID = inAccountID;
        this.bookID = inBookID;
        this.rentYear = inYear;
        this.rentMonth = inMonth;
        this.rentDay = inDay;
    }

    public int[] getRentDate() {
        return new int[]{rentYear,rentMonth,rentDay};
    }

    public void setRentDate(int[] inRentDate) {
        this.rentYear = inRentDate[0];
        this.rentMonth = inRentDate[1];
        this.rentDay = inRentDate[2];
    }

    public void setBook(String inBookID) {
        this.bookID = inBookID;
    }

    public void setUser(String inAccountID) {
        this.accountID = inAccountID;
    }

    public String getBook() {
        return this.bookID;
    }

    public String getUser() {
        return this.accountID;
    }
}
