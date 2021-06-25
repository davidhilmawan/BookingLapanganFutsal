package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnection {
    private static final  String driverName = "com.mysql.cj.jdbc.Driver";
    private static final  String dbUrl = "jdbc:mysql://localhost:3306/booking_lapangan?serverTimezone=Asia/Bangkok";
    private static final String username = "root";
    private static final String password = "";
    private static Connection con;
    
    //untuk menghubungkan java ke database
    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                        con = DriverManager.getConnection(dbUrl, username, password);
            }
            catch (SQLException se)
            {
                JOptionPane.showMessageDialog(null, se);
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
        return con;
    }
}




