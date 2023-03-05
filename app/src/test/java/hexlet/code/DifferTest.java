package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
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
    public void generateWithEmptyFile() throws Exception {

        var expected1 = "The files are empty!";
        var actual1 = Differ.generate("src/test/resources/emptyfile.json", "src/test/resources/emptyfile2.json");

        assertThat(actual1).isEqualTo(expected1);

    }

    @Test
    public void generateStylishYml() throws Exception {

        var expected4 = """
                - follow: false
                  host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                + timeout: 20
                + verbose: true
                """;

        var actual4 = Differ.generate("src/test/resources/file1.yml", "src/test/resources/file2.yml");

        assertThat(actual4).isEqualTo(expected4);

    }

    @Test
    public void generateStylishJson() throws Exception {

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
        var actual4 = Differ.generate("src/test/resources/nested_file1.json", "src/test/resources/nested_file2.json");

        assertThat(actual1).isEqualTo(expected1);
        assertThat(actual2).isEqualTo(expected2);
        assertThat(actual3).isEqualTo(expected3);
        assertThat(actual4).isEqualTo(expected4);

    }

    @Test
    public void generatePlain() throws Exception {

        var expected1 = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'
                """;

        var actual1 = Differ.generate("src/test/resources/nested_file1.json",
                "src/test/resources/nested_file2.json", "plain");

        assertThat(actual1).isEqualTo(expected1);

    }

    @Test
    public void generate() throws Exception {

        var expected1 = """
                {
                "chars1=[a, b, c]": "unchanged",
                "chars2=[d, e, f]": "deleted",
                "chars2=false": "added",
                "checked=false": "deleted",
                "checked=true": "added",
                "default=null": "deleted",
                "default=[value1, value2]": "added",
                "id=45": "deleted",
                "id=null": "added",
                "key1=value1": "deleted",
                "key2=value2": "added",
                "numbers1=[1, 2, 3, 4]": "unchanged",
                "numbers2=[2, 3, 4, 5]": "deleted",
                "numbers2=[22, 33, 44, 55]": "added",
                "numbers3=[3, 4, 5]": "deleted",
                "numbers4=[4, 5, 6]": "added",
                "obj1={nestedKey=value, isNested=true}": "added",
                "setting1=Some value": "deleted",
                "setting1=Another value": "added",
                "setting2=200": "deleted",
                "setting2=300": "added",
                "setting3=true": "deleted",
                "setting3=none": "added"
                }
                """;

        var actual1 = Differ.generate("src/test/resources/nested_file1.json",
                "src/test/resources/nested_file2.json", "json");

        assertThat(actual1).isEqualTo(expected1);
    }
}
