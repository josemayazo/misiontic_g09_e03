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
}
