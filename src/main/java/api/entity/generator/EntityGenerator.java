package api.entity.generator;

import api.entity.Car;
import api.entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//класс для генерации user и car
public class EntityGenerator {

    //получить рандомного пользователя
    public static User getUser() {
        User user = new User();
        user.setFirstName(getRandomString(readFile(new File("src/main/resources/firstnames.txt"))))
                .setSecondName(getRandomString(readFile(new File("src/main/resources/secondnames.txt"))))
                .setAge(getRandomAge())
                .setSex("MALE")
                .setMoney(getRandomMoney());

        return user;
    }

    //получить рандомную машину
    public static Car getCar() {
        Car car = new Car();
        Cars enumCar = getRandomCarsFromEnum();
        car.setEngineType(enumCar.getEngineType())
                .setMark(enumCar.getMark())
                .setModel(enumCar.getModel())
                .setPrice(getRandomPrice());

        return car;
    }

    //метод чтения файла по пути
    private static List<String> readFile(File path) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    //метод получения рандомной строки из списка
    private static String getRandomString(List<String> lines) {
        int index = new Random().nextInt(lines.size());
        return lines.get(index);
    }

    //метод получения рандомного возраста
    private static int getRandomAge() {
        return (new Random().nextInt(47) + 18);
    }

    //метод получения рандомного количества денег
    private static double getRandomMoney() {
        return Math.ceil(Math.random() * ((100000 - 1000) + 1) + 1000);
    }

    //метод получения рандомного EngineType
    private static Cars getRandomCarsFromEnum() {
        List<Cars> values = Arrays.asList(Cars.values());
        return values.get(new Random().nextInt(values.size()));
    }

    //метод получения рандомной цены
    private static double getRandomPrice() {
        return Math.ceil(Math.random() * ((1000000 - 50000) + 1) + 50000);
    }
}
