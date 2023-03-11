package hexlet.code;

import hexlet.code.formatters.Formatter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {

        Path fullPath1 = getFullPath(filePath1);
        Path fullPath2 = getFullPath(filePath2);

        String file1Extension = getDataFormat(String.valueOf(fullPath1));
        String file2Extension = getDataFormat(String.valueOf(fullPath2));

        String contentOfFile1 = Files.readString(fullPath1);
        String contentOfFile2 = Files.readString(fullPath2);

        Map<String, Object> map1 = Parser.getData(contentOfFile1, file1Extension);
        Map<String, Object> map2 = Parser.getData(contentOfFile2, file2Extension);

        Map<String, KeyStatus> mapOfDiff = DifferenceFinder.findDifference(map1, map2);

        if (mapOfDiff.isEmpty()) {
            return "The files are empty!";
        }

        String result = Formatter.format(mapOfDiff, formatName);

        return result;
    }

    public static Path getFullPath(String path) {

        Path fullPath = Paths.get(path).toAbsolutePath().normalize();

        return fullPath;
    }

    private static String getDataFormat(String filePath) {

        int index = filePath.lastIndexOf('.');

        return index > 0
                ? filePath.substring(index + 1)
                : "";

    }
}
