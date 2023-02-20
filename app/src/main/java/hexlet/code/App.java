package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1.0",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", paramLabel = "format")
    private String formatName;

    @Parameters(paramLabel = "filepath1",
            index = "0",
            description = "path to first file")
    private String filePath1;

    @Parameters(paramLabel = "filepath2",
            index = "1",
            description = "path to second file")
    private String filePath2;

    public static void main(String[] args) throws Exception {

//        String result = Differ.generate("app/src/test/resources/file1.json", "app/src/test/resources/file2.json");
//        System.out.println(result);

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

        System.out.println("Hello, world!");
    }

    @Override
    public Integer call() throws Exception {
        // Обработку исключений пока ещё не проходили, потому в простейшем случае тут должен быть
        // только вызов Differ.generate()
        try {
            String formattedDiff = Differ.generate(filePath1, filePath2, formatName);
            System.out.println(formattedDiff);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 1;
        }

        return 0;
    }
}
