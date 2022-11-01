package model;

public class User {
    private String accountID;
    private boolean accountStatus;
    private String noticeString;
    private int reserveCount;

    public User(String accountID, boolean accountStatus, String noticeString) {
        this.accountID = accountID;
        this.accountStatus = accountStatus;
        this.noticeString = noticeString;
    }

    public String getAccountID() {
        return accountID;
    }

    public boolean getAccountStatus() {
        return accountStatus;
    }

    public String getNoticeString(){
        return noticeString;
    }

    public void setAccountID(String inAccountID) {
        this.accountID = inAccountID;
    }

    public void setAccountStatus(boolean inAccountStatus) {
        this.accountStatus = inAccountStatus;
    }

    public void setNoticeString(String noticeString){this.noticeString = noticeString;}
}
