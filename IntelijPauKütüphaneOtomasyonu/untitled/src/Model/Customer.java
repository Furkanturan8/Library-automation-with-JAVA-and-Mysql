package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Customer extends User{

    Connection con = conn.connDb();
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement preparedStatement = null;
    public Customer() {	}

    public Customer(int id, String kullaniciAdi, String name, String password, String type, String tc,String telefonNo) {
        this.id = id;
        this.kullaniciAdi = kullaniciAdi;
        this.name = name;
        this.password = password;
        this.type = type;
        this.tc = tc;
        this.telefonNo = telefonNo;
    }


    public User getUser(String tc) {
        User obj = new User();
        try {

            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM user WHERE type='kullanici' AND tc="+tc);
            while(rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setKullaniciAdi(rs.getString("kullaniciAdi"));
                obj.setName(rs.getString("name"));
                obj.setPassword(rs.getString("password"));
                obj.setTc(rs.getString("tc"));
                obj.setTelefonNo(rs.getString("telefonNo"));
                break;
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return obj;
    }



    public ArrayList<User> getCustomerList() throws SQLException{
        ArrayList<User> list = new ArrayList<>();
        User obj;

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM user WHERE type = 'kullanici' ");
            while(rs.next()) {
                obj = new User(rs.getInt("id"),rs.getString("kullaniciAdi"),rs.getString("name"),rs.getString("password"),rs.getString("type"),rs.getString("tc"),rs.getString("telefonNo"));
                list.add(obj);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean addCustomer(String kullaniciAdi, String name, String password, String tc, String telefonNo) throws SQLException {

        String Query = "INSERT INTO user " + "(kullaniciAdi,password,name,type,tc,telefonNo) VALUES" + "(?,?,?,?,?,?)";
        boolean key = false; // true mu false mu old. anlamak icin

        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setString(1, kullaniciAdi);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, "kullanici"); // bunlar yukaridaki ? yerine gelecek ifadeler
            preparedStatement.setString(5, tc);
            preparedStatement.setString(6, telefonNo);
            preparedStatement.executeUpdate();
            key = true ; //true;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        if(key)
            return true;
        else
            return false;
    }



    public boolean deleteCustomer(int id) throws SQLException {

        String Query = "DELETE FROM user WHERE id = ?";
        boolean key = false; // true mu false mu old. anlamak icin

        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setInt(1, id); // bu yukaridaki ? yerine gelecek
            preparedStatement.executeUpdate();
            key = true ; //true;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        if(key)
            return true;
        else
            return false;
    }

    public boolean updateCustomer(int id,String kullaniciAdi, String password, String name , String tc, String telefonNo) throws SQLException {

        String Query = "UPDATE user SET name = ? , kullaniciAdi = ? , password = ? , tc = ? , telefonNO = ? WHERE id = ?";
        boolean key = false; // true mu false mu old. anlamak icin

        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setString(1,name); // bu yukaridaki ? yerine gelecek
            preparedStatement.setString(2,kullaniciAdi);
            preparedStatement.setString(3,password);
            preparedStatement.setString(4,tc);
            preparedStatement.setString(5,telefonNo);
            preparedStatement.setInt(6, id);

            preparedStatement.executeUpdate();
            key = true ; //true;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        if(key)
            return true;
        else
            return false;
    }

}
