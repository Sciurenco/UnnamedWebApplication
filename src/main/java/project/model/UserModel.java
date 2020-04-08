package project.model;

import org.springframework.stereotype.Component;

@Component
public class UserModel {
    private String userLogin;
    private String userPassword;
    private String userName;
    private String userSurname;
    private String userCountry;
    private String userCity;

    public UserModel(){}

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    @Override
    public String toString() {
        return
                " userLogin: " + userLogin + '\'' +
                        " userPassword: " + userPassword + '\'' +
                        " userName: " + userName + '\'' +
                        " userSurname: " + userSurname + '\'' +
                        " userCountry: " + userCountry + '\'' +
                        " userCity: " + userCity ;
    }
}