package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static Map getData(String pathToContent) throws Exception {

        ObjectMapper mapper = chooseFormatOfMapper(pathToContent);
        Path fullPath = getFullPath(pathToContent);

        Map<String, Object> result = mapper.readValue(new File(String.valueOf(fullPath)), Map.class);

        return result;
    }

    public static Path getFullPath(String path) {

        String loweredPath = path.toLowerCase();

        Path fullPath = Paths.get(loweredPath).toAbsolutePath().normalize();

        return fullPath;
    }

    private static ObjectMapper chooseFormatOfMapper(String path) {

        ObjectMapper result;

        if (path.endsWith(".json")) {
            result = new ObjectMapper();
        } else {
            result = new YAMLMapper();
        }

        return result;
    }

}
