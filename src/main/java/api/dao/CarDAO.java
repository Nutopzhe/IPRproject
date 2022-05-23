package api.dao;

import api.entity.Car;
import java.util.List;

public interface CarDAO {
    List<Car> getAllCars();
    Car getCarById(Integer carId);
    void addCar(Car car);
}
