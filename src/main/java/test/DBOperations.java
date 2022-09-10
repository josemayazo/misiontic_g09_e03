package test;

import beans.vo.ServiceProviderVo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import connection.DBConnection;
import beans.vo.ServiceVo;

class DBOperations {

    public static void main(String[] args) {
        checkConnection();
    }

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
    
    public static void actualizarServicio(int id,  String name, String description){
        DBConnection conn = new DBConnection();
        String sql ="UPDATE servicio SET nombre_servicio='" + name + "'WHERE id_servicio="+id;
        try {
            Statement st = conn.getConnection().createStatement();
            st.executeUpdate(sql);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            conn.turnOff();
        }
    }

    public static void listarServicio(){
        DBConnection conn = new DBConnection();
        String sql ="SELECT * FROM servicio";
        try {
            Statement st = conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id=rs.getInt("id_servicio");
                ServiceProviderVo serviceProvider = (ServiceProviderVo) rs.getObject(sql);
                String name= rs.getString("nombre_servicio");
                String description =rs.getString("descripcion");
                String category=rs.getString("categoria");
                String phoneNumer =rs.getString("telefono");
                String city=rs.getString("ciudad");
                String address=rs.getString("direccion");
                Double value= rs.getDouble("valor");
                ServiceVo servicios = new ServiceVo(id,serviceProvider, name, description,category,phoneNumer, city, address, value);
                System.out.println(servicios.toString());
            }
            st.executeQuery(sql);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            conn.turnOff();

    }
}}
