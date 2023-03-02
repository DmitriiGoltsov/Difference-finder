package hexlet.code;

import java.util.Map;

public class Formatter {

    public static String format(Map<String, String> mapOfDiff, Map<String, Object> map1,
                                Map<String, Object> map2, String style) {

        String result;

        switch (style) {
            case "stylish" -> {
                result = stylishFormatter(mapOfDiff, map1, map2);
            }
            default -> {
                return "Unsupported style!";
            }
        }
        return result;
    }

    private static String stylishFormatter(Map<String, String> mapOfDiff, Map<String,
            Object> map1, Map<String, Object> map2) {

        StringBuilder str = new StringBuilder();

        for (Map.Entry<String, String> element : mapOfDiff.entrySet()) {
            switch (element.getValue()) {
                case "deleted" -> str.append("- " + element.getKey() + ": " + map1.get(element.getKey()) + "\n");
                case "added" -> str.append("+ " + element.getKey() + ": " + map2.get(element.getKey()) + "\n");
                case "unchanged" -> str.append("  " + element.getKey() + ": " + map1.get(element.getKey()) + "\n");
                case "changed" -> str.append("- " + element.getKey() + ": " + map1.get(element.getKey()) + "\n"
                        + "+ " + element.getKey() + ": " + map2.get(element.getKey()) + "\n");
                default -> {
                    return "Something went wrong!";
                }
            }
        }

        return str.toString();
    }
}
