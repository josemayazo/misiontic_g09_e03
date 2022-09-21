package beans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public static ResultSet login(Connection conn, String email, String password) {
        ResultSet result = null;

        try {
            String sql = "SELECT * FROM servi.user WHERE user.email = ? AND user.password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            result = preparedStatement.executeQuery();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public static ResultSet register(Connection conn, String name,
            String lastname, String email, String password, String phoneNumber,
            char userType) {
        ResultSet result = null;

        try {
            /*
            Call Stored Procedure: userRegist(name, lastname, email, password, phonNumber, userType)
                Params:
                ------
                name: String
                lastname: String
                email: String
                password: String
                phoneNumber: String
                userType: Char
            */
            PreparedStatement preparedCall = conn.prepareCall("{CALL servi.userRegist(?, ?, ?, ?, ?, ?)}");
            preparedCall.setString(1, name);
            preparedCall.setString(2, lastname);
            preparedCall.setString(3, email);
            preparedCall.setString(4, password);
            preparedCall.setString(5, phoneNumber);
            preparedCall.setString(6, String.valueOf(userType));
            result = preparedCall.executeQuery();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
