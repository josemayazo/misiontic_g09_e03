package beans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDao {

    public static ResultSet ServiceList(Connection conn) {
        ResultSet result = null;

        try {
            String sql = "SELECT servicio.id_servicio, user.user_id, user.nombre as nombre_usuario, "
                    + "user.apellido as apellido_usuario, servicio.nombre_servicio, servicio.ciudad, "
                    + "servicio.valor\n"
                    + "FROM servi.servicio\n"
                    + "LEFT JOIN servi.user\n"
                    + "ON servicio.user_id = user.user_id";
            Statement stat = conn.createStatement();
            result = stat.executeQuery(sql);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
