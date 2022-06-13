package api;

import api.dao.CarDAO;
import api.dao.UserDAO;
import api.entity.Car;
import api.entity.User;
import api.entity.generator.EntityGenerator;
import api.dao.FactoryDAO;
import api.utils.PerformanceAPI;
import api.utils.ObjectMapperUtil;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class APITest {
    UserDAO userDAO = FactoryDAO.getInstance().getUserDAO();
    CarDAO carDAO = FactoryDAO.getInstance().getCarDAO();

    ObjectMapperUtil mapper = new ObjectMapperUtil();
    PerformanceAPI httpClient = new PerformanceAPI();

    @Test
    @DisplayName("Получить всех users, проверить с БД")
    void getAllUsers() {
        List<User> usersFromAPI = Arrays.asList(
                mapper.getValue(
                        httpClient.getAllUsers(), User[].class)
        );
        List<User> usersFromDB = userDAO.getAllUsers();

        assertEquals(usersFromAPI, usersFromDB);
    }

    @Test
    @DisplayName("Получить все cars, проверить с БД")
    void getAllCarsTest() {
        List<Car> carsFromAPI = Arrays.asList(
                mapper.getValue(
                        httpClient.getAllCars(), Car[].class)
        );
        List<Car> carsFromDB = carDAO.getAllCars();

        assertEquals(carsFromAPI, carsFromDB);
    }

    @Test
    @DisplayName("Добавить нового user, проверить в БД")
    void addNewUserTest() {
        //генератор пользователей
        User user = EntityGenerator.getUser();
        User userResponse = mapper.getValue(
                httpClient.addUser(user), User.class);

        assertTrue(userDAO.getAllUsers().contains(userResponse));
    }

    @Test
    @DisplayName("Добавить новую car, проверить в БД")
    void addNewCarTest() {
        //генератор машин
        Car car = EntityGenerator.getCar();
        Car carResponse = mapper.getValue(
                httpClient.addCar(car), Car.class);

        assertTrue(carDAO.getAllCars().contains(carResponse));
    }

    @Test
    @DisplayName("Добавить деньги пользователю и проверить их значение")
    void addMoneyForUserByIdTest() {
        //добавлять деньги user с этим id
        int id = 28;
        Double moneyBefore = userDAO.getUserById(id).getMoney();
        Double amountMoneyAdded = 4.05;

        //добавляем деньги пользователю и записываем его в переменную
        User userApi = mapper.getValue(
                httpClient.updateMoneyForUser(id, amountMoneyAdded), User.class);
//        User userApi = HttpClientUtil.updateMoneyForUser(id, amountMoneyAdded);

        assertEquals(String.format("%.2f", userApi.getMoney()),
                String.format("%.2f", moneyBefore + amountMoneyAdded),
                "Количество денег после добавления не равно!");
    }
}
