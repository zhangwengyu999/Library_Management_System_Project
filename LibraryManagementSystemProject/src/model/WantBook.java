package model;

public class WantBook {
    private User user;
    private String wantISBN;
    private int size;
    private String wantDate;

    public WantBook(User user, String InWantISBN, String InWantDate) {
        this.user = user;
        this.wantISBN = InWantISBN;
        this.wantDate = InWantDate;
        this.size = 0;
    }

    public String getUserAccountID() {
        return user.getAccountID();
    }

//    // add user's want book
//    public void addWantISBN(String ISBN) {
//        // ...
//    }
//
//    // remove user's want book
//    public void removeWantISBN(String ISBN) {
//        // ...
//    }

    public String getWantISBNs() {
        return this.wantISBN;
    }

    public String getWantDate() {
        return this.wantDate;
    }

    public void setWantDate(String inWantDate) {
        this.wantDate = inWantDate;
    }
}

