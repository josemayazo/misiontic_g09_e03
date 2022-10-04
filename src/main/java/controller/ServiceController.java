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
            result = ServiceDao.serviceList(this.conn);

            while (result.next()) {

                int serviceId = result.getInt("id_servicio");
                int userId = result.getInt("user_id");
                String username = result.getString("nombre_usuario");
                String userLastname = result.getString("apellido_usuario");
                String serviceName = result.getString("nombre_servicio");
                String serviceDesc = result.getString("descripcion");
                String city = result.getString("ciudad");
                Double value = result.getDouble("valor");

                ServiceProviderVo servProv = new ServiceProviderVo(userId, username, userLastname);

                ServiceVo service = new ServiceVo(serviceId, serviceName, servProv, city, value, serviceDesc);
                String serviceJson = gson.toJson(service);
                //String servResult = "{\"id\":" + serviceId + ", \"title\":" + serviceName + ", \"value\":" + value + ", \"city\":" + city + " \"user\":" + servProvJson + "}";
                //String servResult = "{"+ service + " \"user\":" + servProvJson + "}";

                services.add(serviceJson);
            }

            return "{\"services\": [" + String.join(",", services) + "]}";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "{\"services\": []}";

    }

    public String listMyServices(int providerId) {
        Gson gson = new Gson();
        ResultSet result = null;
        List<String> services = new ArrayList<String>();

        try {
            result = ServiceDao.myServiceList(this.conn, providerId);

            while (result.next()) {

                int serviceId = result.getInt("id_servicio");
                int userId = result.getInt("user_id");
                String username = result.getString("nombre_usuario");
                String userLastname = result.getString("apellido_usuario");
                String serviceName = result.getString("nombre_servicio");
                String serviceDesc = result.getString("descripcion");
                String city = result.getString("ciudad");
                Double value = result.getDouble("valor");

                ServiceProviderVo servProv = new ServiceProviderVo(userId, username, userLastname);

                ServiceVo service = new ServiceVo(serviceId, serviceName, servProv, city, value, serviceDesc);
                String serviceJson = gson.toJson(service);
                services.add(serviceJson);
            }

            return "{\"services\": [" + String.join(",", services) + "]}";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "{\"services\": []}";

    }

    public String createService(int userId, String serviceName,
            String serviceDescripcion, String serviceCategory, String servicePhoneNumber,
            String serviceCity, String serviceAddress, Double serviceValue) {

        boolean result = false;

        try {
            ServiceProviderVo servProv = new ServiceProviderVo(userId, "", "");
            ServiceVo service = new ServiceVo(userId, servProv, serviceName, serviceDescripcion, serviceCategory, servicePhoneNumber, serviceCity, serviceAddress, serviceValue);
            result = ServiceDao.createService(conn, service);
            return "{\"inserted\": " + result + "}";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "{\"inserted\": " + result + "}";
    }

    public String deleteService(int serviceId) {

        boolean result = false;

        try {
            ServiceVo service = new ServiceVo(serviceId);

            result = ServiceDao.deleteService(this.conn, service);
            return "{\"deleted\": " + result + "}";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "{\"deleted\": " + result + "}";
    }

    public String editService(int serviceId, String serviceName, String serviceDescripcion,
            String serviceCategory, String servicePhoneNumber,
            String serviceCity, String serviceAddress, Double serviceValue) {
        
        boolean result = false;
        ServiceProviderVo serviceProv = new ServiceProviderVo(0, "", "");
        try {
            ServiceVo service = new ServiceVo(serviceId, serviceProv, serviceName, 
                    serviceDescripcion, serviceCategory, servicePhoneNumber, 
                    serviceCity, serviceAddress, serviceValue);
            result = ServiceDao.editService(conn, service);
            
            return "{\"updated\": " + result + "}";

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "{\"updated\": " + result + "}";

    }

}
