package api.utils;

import api.entity.Car;
import api.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APIUtil {
    private static final String URL = "http://77.50.236.203:4880";
    private static final String ALL_USERS = "/users";
    private static final String ALL_CARS = "/cars";
    private static final String ADD_USER = "/addUser";
    private static final String ADD_CAR = "/addCar";
    private static final String ADD_MONEY_FOR_USER = "/user/%s/money/%s";

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Car addCar(Car car) {
        Car carResponse = new Car();
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(URL + ADD_CAR);

            StringEntity entity = new StringEntity(mapper.writeValueAsString(car));
            request.setEntity(entity);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");

            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
                carResponse = mapper.readValue(response.getEntity().getContent(), Car.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carResponse;
    }

    public static User addUser(User user) {
        User userResponse = new User();
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(URL + ADD_USER);

            StringEntity entity = new StringEntity(mapper.writeValueAsString(user));
            request.setEntity(entity);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");

            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
                userResponse = mapper.readValue(response.getEntity().getContent(), User.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userResponse;
    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpResponse response = client.execute(new HttpGet(URL + ALL_USERS));
            users = Arrays.asList(mapper.readValue(response.getEntity().getContent(), User[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpResponse response = client.execute(new HttpGet(URL + ALL_CARS));
            cars = Arrays.asList(mapper.readValue(response.getEntity().getContent(), Car[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cars;
    }
}
