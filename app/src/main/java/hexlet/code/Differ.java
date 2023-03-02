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
