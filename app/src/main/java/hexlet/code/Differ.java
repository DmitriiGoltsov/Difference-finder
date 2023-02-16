package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {

        Map<String, Object> map1 = getData(filePath1);
        Map<String, Object> map2 = getData(filePath2);

//        System.out.println(map1);
//        System.out.println(map2);

        Map<String, String> result = new LinkedHashMap<>();
        Set<String> keySet = new TreeSet<>(map1.keySet());
        keySet.addAll(map2.keySet());

        if (map1.isEmpty() && map2.isEmpty()) {
            return "Both files do not contain data!";
        } else if (!map1.isEmpty() && map2.isEmpty()) {
            for (String element : keySet) {
                result.put(element, "deleted");
            }
        } else if (map1.isEmpty() && !map2.isEmpty()) {
            for (String element : keySet) {
                result.put(element, "added");
            }
        }

        for (String key : keySet) {
            if (!map1.containsKey(key)) {
                result.put(key, "added");
            } else if (!map2.containsKey(key)) {
                result.put(key, "deleted");
            } else if (map1.get(key).equals(map2.get(key))) {
                result.put(key, "unchanged");
            } else if (!map1.get(key).equals(map2.get(key))) {
                result.put(key, "changed");
            }
        }
//        System.out.println(result);
        return formStringResult(result, map1, map2);
    }

    public static Map<String, Object> getData(String content) throws Exception {

        Path path = Paths.get(content).toAbsolutePath().normalize();

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> result = mapper.readValue(new File(String.valueOf(path)), Map.class);

        return result;
    }

    public static String formStringResult(Map<String, String> diff,
                                          Map<String, Object> data1, Map<String, Object> data2) {

        StringBuilder str = new StringBuilder();

        for (Map.Entry<String, String> element : diff.entrySet()) {
            if (element.getValue().equals("deleted")) {
                str.append("- " + element.getKey() + ": " + data1.get(element.getKey()) + "\n");
            } else if (element.getValue().equals("added")) {
                str.append("+ " + element.getKey() + ": " + data2.get(element.getKey()) + "\n");
            } else if (element.getValue().equals("unchanged")) {
                str.append("  " + data1.get(element.getKey()) + " " + element.getKey() + "\n");
            } else if (element.getValue().equals("changed")) {
                str.append("+ " + element.getKey() + ": " + data2.get(element.getKey()) + "\n" +
                        "- " + element.getKey() + ": " + data1.get(element.getKey()) + "\n");
            }
        }
        return str.toString();
    }
}
