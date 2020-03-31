package DAO;

import model.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.DBHelper;

import java.util.List;

public class CarDao {

    private SessionFactory sessionFactory;

    public CarDao() {
        this.sessionFactory = DBHelper.getSessionFactory();
    }

    public List<Car> getAllCar() {
        Session session = sessionFactory.openSession();
        Query<Car> query = session.createQuery("SELECT c FROM Car c", Car.class);
        List<Car> carList = query.getResultList();
        session.close();
        return carList;
    }

    public Car buyCar(String brand, String model, String licensePlate) {
        Session session = sessionFactory.openSession();
        Query<Car> query = session.createQuery("SELECT c FROM Car c WHERE (c.brand = :car_brand AND c.model = :car_model AND c.licensePlate = :car_licensePlate)", Car.class);
        query.setParameter("car_brand", brand);
        query.setParameter("car_model", model);
        query.setParameter("car_licensePlate", licensePlate);
        Car car = query.getSingleResult();
        session.close();
        return car;
    }

    public void deleteCar(Car car) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(car);
        transaction.commit();
        session.close();
    }

    public void addCar(Car car) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(car);
        transaction.commit();
        session.close();
    }

    public void clearCarList() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM Car").executeUpdate();
        transaction.commit();
        session.close();
    }
}
