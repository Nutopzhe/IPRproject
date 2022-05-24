package api.dao.impl;

import api.dao.CarDAO;
import api.entity.Car;
import api.utils.HibernateUtil;
import org.hibernate.Session;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO {
    //метод получения всех машин из БД
    @Override
    public List<Car> getAllCars() {
        List cars = new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            //получить все машины и заполнить список cars
            CriteriaQuery<Car> criteriaQuery = session.getCriteriaBuilder().createQuery(Car.class);
            criteriaQuery.from(Car.class);

            //SQL запрос на получение машины (объединение двух таблиц person и engine_type)
            cars = session.createNativeQuery("select car.id, mark, model, price, type_name from car \n" +
                    "join engine_type \n" +
                    "\ton car.engine_type_id = engine_type.id \n" +
                    "where car.engine_type_id = engine_type.id;")
                    .addEntity(Car.class)
                    .list();

            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Ошибка при получении всех машин!");
        }
        return cars;
    }

    //метод получение машины по id из БД
    @Override
    public Car getCarById(Integer carId) {
        Car car = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            //получить машину по Id
            car = session.load(Car.class, carId);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Ошибка при получении машины по Id!");
        }
        return car;
    }

    //метод добавления машины в БД
    @Override
    public void addCar(Car car) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            //добавить машину в таблицу
            session.save(car);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Ошибка при вставке новой машины!");
        }
    }
}
