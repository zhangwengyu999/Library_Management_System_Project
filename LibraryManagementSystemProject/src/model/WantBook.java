package model;

public class WantBook {
    private String accountID;
    private String wantISBN;
    private int size;

    private int wantYear;
    private int wantMonth;
    private int wantDay;

    public WantBook(String inAccountID, String InWantISBN, int inWantYear, int inWantMonth, int inWantDay) {
        this.accountID = inAccountID;
        this.wantISBN = InWantISBN;
        this.wantYear = inWantYear;
        this.wantMonth = inWantMonth;
        this.wantDay = inWantDay;
        this.size = 0;
    }


    public void SetUserAccountID(String inAccountID) {
        this.accountID = inAccountID;
    }

    public String getUserAccountID() {
        return this.accountID;
    }


    public String getWantISBNs() {
        return this.wantISBN;
    }

    public int[] getWantDate() {
        return new int[]{wantYear,wantMonth,wantDay};
    }

    public void setWantDate(int[] inDate) {
        this.wantYear = inDate[0];
        this.wantMonth = inDate[1];
        this.wantDay = inDate[2];
    }
}

