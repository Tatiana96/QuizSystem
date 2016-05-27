package hello.service;

import hello.entity.User;

/**
 * Created by Tatiana on 22.05.2016.
 */
public class LogicService {


    public User Authorize(String login, String password) {

        // 0 - значение по умолчанию
        // 1 - успешная авторизация
        // 2 - пользователь не существует
        // 3 - неверный пароль

        User UserInstance = new UserService().SelectUser(login); // получаем данные о пользователе по его логину
        //String pass = UserInstance.getPassword().toString();
        //System.out.println(pass);

        if (UserInstance == null) {// Пользователь не найден
            UserInstance = new User();
            UserInstance.Authorisation = 2;
            return UserInstance;
        }
        if (UserInstance.getPassword().equals(password)) // сравниваем пароли
            UserInstance.Authorisation = 1;
        else
            UserInstance.Authorisation = 3;

        return UserInstance;
    }
}
