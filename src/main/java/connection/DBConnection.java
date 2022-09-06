package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    String databaseName;
    String mySqlHost;
    String port;
    String user;
    String password;
    Connection connection;

    public DBConnection() {

        Properties props = new Properties();
        try {
            props.load(DBConnection.class.getResourceAsStream("/db.properties"));

            this.databaseName = (String) props.get("MYSQLDATABASE");
            this.mySqlHost = (String) props.get("MYSQLHOST");
            this.port = (String) props.get("MYSQLPORT");
            this.user = (String) props.get("MYSQLUSER");
            this.password = (String) props.get("MYSQLPASSWORD");

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
            System.out.println(e);
            System.out.println("*******************************");
        }
    }

    public Connection getConnection() {

        return this.connection;
    }

    public void turnOff() {
        connection = null;
    }
}
