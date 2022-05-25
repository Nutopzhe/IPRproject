package api.utils;

import api.entity.Car;
import api.entity.User;
import api.http.HttpMethods;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.Config;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.List;

public class HttpClientUtil {

    private static String url = Config.getProperty("api.url");
    private static final String ADD_USER = "/addUser";
    private static final String ADD_CAR = "/addCar";
    private static final String GET_ALL_USERS = "/users";
    private static final String GET_ALL_CARS = "/cars";
    private static final String ADD_MONEY_FOR_USER = "/user/%s/money/%s";

    //rest метод добавления денег пользователю по id
    public static User updateMoneyForUser(int userId, double money) {
        return HttpMethods.doPost(url + String.format(ADD_MONEY_FOR_USER, userId, money), User.class);
    }

    //rest метод добавления нового пользователя
    public static User addUser(User user) {
        return HttpMethods.doPost(url + ADD_USER, user, User.class);
    }

    //rest метод добавления новой машины
    public static Car addCar(Car car) {
        return HttpMethods.doPost(url + ADD_CAR, car, Car.class);
    }

    //rest метод получения всех пользователей
    public static List<User> getAllUsers() {
        List<User> users = HttpMethods.doGet(url + GET_ALL_USERS, User[].class);;
        return users;
    }

    //rest метод получения всех машин
    public static List<Car> getAllCars() {
        List<Car> cars = HttpMethods.doGet(url + GET_ALL_CARS, Car[].class);
        return cars;
    }
}
