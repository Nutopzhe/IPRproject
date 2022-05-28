package api.utils;

import api.entity.Car;
import api.entity.User;
import api.http.HttpMethods;
import config.Config;
import org.apache.http.HttpResponse;

public class HttpClientUtil {
    private HttpMethods baseMethods = new HttpMethods();

    private static String url = Config.getProperty("api.url");
    private static final String ADD_USER = "/addUser";
    private static final String ADD_CAR = "/addCar";
    private static final String GET_ALL_USERS = "/users";
    private static final String GET_ALL_CARS = "/cars";
    private static final String ADD_MONEY_FOR_USER = "/user/%s/money/%s";

    //rest метод добавления денег пользователю по id

    /**
     * Отправляет POST запрос на добавление денег пользователю по ID
     * @param userId Id пользователя
     * @param money Количество денег
     * @return HttpResponse
     */
    public HttpResponse updateMoneyForUser(int userId, double money) {
        return baseMethods.doPost(url + String.format(ADD_MONEY_FOR_USER, userId, money), null);
    }

    /**
     * Отправляет POST запрос на добавление нового пользователя
     * @param user Принимаемый объект
     * @return HttpResponse
     */
    public HttpResponse addUser(User user) {
        return baseMethods.doPost(url + ADD_USER, user);
    }

    /**
     * Отправляет POST запрос на добавление новой машины
     * @param car Принимаемый объект
     * @return HttpResponse
     */
    public HttpResponse addCar(Car car) {
        return baseMethods.doPost(url + ADD_CAR, car);
    }

    /**
     * Отправляет GET запрос на получения всех пользователей
     * @return HttpResponse
     */
    public HttpResponse getAllUsers() {
        return baseMethods.doGet(url + GET_ALL_USERS);
    }

    /**
     * Отправляет GET запрос на получения всех машин
     * @return HttpResponse
     */
    public HttpResponse getAllCars() {
        return baseMethods.doGet(url + GET_ALL_CARS);
    }
}
