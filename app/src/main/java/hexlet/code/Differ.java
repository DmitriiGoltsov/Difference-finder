package hexlet.code;

import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {

        Map<String, Object> map1 = Parser.getData(filePath1);
        Map<String, Object> map2 = Parser.getData(filePath2);

        Map<String, String> mapOfDiff = DifferenceFinder.findDifference(map1, map2);

        if (mapOfDiff.isEmpty()) {
            return "The files are empty!";
        }

        String result = Formatter.format(mapOfDiff, map1, map2, formatName);

        return result;
    }
}
