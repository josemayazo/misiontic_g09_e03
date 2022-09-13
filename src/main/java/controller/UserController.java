package controller;

import connection.DBConnection;

public class UserController implements UserInterface {

    @Override
    public String login(String email, String password) {
        // Gson gson = new Gson();
        DBConnection dbConn = new DBConnection();
        return null;
    }
}
