package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.KeyStatus;

import java.util.Map;

public class Formatter {

    public static String format(Map<String, KeyStatus> mapOfDiff, String style) throws JsonProcessingException {

        String result;

        switch (style) {
            case "stylish" -> {
                result = Stylish.stylishFormatter(mapOfDiff);
            }
            case "plain" -> {
                result = Plain.plainFormatter(mapOfDiff);
            }
            case "json" -> {
                result = Json.jsonFormatter(mapOfDiff);
            }
            default -> throw new RuntimeException("Unsupported style: " + style);
        }
        return result;
    }
}
