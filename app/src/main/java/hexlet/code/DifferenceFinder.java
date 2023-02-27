package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DifferenceFinder {

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
