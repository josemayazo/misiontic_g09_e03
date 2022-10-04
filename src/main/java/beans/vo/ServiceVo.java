package beans.vo;

public class ServiceVo {

    int id;
    ServiceProviderVo serviceProvider;
    String name;
    String description;
    String category;
    String phoneNumer;
    String city;
    String address;
    Double value;

    public ServiceVo(int id) {
        this.id = id;
    }

    public ServiceVo(int id, ServiceProviderVo serviceProvider, String name, String description, String category,
            String phoneNumer,
            String city, String address, Double value) {
        this.id = id;
        this.serviceProvider = serviceProvider;
        this.name = name;
        this.description = description;
        this.category = category;
        this.phoneNumer = phoneNumer;
        this.city = city;
        this.address = address;
        this.value = value;
    }

    public ServiceVo(int id, String title, ServiceProviderVo serviceProvider, String city, Double value, String description) {
        this.id = id;
        this.serviceProvider = serviceProvider;
        this.name = title;
        this.city = city;
        this.value = value;
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhoneNumer() {
        return phoneNumer;
    }

    public void setPhoneNumer(String phoneNumer) {
        this.phoneNumer = phoneNumer;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public int getServiceProviderId() {
        return this.serviceProvider.getId();
    }

    @Override
    public String toString() {

        String object = String.format(
                "ServiceVo {id: %s, serviceProviderId: %s, name: %s, description: %s, category: %s, phoneNumer: %s, city: %s, address: %s, value: %s}",
                this.id, this.serviceProvider.getId(), this.name, this.description, this.category, this.phoneNumer,
                this.city, this.address, this.value);
        return object;
    }

}
