package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {

        Map<String, Object> map1 = getData(filePath1);
        Map<String, Object> map2 = getData(filePath2);

        Map<String, String> mapOfDiff = findDifference(map1, map2);

        if (mapOfDiff.isEmpty()) {
            return "The files are empty!";
        }

        StringBuilder str = new StringBuilder();

        for (Map.Entry<String, String> element : mapOfDiff.entrySet()) {
            switch (element.getValue()) {
                case "deleted" -> str.append("- " + element.getKey() + ": " + map1.get(element.getKey()) + "\n");
                case "added" -> str.append("+ " + element.getKey() + ": " + map2.get(element.getKey()) + "\n");
                case "unchanged" -> str.append("  " + element.getKey() + ": " + map1.get(element.getKey()) + "\n");
                case "changed" -> str.append("+ " + element.getKey() + ": " + map2.get(element.getKey()) + "\n"
                        + "- " + element.getKey() + ": " + map1.get(element.getKey()) + "\n");
                default -> {
                    return "Something went wrong!";
                }
            }
        }

        return str.toString();
    }

    public static Map<String, Object> getData(String content) throws Exception {

        Path path = Paths.get(content).toAbsolutePath().normalize();

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> result = mapper.readValue(new File(String.valueOf(path)), Map.class);

        TreeMap<String, Object> sortedResult = new TreeMap<>(result);

        return sortedResult;
    }

    public static Map<String, String> findDifference(Map<String, Object> firstMap, Map<String, Object> secondMap) {

        Map<String, String> result = new LinkedHashMap<>();

        Set<String> keySet = new TreeSet<>(firstMap.keySet());
        keySet.addAll(secondMap.keySet());

        if (firstMap.isEmpty() && secondMap.isEmpty()) {
            return result;
        } else if (!firstMap.isEmpty() && secondMap.isEmpty()) {
            for (String element : keySet) {
                result.put(element, "deleted");
            }
        } else if (firstMap.isEmpty() && !secondMap.isEmpty()) {
            for (String element : keySet) {
                result.put(element, "added");
            }
        }

        for (String key : keySet) {
            if (!firstMap.containsKey(key)) {
                result.put(key, "added");
            } else if (!secondMap.containsKey(key)) {
                result.put(key, "deleted");
            } else if (firstMap.get(key).equals(secondMap.get(key))) {
                result.put(key, "unchanged");
            } else if (!firstMap.get(key).equals(secondMap.get(key))) {
                result.put(key, "changed");
            }
        }

        return result;
    }
}
