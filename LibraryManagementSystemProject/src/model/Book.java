package model;

public class Book {
    private String bookID;
    private String ISBN;
    private String bookName;
    private String publisher;
    private String category;
    private boolean[] status; // Length in 3, isRent, isAvailable, isPlaced, true for yes, false for no
    private String timeStamp;


    public Book(String bookID, String ISBN, String bookName, String publisher, String category, String timeStamp){
        this.bookID = bookID;
        this.ISBN = ISBN;
        this.bookName = bookName;
        this.publisher = publisher;
        this.category = category;
        this.status = new boolean[]{false,true,false};
        this.timeStamp = timeStamp;
    }

    // getter
    public String getISBN() {
        return ISBN;
    }

    public String getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getCategory() {
        return category;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public boolean[] getStatus() {
        return status;
    }

    // setter
    public void setISBN(String inISBN) {
        this.ISBN = inISBN;
    }

    public void setBookID(String inBookID) {
        this.bookID = inBookID;
    }

    public void setBookName(String inBookName) {
        this.bookName = inBookName;
    }

    public void setPublisher(String inPublisher) {
        this.publisher = inPublisher;
    }

    public void setCategory(String inCategory) {
        this.category = inCategory;
    }

    public void setTimeStamp(String inTimeStamp) {
        this.timeStamp = inTimeStamp;
    }

    public void setRent(boolean inRent) {
        this.status[0] = inRent;
    }

    public void setAvailable(boolean inAvailable) {
        this.status[1] = inAvailable;
    }

    public void setPlaced(boolean inPlaced) {
        this.status[2] = inPlaced;
    }

    // for JDBC
    public void pullInfo(){
        getISBN();
        getBookID();
        getBookName();
        getCategory();
        getStatus();
        getPublisher();
        getTimeStamp();
    }

    // for JDBC
    public void pushInfo() {

    }
}
