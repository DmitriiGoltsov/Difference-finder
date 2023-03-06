package hexlet.code.formatters;

import java.util.Map;

public class Stylish {

    public static String stylishFormatter(Map<String, String> mapOfDiff, Map<String,
            Object> map1, Map<String, Object> map2) {

        StringBuilder str = new StringBuilder();

        for (Map.Entry<String, String> element : mapOfDiff.entrySet()) {
            switch (element.getValue()) {
                case "deleted" -> str.append("- ").append(element.getKey()).
                        append(": ").append(map1.get(element.getKey())).append("\n");
                case "added" -> str.append("+ ").append(element.getKey()).append(": ")
                        .append(map2.get(element.getKey())).append("\n");
                case "unchanged" -> str.append("  ").append(element.getKey()).append(": ")
                        .append(map1.get(element.getKey())).append("\n");
                case "changed" -> str.append("- ").append(element.getKey()).append(": ")
                        .append(map1.get(element.getKey())).append("\n").append("+ ")
                        .append(element.getKey()).append(": ").append(map2.get(element.getKey())).append("\n");
                default -> {
                    return "Something went wrong!";
                }
            }
        }

        return str.toString().trim();
    }
}
