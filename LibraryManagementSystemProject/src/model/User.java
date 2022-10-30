package model;

public class User {
    private String accountID;
    private boolean accountStatus;

    public User(String accountID, boolean accountStatus) {
        this.accountID = accountID;
        this.accountStatus = accountStatus;
    }

    public String getAccountID() {
        return accountID;
    }

    public boolean getAccountStatus() {
        return accountStatus;
    }

    public void setAccountID(String inAccountID) {
        this.accountID = inAccountID;
    }

    public void setAccountStatus(boolean inAccountStatus) {
        this.accountStatus = inAccountStatus;
    }
}
