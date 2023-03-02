package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static Map<String, Object> getData(String pathToContent) throws Exception {

        Path fullPath = getFullPath(pathToContent);
        String fileExtension = getFileExtension(String.valueOf(fullPath));
        ObjectMapper mapper = mapperFactory(fileExtension);
        String contentOfFile = Files.readString(fullPath);

        Map<String, Object> result = mapper.readValue(contentOfFile, Map.class);

        return result;
    }

    public static Path getFullPath(String path) {

        String loweredPath = path.toLowerCase();

        Path fullPath = Paths.get(loweredPath).toAbsolutePath().normalize();

        return fullPath;
    }

    private static ObjectMapper mapperFactory(String extension) throws Exception {

        switch (extension) {
            case ".json" -> {
                return new ObjectMapper();
            }
            case ".yml" -> {
                return new YAMLMapper();
            }
            default -> throw new Exception("Unsupported file extension!");
        }
    }

    private static String getFileExtension(String path) {

        return path.substring(path.indexOf("."));

    }

}
