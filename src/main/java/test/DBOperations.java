package test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import beans.User;
import connection.DBConnection;

public class DBOperations {
    public static void main(String[] args) {

    }

    public static void checkConnection(int userId, String name, String lastName, String email, String password,
            String phoneNumber, char userType) {
        ResultSet result = null;
        DBConnection conn = new DBConnection();
        String sql = "SELECT table_name FROM information_schema. TABLES;";
        try {
            Statement statement = conn.getConnection().createStatement();
            result = statement.executeQuery(sql);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            conn.turnOff();
        }
    }

}
