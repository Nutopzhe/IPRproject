package api.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HttpMethods {
    private static final ObjectMapper mapper = new ObjectMapper();

    //метод HttpPost на изменение объектов
    public static <T> T doPost(String url, Class<T> valueType) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(url);
            request.setHeader("Content-type", "application/json");

            HttpResponse response = client.execute(request, new HttpClientContext());
            if (response.getStatusLine().getStatusCode() == 200) {
            return mapper.readValue(response.getEntity().getContent(), valueType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Не удалось отправить POST запрос!");
    }

    //метод HttpPost на добавление объектов
    public static <T> T doPost(String url, T value, Class<T> valueType) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(url);

            StringEntity entity = new StringEntity(mapper.writeValueAsString(value));
            request.setEntity(entity);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");

            HttpResponse response = client.execute(request, new HttpClientContext());

            return mapper.readValue(response.getEntity().getContent(), valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Не удалось отправить POST запрос!");
    }

    //метод HttpGet на получение объектов
    public static <T> List<T> doGet(String url, Class<T[]> valueType) {
        List<T> list = new ArrayList<>();
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);

            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                list = Arrays.asList(mapper.readValue(response.getEntity().getContent(), valueType));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Не удалось отправить GET запрос!");
    }
}
