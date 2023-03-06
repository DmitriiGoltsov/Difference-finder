package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {

    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    private static String jsonFile1Path;
    private static String jsonFile2Path;
    private static String ymlFile1Path;
    private static String ymlFile2Path;

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultJson = readFixture("result_json.json");
        resultPlain = readFixture("result_plain.txt");
        resultStylish = readFixture("result_stylish.txt");
        jsonFile1Path = "src/test/resources/fixtures/nested_file1.json";
        jsonFile2Path = "src/test/resources/fixtures/nested_file2.json";
        ymlFile1Path = "src/test/resources/fixtures/nested_file1.yml";
        ymlFile2Path = "src/test/resources/fixtures/nested_file2.yml";

    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @Test
    public void generateJSON() throws Exception {

        var actual1 = Differ.generate(jsonFile1Path, jsonFile2Path);
        var actual2 = Differ.generate(jsonFile1Path, jsonFile2Path, "stylish");
        var actual3 = Differ.generate(jsonFile1Path, jsonFile2Path, "plain");
        var actual4 = Differ.generate(jsonFile1Path, jsonFile2Path, "json");

        var expected1 = resultStylish;
        var expected2 = resultPlain;
        var expected3 = resultJson;

        Assertions.assertEquals(actual1, expected1);
        Assertions.assertEquals(actual2, expected1);
        Assertions.assertEquals(actual3, expected2);
        Assertions.assertEquals(actual4, expected3);

    }

    @Test
    public void generateYML() throws Exception {

        var actual1 = Differ.generate(ymlFile1Path, ymlFile2Path);
        var actual2 = Differ.generate(ymlFile1Path, ymlFile2Path, "stylish");
        var actual3 = Differ.generate(ymlFile1Path, ymlFile2Path, "plain");
        var actual4 = Differ.generate(ymlFile1Path, ymlFile2Path, "json");

        var expected1 = resultStylish;
        var expected2 = resultPlain;
        var expected3 = resultJson;

        Assertions.assertEquals(actual1, expected1);
        Assertions.assertEquals(actual2, expected1);
        Assertions.assertEquals(actual3, expected2);
        Assertions.assertEquals(actual4, expected3);

    }

}
