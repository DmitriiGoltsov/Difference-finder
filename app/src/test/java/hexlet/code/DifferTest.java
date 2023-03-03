package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.Collections;
import java.util.Map;

public class DifferTest {

    private Map<String, Object> map1;
    private Map<String, Object> map2;
    private final Map<String, Object> emptyMap = Collections.emptyMap();
    private final Map<String, Object> emptyMap2 = Collections.emptyMap();

    private final ObjectMapper mapper1 = new ObjectMapper();
    private final ObjectMapper mapper2 = new YAMLMapper();

    @BeforeEach
    public void beforeEach() {
        map1 = Map.of("timeout", 50, "host", "hexlet.io",
                "proxy", "123.234.53.22", "follow", false);

        map2 = Map.of("timeout", 20, "verbose", true,
                "host", "hexlet.io");
    }

    @Test
    public void getDataTest() throws Exception {

        var content1 = Files.readString(Differ.getFullPath("src/test/resources/file1.json"));
        var content2 = Files.readString(Differ.getFullPath("src/test/resources/file2.json"));
        var actual1 = Parser.getData(content1, mapper1);
        var actual2 = Parser.getData(content2, mapper2);
        var expected1 = map1;
        var expected2 = map2;

        assertThat(actual1).isEqualTo(expected1);
        assertThat(actual2).isEqualTo(expected2);

    }

    @Test
    public void findDifferenceTest() {
        var expected2 = Map.of("follow", "deleted", "host", "unchanged", "proxy", "deleted",
                "timeout", "changed", "verbose", "added");
        var expected3 = Map.of("timeout", "added", "verbose", "added", "host", "added");
        var expected4 = Map.of("host", "deleted", "timeout", "deleted", "proxy", "deleted",
                "follow", "deleted");

        var actual1 = DifferenceFinder.findDifference(emptyMap, emptyMap2);
        var actual2 = DifferenceFinder.findDifference(map1, map2);
        var actual3 = DifferenceFinder.findDifference(emptyMap, map2);
        var actual4 = DifferenceFinder.findDifference(map1, emptyMap);

        assertThat(actual1).isEqualTo(emptyMap);
        assertThat(actual2).isEqualTo(expected2);
        assertThat(actual3).isEqualTo(expected3);
        assertThat(actual4).isEqualTo(expected4);
    }

    @Test
    public void generate() throws Exception {

        var expected1 = """
                - follow: false
                  host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                + timeout: 20
                + verbose: true
                """;

        var expected2 = """
                + host: hexlet.io
                + timeout: 20
                + verbose: true
                """;

        var expected3 = """
                - follow: false
                - host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                """;

        var expected4 = """
                - follow: false
                  host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                + timeout: 20
                + verbose: true
                """;

        var expected5 = "The files are empty!";

        var expected6 = """
                  chars1: [a, b, c]
                - chars2: [d, e, f]
                + chars2: false
                - checked: false
                + checked: true
                - default: null
                + default: [value1, value2]
                - id: 45
                + id: null
                - key1: value1
                + key2: value2
                  numbers1: [1, 2, 3, 4]
                - numbers2: [2, 3, 4, 5]
                + numbers2: [22, 33, 44, 55]
                - numbers3: [3, 4, 5]
                + numbers4: [4, 5, 6]
                + obj1: {nestedKey=value, isNested=true}
                - setting1: Some value
                + setting1: Another value
                - setting2: 200
                + setting2: 300
                - setting3: true
                + setting3: none
                """;

        var actual1 = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json");
        var actual2 = Differ.generate("src/test/resources/emptyfile.json", "src/test/resources/file2.json");
        var actual3 = Differ.generate("src/test/resources/file1.json", "src/test/resources/emptyfile.json");
        var actual4 = Differ.generate("src/test/resources/file1.yml", "src/test/resources/file2.yml");
        var actual5 = Differ.generate("src/test/resources/emptyfile.json", "src/test/resources/emptyfile2.json");
        var actual6 = Differ.generate("src/test/resources/nested_file1.json", "src/test/resources/nested_file2.json");


        assertThat(actual1).isEqualTo(expected1);
        assertThat(actual2).isEqualTo(expected2);
        assertThat(actual3).isEqualTo(expected3);
        assertThat(actual4).isEqualTo(expected4);
        assertThat(actual5).isEqualTo(expected5);
        assertThat(actual6).isEqualTo(expected6);
        assertThrows(NoSuchFileException.class, () -> Differ.generate("src/test/resources/file1.json",
                "src/test/resources/file3.json"));
    }
}
