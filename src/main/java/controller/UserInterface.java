package controller;

public interface UserInterface {
    public String login(String email, String password);
    
    public String register(String name, String lastname, String email,
            String password, String phoneNumber, char userType);
}
