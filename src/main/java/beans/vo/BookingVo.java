package beans.vo;

import java.util.Date;

public class BookingVo {
    int id;
    CustomerVo customer;
    ServiceVo service;
    Date date;
    String state;
    boolean customerCancelPetition = false;
    boolean serviceProviderCancelPetition = false;

    public BookingVo(int id, CustomerVo customer, ServiceVo service, Date date, String state) {
        this.id = id;
        this.customer = customer;
        this.service = service;
        this.date = date;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        // check if
        this.state = state;
    }

    public boolean isCustomerCancelPetition() {
        return customerCancelPetition;
    }

    public void setCustomerCancelPetition(boolean customerCancelPetition) {
        this.customerCancelPetition = customerCancelPetition;
    }

    public boolean isServiceProviderCancelPetition() {
        return serviceProviderCancelPetition;
    }

    public void setServiceProviderCancelPetition(boolean serviceProviderCancelPetition) {
        this.serviceProviderCancelPetition = serviceProviderCancelPetition;
    }

}
