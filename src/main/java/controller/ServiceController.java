
package controller;
import beans.vo.ServiceProviderVo;
import connection.DBConnection;

import java.sql.Connection;

import com.google.gson.Gson;

import beans.vo.ServiceVo;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceController implements ServiceInterface{
    
    private Connection conn;
    
    public ServiceController() {
        try {
            DBConnection dbConn = new DBConnection();
            this.conn = dbConn.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String create(int id, ServiceProviderVo serviceProvider,String name, String description, String category,
            String phoneNumer, String city, String  address , Double value) {

        Gson gson = new Gson();
      

        DBConnection con = new DBConnection();
        String sql = "Insert into servicio values('" + id + "','" + serviceProvider + "','" + name + "', '" + description + "', '" + category
                + "', '" + phoneNumer + "', '" + city + "', " + address + ", " + value + ")";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            ServiceVo servicio = new ServiceVo(id,serviceProvider,name, description, category, phoneNumer, city, address, value);

            st.close();

            return gson.toJson(servicio);
        } catch (SQLException e) {
        }
        return "";
    }
    
    @Override
    public String update(int id, String newname, String newdescription, String newcategory,
            String newphoneNumer, String newcity, String  newaddress , Double newvalue) {

        DBConnection con = new DBConnection();

        String sql = "Update servicio set nombre_servicio = '" + newname
                + "', descripcion = '" + newdescription + "', "
                + "categoria = '" + newcategory + "', telefono = '"
                + newphoneNumer + "', ciudad = '" + newcity + "', " + "direccion = '" + 
                newaddress + "', valor = " + newvalue;


        sql += " where id_servicio = '" + id + "'";

        try {

            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (SQLException e) {
        }
        return "";

    }
    
    @Override
    public String delete(String id) {

        DBConnection con = new DBConnection();

        
        String sql2 = "Delete from servicio where id_servicio = '" + id + "'";

        try {
            Statement st = con.getConnection().createStatement();
            
            st.executeUpdate(sql2);

            return "true";
        } catch (SQLException e) {
        }
        return "";
    }
}
