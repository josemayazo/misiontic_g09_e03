package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import connection.DBConnection;

class DBOperations {

    public static void main(String[] args) {
        checkConnection();
    }

    static void checkConnection() {
        ResultSet result = null;
        DBConnection conn = new DBConnection();
        String sql = "SELECT COUNT(*) AS COUNT FROM information_schema.TABLES;";

        try {

            Statement statement = conn.getConnection().createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {

                if (result.getInt("COUNT") > 0) {
                    System.out.println("Database Connected Succesfully!!!!");
                }

            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            conn.turnOff();
        }
    }

}
