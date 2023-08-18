package Model;
import Helper.DBConnection;
public class User {

    protected int id;
    protected String kullaniciAdi;
    protected String name;
    protected String password;
    protected String type;
    protected String tc;
    protected String telefonNo;

    DBConnection conn = new DBConnection();

    public User() {

    }

    public User(int id, String kullaniciAdi, String name, String password, String type,String tc,String telefonNo) {
        this.id = id;
        this.kullaniciAdi = kullaniciAdi;
        this.name = name;
        this.password = password;
        this.type = type;
        this.tc = tc;
        this.telefonNo = telefonNo;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getKullaniciAdi() {
        return kullaniciAdi;
    }
    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTc() {
        return tc;
    }
    public void setTc(String tc) {
        this.tc = tc;
    }
    public String getTelefonNo() {
        return telefonNo;
    }
    public void setTelefonNo(String telefonNo) {
        this.telefonNo = telefonNo;
    }
}
