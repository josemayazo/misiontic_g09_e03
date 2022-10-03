package beans.vo;

public class UserVo {
    int id;
    String name;
    String lastname;
    String email;
    String password;
    String phoneNumber;
    char type;

    public UserVo(int id, String name, String lastName, String email, String password, String phoneNumber, char type) {
        this.id = id;
        this.name = name;
        this.lastname = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    public UserVo(int id, String name, String lastName, String email, String phoneNumber, char type) {
        this.id = id;
        this.name = name;
        this.lastname = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    public UserVo(int id, String name, String lastName, String email) {
        this.id = id;
        this.name = name;
        this.lastname = lastName;
        this.email = email;
    }

    public UserVo(int id, String name, String lastName, char  type) {
        this.id = id;
        this.name = name;
        this.lastname = lastName;
        this.type = type;
    }
    public UserVo(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastname = lastName;
    //    this.type = type;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

}
