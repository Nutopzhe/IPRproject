package api.http;

import api.utils.ObjectMapperUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class HttpMethods {

    //метод HttpPost на добавление и изменения объектов
    public static <T> HttpResponse doPost(String url, T value) {
        HttpPost request = new HttpPost(url);

        //если значение не null, то метод используется для добавления
        //если null, то для изменения
        if (value != null) {
            StringEntity entity = null;
            try {
                entity = new StringEntity(ObjectMapperUtil.writeValue(value));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            request.setEntity(entity);
            request.setHeader("Accept", "application/json");
        }
        request.setHeader("Content-type", "application/json");

        return getResponse(request);

    }

    //метод HttpGet на получение объектов
    public static HttpResponse doGet(String url) {
        HttpGet request = new HttpGet(url);

        return getResponse(request);
    }

    //метод получения ответа по запросу
    private static HttpResponse getResponse(HttpUriRequest request) {
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            return client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Не удалось отправить запрос " + request);
    }
}
