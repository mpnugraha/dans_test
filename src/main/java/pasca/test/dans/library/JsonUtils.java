package pasca.test.dans.library;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonUtils() {}
    public static <T> T stringJsonToObject(String jsonInString, Class<T> clazz) {
        try {
            return mapper.readValue(jsonInString, clazz);
        } catch (IOException e) {
            LOGGER.error("Error on stringJsonToObject: {} -> {}", e, jsonInString);
            return null;
        }
    }

    public static <T> T convertJsonInStringToObject(String jsonInString, TypeReference<T> typeRef) {
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(jsonInString, typeRef);
        } catch (IOException e) {
            LOGGER.error("Error on convertJsonInStringToObject.", e);
            return null;
        }
    }
}
