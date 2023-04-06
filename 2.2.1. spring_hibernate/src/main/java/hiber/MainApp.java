package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserAndCarService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserAndCarService userService = context.getBean(UserAndCarService.class);

      userService.add(new Car("Car1", 1));
      userService.add(new Car("Car2", 2));
      userService.add(new Car("Car3", 3));
      userService.add(new Car("Car4", 4));

      List<Car> cars = userService.listCars();
      for (Car car : cars) {
         System.out.println("Id = "+car.getId());
         System.out.println("Model = "+car.getModel());
         System.out.println("Series = "+car.getSeries());
         System.out.println();
      }

      userService.add(new User("User1", "Lastname1", "user1@mail.ru",
              userService.findCarById(2)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru",
              userService.findCarById(2)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru",
              userService.findCarById(1)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru",
              userService.findCarById(4)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }
      context.close();
   }
}
