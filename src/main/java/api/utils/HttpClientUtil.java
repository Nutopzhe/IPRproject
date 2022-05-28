package api.utils;

import api.entity.Car;
import api.entity.User;
import config.Config;

import java.util.Arrays;
import java.util.List;

import static api.http.HttpMethods.*;
import static api.utils.ObjectMapperUtil.*;

public class HttpClientUtil {

    private static String url = Config.getProperty("api.url");
    private static final String ADD_USER = "/addUser";
    private static final String ADD_CAR = "/addCar";
    private static final String GET_ALL_USERS = "/users";
    private static final String GET_ALL_CARS = "/cars";
    private static final String ADD_MONEY_FOR_USER = "/user/%s/money/%s";

    //rest метод добавления денег пользователю по id
    public static User updateMoneyForUser(int userId, double money) {
        return getValue(doPost(url + String.format(ADD_MONEY_FOR_USER, userId, money), null), User.class);
    }

    //rest метод добавления нового пользователя
    public static User addUser(User user) {
        return getValue(doPost(url + ADD_USER, user), User.class);
    }

    //rest метод добавления новой машины
    public static Car addCar(Car car) {
        return getValue(doPost(url + ADD_CAR, car), Car.class);
    }

    //rest метод получения всех пользователей
    public static List<User> getAllUsers() {
        return Arrays.asList(getValue(
                doGet(url + GET_ALL_USERS), User[].class)
        );
    }

    //rest метод получения всех машин
    public static List<Car> getAllCars() {
        return Arrays.asList(getValue(
                doGet(url + GET_ALL_CARS), Car[].class)
        );
    }
}
