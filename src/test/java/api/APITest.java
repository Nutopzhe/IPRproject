package api;

import api.dao.CarDAO;
import api.dao.UserDAO;
import api.entity.Car;
import api.entity.User;
import api.utils.EntityGenerator;
import api.utils.FactoryDAO;
import api.utils.HttpClientUtil;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class APITest {
    UserDAO userDAO = FactoryDAO.getInstance().getUserDAO();
    CarDAO carDAO = FactoryDAO.getInstance().getCarDAO();

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
        //генератор пользователей
        User user = EntityGenerator.getUser();
        User userResponse = HttpClientUtil.addUser(user);

        assertTrue(HttpClientUtil.getAllUsers().contains(userResponse));
    }

    @Test
    @DisplayName("Добавить новую car, проверить в БД")
    void addNewCarTest() {
        //генератор машин
        Car car = EntityGenerator.getCar();
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
