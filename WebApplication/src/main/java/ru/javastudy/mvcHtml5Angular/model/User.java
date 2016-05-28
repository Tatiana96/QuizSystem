package ru.javastudy.mvcHtml5Angular.model;

import org.springframework.stereotype.Component;


@Component
public class User {

    private String name;
    private String login;
    private String password;


    User(){}

    public User (String login, String password){
        User user=new User();
        user.setLogin(login);
        user.setPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
