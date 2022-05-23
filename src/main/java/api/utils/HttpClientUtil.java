package api.utils;

import api.entity.Car;
import api.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HttpClientUtil {

    private static final String URL = "http://77.50.236.203:4880";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static User updateMoneyForUser(int userId, double money) {
        User userResponse = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(URL + String.format("/user/%s/money/%s", userId, money));

            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                userResponse = mapper.readValue(response.getEntity().getContent(), User.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userResponse;
    }

    public static User addUser(User user) {
        User userResponse = new User();
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(URL + "/addUser");

            StringEntity entity = new StringEntity(mapper.writeValueAsString(user));
            request.setEntity(entity);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");

            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                userResponse = mapper.readValue(response.getEntity().getContent(), User.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userResponse;
    }

    public static Car addCar(Car car) {
        Car carResponse = new Car();
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(URL + "/addCar");

            StringEntity entity = new StringEntity(mapper.writeValueAsString(car));
            request.setEntity(entity);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");

            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                carResponse = mapper.readValue(response.getEntity().getContent(), Car.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carResponse;
    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpResponse response = client.execute(new HttpGet(URL + "/users"));
            if (response.getStatusLine().getStatusCode() == 200) {
                users = Arrays.asList(mapper.readValue(response.getEntity().getContent(), User[].class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpResponse response = client.execute(new HttpGet(URL + "/cars"));
            if (response.getStatusLine().getStatusCode() == 200) {
                cars = Arrays.asList(mapper.readValue(response.getEntity().getContent(), Car[].class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cars;
    }
}