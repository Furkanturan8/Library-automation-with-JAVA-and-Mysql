package Helper;

import java.sql.*;

public class DBConnection {

    Connection c = null;

    public DBConnection() {

    }
    public Connection connDb() {

        try {
            this.c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library?user=root&password=");
            return c;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return c;
    }


}