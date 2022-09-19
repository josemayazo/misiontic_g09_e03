package test;

import beans.vo.ServiceProviderVo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import connection.DBConnection;
import beans.vo.ServiceVo;

class DBOperations {
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

    public  void actualizarServicio(String description,String category ,int id  ) {
        
        DBConnection conn = new DBConnection();
        String sql = "UPDATE servicio SET descripcion=? , categoria=?  WHERE id_servicio=? ";
        try {
            PreparedStatement st = conn.getConnection().prepareStatement(sql);
            st.setString(1,description );
            st.setString(2,category);
            st.setInt(3,id);
            st.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            conn.turnOff();
        }
    }

    public void listarServicio() {
        ResultSet result = null;
        DBConnection conn = new DBConnection();
        // String sql = "SELECT * FROM servi.servicio;";
        String sql = "SELECT servicio.* , user.user_id, user.nombre, user.apellido FROM servi.servicio JOIN servi.user ON servicio.user_id = user.user_id;";
        try {
            Statement st = conn.getConnection().createStatement();
            result = st.executeQuery(sql);
            while (result.next()) {
                int id = result.getInt("id_servicio");
                // result.getObject(sql);
                int idSericeProvider = result.getInt("user_id");
                String serviceProviderName = result.getString("nombre");
                String serviceProviderLastname = result.getString("apellido");
                String name = result.getString("nombre_servicio");
                String description = result.getString("descripcion");
                String category = result.getString("categoria");
                String phoneNumer = result.getString("telefono");
                String city = result.getString("ciudad");
                String address = result.getString("direccion");
                Double value = result.getDouble("valor");
                ServiceVo servicios = new ServiceVo(id,
                        new ServiceProviderVo(idSericeProvider, serviceProviderName, serviceProviderLastname), name,
                        description,
                        category, phoneNumer, city,
                        address, value);
                System.out.println(servicios.toString());
            }
            st.executeQuery(sql);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            conn.turnOff();

        }
    }

    ServiceProviderVo getServiceProviderVo(int userID) {
        ServiceProviderVo serviceProvider = new ServiceProviderVo(userID, "", "");
        ResultSet result = null;
        DBConnection conn = new DBConnection();
        String sql = "SELECT * FROM servi.user WHERE user_id = ?;";

        try {

            PreparedStatement statement = conn.getConnection().prepareStatement(sql);
            statement.setInt(1, userID);
            result = statement.executeQuery();

            while (result.next()) {

                serviceProvider.setName(result.getString("nombre"));
                serviceProvider.setLastname(result.getString("apellido"));

                return serviceProvider;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            conn.turnOff();
        }

        return serviceProvider;
    }

}
