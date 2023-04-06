package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserAndCarService {
    // методы для users
    void add(User user);
    List<User> listUsers();
    Car findCarById(long id);

    // методы для cars
    void add(Car car);
    List<Car> listCars();
}
