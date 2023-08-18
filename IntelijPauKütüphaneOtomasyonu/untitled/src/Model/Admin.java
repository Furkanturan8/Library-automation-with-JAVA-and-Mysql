package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Admin extends User{
    Connection con = conn.connDb();
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement preparedStatement = null;

    public Admin() {}
    public Admin(int id, String kullaniciAdi, String name, String password, String type, String tc, String telefonNo) {
        this.id = id;
        this.kullaniciAdi = kullaniciAdi;
        this.name = name;
        this.password = password;
        this.type = type;
        this.tc = tc;
        this.telefonNo = telefonNo;
    }

    public User getUser(int id) {
        User obj = new User();
        try {

            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM user WHERE type='admin' AND id="+id);
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

    public ArrayList<User> getAdminList() throws SQLException{
        ArrayList<User> list = new ArrayList<>();
        User obj;

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM user WHERE type = 'admin' ");
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

    //OLMADI GUNCELLEME İSLEMİNİ YAPMADA KALDIK
    public Admin getSelectedAdmin(int id){

        Admin selAdmin = new Admin();

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM user WHERE type = 'admin' AND id="+id);
            while(rs.next()) {

                selAdmin.setKullaniciAdi(rs.getString("kullaniciAdi"));
                selAdmin.setName(rs.getString("name"));
                selAdmin.setPassword(rs.getString("password"));
                selAdmin.setTc(rs.getString("tc"));
                selAdmin.setTelefonNo(rs.getString("telefonNo"));
                break;
            }


        }catch(Exception e){
            e.printStackTrace();
        }

        return selAdmin;
    }


    public boolean addAdmin(String kullaniciAdi, String name, String password,String tc,String telefonNo) throws SQLException {

        String Query = "INSERT INTO user " + "(kullaniciAdi,password,name,type,tc,telefonNo) VALUES" + "(?,?,?,?,?,?)";
        boolean key = false; // true mu false mu old. anlamak icin

        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setString(1, kullaniciAdi);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, "admin"); // bunlar yukaridaki ? yerine gelecek ifadeler
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


    public boolean deleteAdmin(int id,String kullaniciAdi) throws SQLException {

        String Query = "DELETE FROM user WHERE kullaniciAdi = ? AND type= ? AND id = ? ";
        boolean key = false; // true mu false mu old. anlamak icin

        try {

            st = con.createStatement();
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setString(1,kullaniciAdi);
            preparedStatement.setString(2, "admin");
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();


            key = true ; //true; kullanici adi ve id eslesiyorsa sil

        }
        catch(Exception e){
            e.printStackTrace();
        }

        if(key)
            return true;
        else
            return false;
    }

    public boolean updateAdmin(int id,String kullaniciAdi, String password, String name ,String tc,String telefonNo) throws SQLException {

        String Query = "UPDATE user SET name = ? , kullaniciAdi = ? , password = ? ,tc = ? , telefonNo = ?  WHERE id = ?";
        boolean key = false; // true mu false mu old. anlamak icin

        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setString(1,name); // bu yukaridaki ? yerine gelecek
            preparedStatement.setString(2,kullaniciAdi);
            preparedStatement.setString(3,password);
            preparedStatement.setString(4, tc);
            preparedStatement.setString(5, telefonNo);
            preparedStatement.setInt(6,id);

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




