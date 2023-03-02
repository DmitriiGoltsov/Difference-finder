package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Map;

public class DifferTest {

    private Map<String, Object> map1;
    private Map<String, Object> map2;
    private final Map<String, Object> emptyMap = Collections.emptyMap();
    private final Map<String, Object> emptyMap2 = Collections.emptyMap();

    @BeforeEach
    public void beforeEach() {
        map1 = Map.of("timeout", 50, "host", "hexlet.io",
                "proxy", "123.234.53.22", "follow", false);

        map2 = Map.of("timeout", 20, "verbose", true,
                "host", "hexlet.io");
    }

    @Test
    public void getDataTest() throws Exception {
        var actual1 = Parser.getData("src/test/resources/file1.json");
        var actual2 = Parser.getData("src/test/resources/file2.json");
        var expected1 = map1;
        var expected2 = map2;

        assertThat(actual1).isEqualTo(expected1);
        assertThat(actual2).isEqualTo(expected2);
        assertThrows(FileNotFoundException.class, () -> Parser.getData("src/test/resources/file3.json"));

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

        var actual1 = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json");
        var actual2 = Differ.generate("src/test/resources/emptyfile.json", "src/test/resources/file2.json");
        var actual3 = Differ.generate("src/test/resources/file1.json", "src/test/resources/emptyfile.json");
        var actual4 = Differ.generate("src/test/resources/file1.yml", "src/test/resources/file2.yml");
        var actual5 = Differ.generate("src/test/resources/emptyfile.json", "src/test/resources/emptyfile2.json");


        assertThat(actual1).isEqualTo(expected1);
        assertThat(actual2).isEqualTo(expected2);
        assertThat(actual3).isEqualTo(expected3);
        assertThat(actual4).isEqualTo(expected4);
        assertThat(actual5).isEqualTo(expected5);
        assertThrows(FileNotFoundException.class, () -> Differ.generate("src/test/resources/file1.json",
                "src/test/resources/file3.json"));
    }
}
