package api.utils;

import api.entity.Car;
import api.entity.User;
import api.http.HttpMethods;
import config.Config;
import org.apache.http.HttpResponse;

public class PerformanceAPI {
    private HttpMethods baseMethods = new HttpMethods();

    private static String url = Config.getProperty("api.url");

    /**
     * Отправляет POST запрос на добавление денег пользователю по ID
     * @param userId Id пользователя
     * @param money Количество денег
     * @return HttpResponse
     */
    public HttpResponse updateMoneyForUser(int userId, double money) {
        return baseMethods.doPost(url + String.format("/user/%s/money/%s", userId, money), null);
    }

    /**
     * Отправляет POST запрос на добавление нового пользователя
     * @param user Принимаемый объект
     * @return HttpResponse
     */
    public HttpResponse addUser(User user) {
        return baseMethods.doPost(url + "/addUser", user);
    }

    /**
     * Отправляет POST запрос на добавление новой машины
     * @param car Принимаемый объект
     * @return HttpResponse
     */
    public HttpResponse addCar(Car car) {
        return baseMethods.doPost(url + "/addCar", car);
    }

    /**
     * Отправляет GET запрос на получения всех пользователей
     * @return HttpResponse
     */
    public HttpResponse getAllUsers() {
        return baseMethods.doGet(url + "/users");
    }

    /**
     * Отправляет GET запрос на получения всех машин
     * @return HttpResponse
     */
    public HttpResponse getAllCars() {
        return baseMethods.doGet(url + "/cars");
    }
}
