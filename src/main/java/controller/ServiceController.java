package controller;

import beans.dao.ServiceDao;
import beans.vo.ServiceProviderVo;
import beans.vo.ServiceVo;
import com.google.gson.Gson;
import connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceController {

    private Connection conn;

    public ServiceController() {
        try {
            DBConnection dbConn = new DBConnection();
            this.conn = dbConn.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String listServices() {
        Gson gson = new Gson();
        ResultSet result = null;
        List<String> services = new ArrayList<String>();

        try {
            result = ServiceDao.ServiceList(this.conn);

            while (result.next()) {

                int serviceId = result.getInt("id_servicio");
                int userId = result.getInt("user_id");
                String username = result.getString("nombre_usuario");
                String userLastname = result.getString("apellido_usuario");
                String serviceName = result.getString("nombre_servicio");
                String city = result.getString("ciudad");
                Double value = result.getDouble("valor");

                ServiceProviderVo servProv = new ServiceProviderVo(userId, username, userLastname);
                
                ServiceVo service = new ServiceVo(serviceId, serviceName, servProv, city, value);
                String serviceJson  = gson.toJson(service);
                //String servResult = "{\"id\":" + serviceId + ", \"title\":" + serviceName + ", \"value\":" + value + ", \"city\":" + city + " \"user\":" + servProvJson + "}";
                //String servResult = "{"+ service + " \"user\":" + servProvJson + "}";

                services.add(serviceJson);
            }
            
            return "{services: ["+String.join(",", services)+"]}";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "{services: []}";

    }

}
