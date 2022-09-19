package controller;

import connection.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import beans.dao.UserDao;
import beans.vo.UserVo;

public class UserController implements UserInterface {

    private Connection conn;

    public UserController() {
        try {
            DBConnection dbConn = new DBConnection();
            this.conn = dbConn.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String login(String email, String password) {
        Gson gson = new Gson();
        ResultSet result = null;

        try {
            result = UserDao.login(this.conn, email, password);

            while (result.next()) {
                int id = result.getInt("user_id");
                String name = result.getString("nombre");
                String lastName = result.getString("apellido");

                UserVo user = new UserVo(id, name, lastName);
                return gson.toJson(user);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
