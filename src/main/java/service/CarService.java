package service;

import DAO.CarDao;
import model.Car;

import java.util.List;

public class CarService {

    private CarDao carDao;

    public CarService() {
        carDao = new CarDao();
    }

    public List<Car> getAllCars() {
        return carDao.getAllCar();
    }

    public Car buyCar(String brand, String model, String licensePlate) {
        Car car = carDao.buyCar(brand, model, licensePlate);
        carDao.deleteCar(car);
        return car;
    }

    public boolean addCar(Car car) {
        List<Car> carList = getAllCars();
        long count = carList.stream()
                .filter(existCar -> car.getBrand().equals(existCar.getBrand()))
                .count();
        if (count < 10) {
            carDao.addCar(car);
            return true;
        } else {
            return false;
        }
    }

    public void clearCarList() {
        carDao.clearCarList();
    }
}
