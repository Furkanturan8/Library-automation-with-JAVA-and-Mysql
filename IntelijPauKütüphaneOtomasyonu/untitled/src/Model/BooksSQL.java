package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Helper.DBConnection;



public class BooksSQL {

    static Books books = new Books();
    static BooksAndUser booksanduser = new BooksAndUser();

    public BooksSQL() {}

    DBConnection conn = new DBConnection();
    Connection con = conn.connDb();
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement preparedStatement = null;

    public ArrayList<Books> getBooksList() throws SQLException{
        ArrayList<Books> list = new ArrayList<>();
        Books obj;

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM books");
            while(rs.next()) {
                obj = new Books(rs.getInt("id"),rs.getString("bookName"),rs.getString("bookAuthor"),rs.getString("bookType"),rs.getString("bookStatus"));
                list.add(obj);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean addBooks(String bookName, String bookAuthor, Object bookType) throws SQLException {

        String Query = "INSERT INTO books " + "(bookName,bookAuthor,bookType) VALUES" + "(?,?,?)";
        boolean key = false;

        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setString(1, bookName);
            preparedStatement.setString(2, bookAuthor);
            preparedStatement.setObject(3, bookType);
            preparedStatement.executeUpdate();
            key = true ; //true;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return key;
    }


    public boolean updateBooks(int id,String bookName, String bookAuthor, Object bookType) throws SQLException {

        String Query = "UPDATE books SET bookName = ? , bookAuthor = ? , bookType = ? WHERE id = ?";
        boolean key = false;

        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setString(1,bookName); // bu yukaridaki ? yerine gelecek
            preparedStatement.setString(2,bookAuthor);
            preparedStatement.setObject(3,bookType);
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
            key = true ; //true;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return key;
    }



    public boolean deleteBook(int id) throws SQLException {

        String Query = "DELETE FROM books WHERE id = ?";
        boolean key = false;

        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            key = true ;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return key;
    }

    public boolean bookStatus(String status, int id) {

        String Query = "UPDATE books SET bookStatus = ? WHERE id = ?";
        boolean key = false;

        try {
            Books selBook = new Books();
            selBook = getBook(id);

            if(selBook.getBookStatus().equals("1")) {
                st = con.createStatement();
                preparedStatement = con.prepareStatement(Query);
                preparedStatement.setString(1,status);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
                key = true;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        return key;
    }

    public Books getBook(int id) {
        Books obj = new Books();
        try {

            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM books WHERE id="+id);
            while(rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setBookAuthor(rs.getString("bookAuthor"));
                obj.setBookName(rs.getString("bookName"));
                obj.setBookType(rs.getString("bookType"));
                obj.setBookStatus(rs.getString("bookStatus"));
                break;
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public boolean userBuyBook(int bookID, int userID, String firstTime, String deadline) throws SQLException {

        String Query = "INSERT INTO booksanduser " + "(bookID,userID,firstTime,deadline) VALUES" + "(?,?,?,?)";
        boolean key = false;

        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setInt(1, bookID);
            preparedStatement.setInt(2, userID);
            preparedStatement.setString(3,firstTime);
            preparedStatement.setString(4, deadline);
            preparedStatement.executeUpdate();
            key = true ;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return key;
    }

    public ArrayList<BooksAndUser> getUserBooksList(int selUserID) throws SQLException{
        ArrayList<BooksAndUser> list = new ArrayList<>();
        BooksAndUser obj;


        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM booksanduser WHERE userID="+selUserID);
            while(rs.next()) {
                obj = new BooksAndUser(rs.getInt("bookID"),rs.getInt("userID"),rs.getString("firstTime"),rs.getString("deadline"));
                list.add(obj);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public BooksAndUser delayTheBook(int userID, int bookID) { // ertelemek icin gereken userID ve bookID sine gore olan tarihleri donduruyoruz. GUI de bu tarihler uzerinden 1 ay erteliyoruz
        BooksAndUser obj = new BooksAndUser();
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM booksanduser WHERE userID=" + userID + " AND bookID="+bookID);

            while(rs.next()) {
                obj.setBookID(rs.getInt("bookID"));
                obj.setUserID(rs.getInt("userID"));
                obj.setFirstDate(rs.getString("firstTime"));
                obj.setDeadline(rs.getString("deadline"));
                break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public boolean updateDeadline(int bookID,int userID, String deadline) {

        String Query = "UPDATE booksanduser SET deadline = ? WHERE userID= ? AND bookID = ?";
        boolean key = false;

        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setString(1,deadline);
            preparedStatement.setInt(2, userID);
            preparedStatement.setInt(3, bookID);
            preparedStatement.executeUpdate();
            key = true;

        }catch(Exception e) {
            e.printStackTrace();
        }

        return key;
    }

    public boolean receiveTheBook(int userID, int bookID) {

        String Query = "UPDATE books SET bookStatus= ? WHERE id =? ";
        String Query2 = "DELETE FROM booksanduser WHERE bookID = ? AND userID=?";
        boolean key = false ;
        boolean key2=false;

        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setString(1, "1");
            preparedStatement.setInt(2,bookID);
            preparedStatement.executeUpdate();
            key=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(Query2);
            preparedStatement.setInt(1, bookID);
            preparedStatement.setInt(2, userID);
            preparedStatement.executeUpdate();
            key2=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(key2 && key)
            return true;
        else
            return false;
    }


}

