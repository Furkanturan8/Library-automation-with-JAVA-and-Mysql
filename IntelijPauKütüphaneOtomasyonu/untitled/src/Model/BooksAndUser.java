package Model;

public class BooksAndUser {

    private int bookID;
    private int userID;
    private String firstDate;
    private String deadline;

    public BooksAndUser() {}
    public BooksAndUser(int bookID, int userID, String firstDate, String deadline) {
        this.bookID=bookID;
        this.userID=userID;
        this.deadline=deadline;
        this.firstDate=firstDate;
    }


    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public String getFirstDate() {
        return firstDate;
    }
    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }
    public String getDeadline() {
        return deadline;
    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }



}
