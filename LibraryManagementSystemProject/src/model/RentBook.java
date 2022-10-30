package model;

public class RentBook {
    private User user;
    private Book book;
    private String rentDate;

    public RentBook(User user, Book book, String inRentDate) {
        this.user = user;
        this.book = book;
        this.rentDate = inRentDate;
    }

    public String getRentDate() {
        return this.rentDate;
    }

    public void setRentDate(String inRentDate) {
        this.rentDate = inRentDate;
    }

    public void setBook(Book inBook) {
        this.book = inBook;
    }

    public void setUser(User inUser) {
        this.user = inUser;
    }

    public Book getBook() {
        return this.book;
    }

    public User getUser() {
        return this.user;
    }
}
