package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserAndCarService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserAndCarService userAndCarService = context.getBean(UserAndCarService.class);

      // создание машин
      System.out.println("Creating cars");
      System.out.println();
      userAndCarService.add(new Car("Car1", 1));
      userAndCarService.add(new Car("Car2", 2));
      userAndCarService.add(new Car("Car3", 3));
      userAndCarService.add(new Car("Car4", 4));

      // вывод машин
      List<Car> cars = userAndCarService.listCars();
      for (Car car : cars) {
         System.out.println("Id = "+car.getId());
         System.out.println("Model = "+car.getModel());
         System.out.println("Series = "+car.getSeries());
         System.out.println();
      }

      // создание пользователей
      System.out.println("Creating users");
      System.out.println();
      userAndCarService.add(new User("User1", "Lastname1", "user1@mail.ru",
              userAndCarService.findCarById(2)));
      userAndCarService.add(new User("User2", "Lastname2", "user2@mail.ru",
              userAndCarService.findCarById(2)));
      userAndCarService.add(new User("User3", "Lastname3", "user3@mail.ru",
              userAndCarService.findCarById(1)));
      userAndCarService.add(new User("User4", "Lastname4", "user4@mail.ru",
              userAndCarService.findCarById(4)));

      List<User> users = userAndCarService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      // фильтрация пользователей по модели и серии
      System.out.println("Output filtered model and series users");
      System.out.println();
      List<User> filtredUsers = userAndCarService.findUsersByModelSeries("Car2", 2);
      for(User user : filtredUsers) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      context.close();
   }
}
