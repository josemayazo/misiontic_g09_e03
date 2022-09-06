package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    final String databaseName = "railway";
    final String mySqlHost = "containers-us-west-31.railway.app";
    final String port = "5485";
    final String user = "root";
    final String password = "LOfqH9RMOiy9szODNGYY";
    Connection connection;

    public DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // Local db
            // String url = String.format("jdbc:mysql://localhost:%s/%s", this.port,
            // this.databaseName);

            // Remote db
            String url = String.format("jdbc:mysql://%s:%s/%s", this.mySqlHost, this.port, this.databaseName);

            connection = DriverManager.getConnection(url, this.user, this.password);
            System.out.println("***** Connection: Up! ******");
        } catch (Exception e) {
            System.out.println("***** Connection: Error! ******");

        }
    }

    public Connection getConnection() {

        return this.connection;
    }

    public void turnOff() {
        connection = null;
    }
}
