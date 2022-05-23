package api;

import api.dao.CarDAOImpl;
import api.dao.UserDAOImpl;
import api.entity.Car;
import api.utils.EngineType;
import api.entity.User;
import api.utils.HttpClientUtil;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class APITest {
    UserDAOImpl userDAO = new UserDAOImpl();
    CarDAOImpl carDAO = new CarDAOImpl();

    @Test
    @DisplayName("Получить всех users, проверить с БД")
    void getAllUsers() {
        List<User> usersFromAPI = HttpClientUtil.getAllUsers();
        List<User> usersFromDB = userDAO.getAllUsers();

        assertEquals(usersFromAPI, usersFromDB);
    }

    @Test
    @DisplayName("Получить все cars, проверить с БД")
    void getAllCarsTest() {
        List<Car> carsFromAPI = HttpClientUtil.getAllCars();
        List<Car> carsFromDB = carDAO.getAllCars();

        assertEquals(carsFromAPI, carsFromDB);
    }

    @Test
    @DisplayName("Добавить нового user, проверить в БД")
    void addNewUserTest() {
        //добавить генератор пользователей
        User user = new User();
        user.setFirstName("Niko")
                .setSecondName("Fisherman")
                .setAge(29)
                .setSex("MALE")
                .setMoney(66666.66);
        User userResponse = HttpClientUtil.addUser(user);

        assertTrue(HttpClientUtil.getAllUsers().contains(userResponse));
    }

    @Test
    @DisplayName("Добавить новую car, проверить в БД")
    void addNewCarTest() {
        //добавить генератор машин
        Car car = new Car();
        car.setEngineType(EngineType.DIESEL.getNameType())
                .setMark("Toyota")
                .setModel("Land Cruser")
                .setPrice(7234561.00);
        Car carResponse = HttpClientUtil.addCar(car);

        assertTrue(HttpClientUtil.getAllCars().contains(carResponse));
    }

    @Test
    @DisplayName("Добавить деньги пользователю и проверить их значение")
    void addMoneyForUserByIdTest() {
        //добавлять деньги user с этим id
        int id = 28;
        double moneyBefore = userDAO.getUserById(id).getMoney();
        double amountMoneyAdded = 4.04;

        //добавляем деньги пользователю и записываем его в переменную
        User userApi = HttpClientUtil.updateMoneyForUser(id, amountMoneyAdded);

        assertEquals(userApi.getMoney(), (moneyBefore + amountMoneyAdded),
                "Количество денег после добавления не равно!");
    }
}
