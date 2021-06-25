package model;

import java.sql.SQLException;

//interface untuk penggunaaan user
public interface Auth {
    boolean login(String username, String password);
    void register(String username, String password, String name, String address) throws SQLException;
    void delete(int userId) throws SQLException;
    void update(int userId, String nPassword) throws SQLException;
}

