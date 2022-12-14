package controller;

import connection.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import beans.dao.UserDao;
import beans.vo.UserVo;
import java.io.Reader;
import java.sql.ResultSetMetaData;

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
                String type = result.getString("tipo_usuario");
                UserVo user = new UserVo(id, name, lastName, type.charAt(0));

                String resultString = gson.toJson(user);
                System.out.println(resultString);
                return "{\"exists\":true, \"user\":" + resultString + "}";
            }
        } catch (SQLException e) {
    
            // return "{\"exists\":0, \"error\": \"Ha ocurrido un error:\"" + e + "}";
            e.printStackTrace();
        }
        return "{\"exists\":0}";
    }

    @Override
    public String register(String name, String lastname, String email,
            String password, String phoneNumber, char userType) {

        Gson gson = new Gson();
        ResultSet result = null;
        try {
            result = UserDao.register(this.conn, name, lastname, email, password, phoneNumber, userType);
            ResultSetMetaData resultMetadata = result.getMetaData();


            if (resultMetadata.getColumnCount() == 1) {
                return "{\"result\": \"user already exists\"}";
            }

            while (result.next()) {
                System.out.println(result.getMetaData().getColumnName(1));

                int _id = result.getInt("user_id");
                String _name = result.getString("nombre");
                String _lastname = result.getString("apellido");
                String _email = result.getString("email");
                String _phoneNumber = result.getString("telefono");
                char _userType = result.getString("tipo_usuario").charAt(0);

                UserVo registeredUser = new UserVo(_id, _name, _lastname, _email, _phoneNumber, _userType);
                String resString = gson.toJson(registeredUser);
                return "{\"registered\": true, \"user\":" + resString + "}";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"registered\": false, \"error\": true}";
    }
}
