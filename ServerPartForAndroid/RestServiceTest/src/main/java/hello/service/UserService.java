package hello.service;

import hello.entity.User;
import hello.persistence.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 * Created by Tatiana on 21.05.2016.
 */

@Component
public class UserService {
    public User SelectUser(String login){ // получаем данные о пользователе из БД по его логину

        System.out.println ("\nSelect user in process...");

        Session session = HibernateUtil.getSessionFactory().openSession();

        // Retrieve existing person first
        Criteria UserCriteria = session.createCriteria(User.class);
        UserCriteria.add(Restrictions.eq("login", login));
        User UserInstance = (User) UserCriteria.uniqueResult();

        //System.out.println (UserInstance.getLogin());

        session.close();

        return UserInstance;
    }
}
