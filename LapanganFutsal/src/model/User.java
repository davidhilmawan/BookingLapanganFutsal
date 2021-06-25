package model;

import db.DBConnection;
import java.sql.*;
import javax.swing.JOptionPane;

public class User implements Auth {
    private final Connection con = DBConnection.getConnection();
    ResultSet rs;
    private int userId;
           
    @Override
    public  boolean login(String username, String password) {
        //untuk login
        String query = "select * from user where username=? and password=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.execute();
            rs = ps.getResultSet();
            if(!rs.next()){
               return false;
            }
            else
            {
                do {
                    this.userId = rs.getInt("user_id");
                } while(rs.next());
            }
        } catch (SQLException ex){
            System.out.println(ex);
            return false;
        }

        return true;
    }

    @Override
    public void register(String username, String password, String name, String address) throws SQLException {
        //untuk register akun
        String query = "insert into user (username, password, name, address) values(?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setString(4, address);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void delete(int userId) throws SQLException {
        //untuk delete akun
        String query = "delete from user where user_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void update(int userId, String nPassword) throws SQLException {
        //untuk update akun
        String query = "update user set password=? where user_id=?";
        try {
            System.out.printf("Difungsi update useridnya segini : %d", userId);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nPassword);
            ps.setInt(2, userId);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public int getUserId() {
        //untuk mendapatkan id akun
        return this.userId;
    }
}
