package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Tatiana on 14.05.2016.
 */
@EnableAutoConfiguration(exclude=org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class)
@Configuration
@ComponentScan

public class Application {

    public static void main(String[] args) {

        /*System.out.println("Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        User user = new User();

        user.setLogin("JustUser");
        user.setPassword("123");
        user.setIsadmin(0);
        user.setEmail("JustUser@mail.ru");
        user.setFirstname("John");
        user.setSecondname("Smith");
        user.setBalance(0.0);

        session.save(user);
        session.getTransaction().commit();
        */
        /*int Auth = new LogicService().Authorize("blabla", "555");
        switch(Auth) {
            case 1:
                System.out.println("\nSuccess");
                break;
            case 2:
                System.out.println("\nUser doesn't exist");
                break;
            case 3:
                System.out.println("\nWrong Password");
                break;
        }*/

        SpringApplication.run(Application.class, args);
    }
}
