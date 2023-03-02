package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;


@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 0.3.0",
        description = "Compares two configuration files and shows a difference.")

public final class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"},
            description = "output format [default: stylish]",
            paramLabel = "format",
            defaultValue = "stylish")
    private String formatName;

    @Parameters(paramLabel = "filepath1",
            index = "0",
            description = "path to the first file")
    private String filePath1;

    @Parameters(paramLabel = "filepath2",
            index = "1",
            description = "path to the second file")
    private String filePath2;

    public static void main(String[] args) throws Exception {

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

    }

    @Override
    public Integer call() throws Exception {
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
