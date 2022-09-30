package beans.dao;

import beans.vo.ServiceVo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDao {

    public static ResultSet serviceList(Connection conn) {
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

    public static ResultSet myServiceList(Connection conn, int providerId) {
        ResultSet result = null;

        try {
            String sql = "SELECT servicio.id_servicio, user.user_id, "
                    + "user.nombre as nombre_usuario, "
                    + "user.apellido as apellido_usuario, "
                    + "servicio.nombre_servicio, servicio.ciudad, servicio.valor\n"
                    + "FROM servi.servicio\n"
                    + "LEFT JOIN servi.user\n"
                    + "ON servicio.user_id = user.user_id\n"
                    + "WHERE user.user_id = ?";
            PreparedStatement preparedStat = conn.prepareStatement(sql);
            preparedStat.setInt(1, providerId);
            result = preparedStat.executeQuery();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Boolean createService(Connection conn, ServiceVo service) {
        Boolean result = false;

        try {
            String sql = "INSERT INTO servi.servicio (user_id, nombre_servicio, "
                    + "descripcion, categoria, telefono, ciudad, direccion, valor) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStat = conn.prepareStatement(sql);
            preparedStat.setInt(1, service.getServiceProviderId());
            preparedStat.setString(2, service.getName());
            preparedStat.setString(3, service.getDescription());
            preparedStat.setString(4, service.getCategory());
            preparedStat.setString(5, service.getPhoneNumer());
            preparedStat.setString(6, service.getCity());
            preparedStat.setString(7, service.getAddress());
            preparedStat.setDouble(8, service.getValue());

            int numRowsAffected = preparedStat.executeUpdate();

            result = (numRowsAffected > 0);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static Boolean editService(Connection conn, ServiceVo service) {
        Boolean result = false;
        try {
            String sql = "UPDATE servi.servicio \n"
                    + "SET nombre_servicio = ?, descripcion = ?, categoria = ?, "
                    + "telefono = ?, ciudad = ?, direccion = ?, valor = ?\n"
                    + "WHERE id_servicio = ?;";
            PreparedStatement preparedStat = conn.prepareStatement(sql);
            preparedStat.setString(1, service.getName());
            preparedStat.setString(2, service.getDescription());
            preparedStat.setString(3, service.getCategory());
            preparedStat.setString(4, service.getPhoneNumer());
            preparedStat.setString(5, service.getCity());
            preparedStat.setString(6, service.getAddress());
            preparedStat.setDouble(7, service.getValue());
            preparedStat.setInt(8, service.getId());
            
            
            int numRowsAffected = preparedStat.executeUpdate();

            result = (numRowsAffected > 0);

            return result;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Boolean deleteService(Connection conn, ServiceVo service) {
        Boolean result = false;

        try {
            String sql = "DELETE FROM servi.servicio WHERE servicio.id_servicio = ?;";
            PreparedStatement preparedStat = conn.prepareStatement(sql);
            preparedStat.setInt(1, service.getId());

            int numRowsAffected = preparedStat.executeUpdate();

            result = (numRowsAffected > 0);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    
}
