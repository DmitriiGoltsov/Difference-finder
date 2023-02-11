package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;



@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1.0",
        description = "Compares two configuration files and shows a difference.")
public class App {

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", paramLabel = "format")
    private String format;

    @Parameters(description = "A path to the 1st file.", paramLabel = "filepath1")
    private static String filePath1;

    @Parameters(description = "A path to the 2nd file.", paramLabel = "filepath2")
    private static String filePath2;

    public static void main(String[] args) throws Exception {

        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();

        ObjectMapper mapper = new ObjectMapper();

        Map<String, String> map1 = mapper.readValue(new File(String.valueOf(path1)), Map.class);
        Map<String, String> map2 = mapper.readValue(new File(String.valueOf(path2)), Map.class);

        System.out.println(map1);
        System.out.println(map2);

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

        System.out.println("Hello, world!");

    }

}
