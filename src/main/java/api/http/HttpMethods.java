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
    private ObjectMapperUtil mapper = new ObjectMapperUtil();

    /**
     * Метод HttpPost на добавление или изменения объекта
     *
     * @param url Путь
     * @param value Объект который нужно добавить или изменить
     * @return HttpResponse
     */
    public HttpResponse doPost(String url, Object value) {
        HttpPost request = new HttpPost(url);

        //если значение не null, то метод используется для добавления
        //если null, то для изменения
        if (value != null) {
            StringEntity entity = null;
            try {
                entity = new StringEntity(mapper.writeValue(value));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            request.setEntity(entity);
            request.setHeader("Accept", "application/json");
        }
        request.setHeader("Content-type", "application/json");

        return getResponse(request);

    }

    /**
     * Метод HttpGet на получение объекта
     * @param url Путь
     * @return HttpResponse
     */
    public HttpResponse doGet(String url) {
        HttpGet request = new HttpGet(url);

        return getResponse(request);
    }

    /**
     * @param request Объект запроса
     * @return HttpResponse
     */
    private HttpResponse getResponse(HttpUriRequest request) {
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            try {
                return client.execute(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("Не удалось отправить запрос " + request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Не удалось отправить запрос " + request);
    }
}
