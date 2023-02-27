package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static Map getData(String pathToContent) throws Exception {

        Path fullPath = getFullPath(pathToContent);

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> result = mapper.readValue(new File(String.valueOf(fullPath)), Map.class);

        return result;
    }

    public static Path getFullPath(String path) {

        Path fullPath = Paths.get(path).toAbsolutePath().normalize();

        return fullPath;
    }

}
