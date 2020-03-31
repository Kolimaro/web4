package DAO;

import model.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;

import java.util.List;

public class CarDao {

    private SessionFactory sessionFactory;

    public CarDao() {
        this.sessionFactory = DBHelper.getSessionFactory();
    }

    public List<Car> getAllCar() {
        Session session = sessionFactory.openSession();
        List<Car> carList = session.createQuery("From Car").list();
        session.close();
        return carList;
    }

    public Car buyCar(String brand, String model, String licensePlate) {
        Session session = sessionFactory.openSession();
        List<Car> carList = session.createQuery("From Car").list();
        session.close();
        Car car = null;
        for (Car x : carList) {
            if (x.getBrand().equals(brand) && x.getModel().equals(model) && x.getLicensePlate().equals(licensePlate)) {
                car = x;
            }
        }
        return car;
    }

    public void sellCar(Car car) {
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
        List<Car> carList = session.createQuery("From Car").list();
        Transaction transaction = session.beginTransaction();
        for (Car x : carList) {
            session.delete(x);
        }
        transaction.commit();
        session.close();
    }
}
