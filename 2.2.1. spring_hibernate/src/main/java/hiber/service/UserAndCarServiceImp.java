package hiber.service;

import hiber.dao.CarDao;
import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserAndCarServiceImp implements UserAndCarService {
   @Autowired
   private UserDao userDao;

   @Autowired
   private CarDao carDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional
   @Override
   public Car findCarById(long id) {
      return carDao.findCarById(id);
   }

   @Transactional
   @Override
   public List<User> findUsersByModelSeries(String model, int series) {
      return userDao.findUsersByModelSeries(model, series);
   }

   @Transactional
   @Override
   public void add(Car car) {
      carDao.add(car);
   }

   @Transactional
   @Override
   public List<Car> listCars() {
      return carDao.listCars();
   }

}
