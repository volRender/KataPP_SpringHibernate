package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> listCars() {
        TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    @Override
    public List<User> findUsersByModelSeries(String model, int series) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("FROM User u WHERE u.car.model =:model AND u.car.series =:series");
        query.setParameter("model", model);
        query.setParameter("series", series);
        return (List<User>) query.getResultList();
    }

}
