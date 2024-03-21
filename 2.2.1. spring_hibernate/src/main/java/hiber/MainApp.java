package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      User user1 = new User("User1", "Lastname1", "User1@gmail.com");
      User user2 = new User("User2", "Lastname2", "User2@gmail.com");
      User user3 = new User("User3", "Lastname3", "User3@gmail.com");
      User user4 = new User("User4", "Lastname4", "User4@gmail.com");

      Car car1 = new Car("Model1", 111);
      Car car2 = new Car("Model2", 222);
      Car car3 = new Car("Model3", 333);
      Car car4 = new Car("Model4", 444);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));


      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
         System.out.println("1. _____________________________________________");
      }


      System.out.println(userService.getUserByCar("Model1", 111));
      System.out.println("2. _____________________________________________");


      try {
         User notFoundUser = userService.getUserByCar("Model5", 555);
      } catch (NoResultException e) {
         System.out.println("User not found");
         System.out.println("3. _____________________________________________");
      }

      context.close();
   }
}
