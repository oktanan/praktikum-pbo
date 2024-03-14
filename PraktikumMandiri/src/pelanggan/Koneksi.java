
package pelanggan;

import java.sql.Connection;
import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
         private Connection koneksi;
    public Connection getConnection() throws SQLException{
        if (koneksi==null){
            new Driver();
            koneksi = DriverManager.getConnection("jdbc:mysql://localhost/pbo_pelanggan","root","");
            }
        return koneksi;
    }
    
}
