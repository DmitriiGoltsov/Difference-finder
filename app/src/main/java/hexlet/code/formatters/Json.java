package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.KeyStatus;

public class Json {

    public static String jsonFormatter(Map<String, KeyStatus> mapOfDiff) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        String result = mapper.writeValueAsString(mapOfDiff);

        return result;
    }
}
