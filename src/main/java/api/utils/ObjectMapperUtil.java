package api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import java.io.IOException;

public class ObjectMapperUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T getValue(HttpResponse response, Class<T> valueType) {
        try {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED ||
                    response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return mapper.readValue(response.getEntity().getContent(), valueType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Не удалось прочитать объект из ответа!");
    }

    public static <T> String writeValue(T value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Не удалось записать объект в строку!");
    }
}
