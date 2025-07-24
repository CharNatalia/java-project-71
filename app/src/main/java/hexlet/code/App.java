package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.Map;
import java.util.concurrent.Callable;

import static hexlet.code.Differ.generate;
import static hexlet.code.Parser.readFile;


@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)
public class App implements Callable<Integer> {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    @Override
    public Integer call() throws Exception {
        if (format.equals("json") || format.equals("yaml") || format.equals("yml")) {
            System.out.println(readFile(filepath1));
            System.out.println(readFile(filepath2));
            return 0;
        }

        Map<String, Object> previousFile = Parser.parse(filepath1);
        Map<String, Object> currentFile = Parser.parse(filepath2);

        System.out.println(generate(previousFile, currentFile, format));
        return 0;
    }
}
