package beans.vo;

import java.util.ArrayList;

public class ServiceProviderVo extends UserVo {
    ArrayList<ServiceVo> serviceList = new ArrayList<>();

    public ServiceProviderVo(int id, String name, String lastName, String email, String password, String phoneNumber,
            char type, ArrayList<ServiceVo> serviceList) {
        super(id, name, lastName, email, password, phoneNumber, type);
        this.serviceList = serviceList;
    }

    public ServiceProviderVo(int id, String name, String lastName, String email, String password, String phoneNumber,
            char type) {
        super(id, name, lastName, email, password, phoneNumber, type);
    }

    public void createService(ServiceVo newService) {
        this.serviceList.add(newService);
    }

    public ServiceVo getService(int serviceId) {
        for (ServiceVo service : serviceList) {
            if (service.id == serviceId) {
                return service;
            }
        }
        return null;
    }

    public void editService(int serviceId, String name, String description, String category, String phoneNumber,
            String city, String address, Double value) {
        for (ServiceVo service : this.serviceList) {
            if (serviceId == service.id) {
                service.name = name;
                service.description = description;
                service.category = category;
                service.phoneNumer = phoneNumber;
                service.city = city;
                service.address = address;
                service.value = value;
            }
        }
    }

}
