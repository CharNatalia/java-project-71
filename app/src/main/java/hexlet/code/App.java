package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Map;
import java.util.concurrent.Callable;

import com.fasterxml.jackson.databind.ObjectMapper;

import static hexlet.code.Differ.generate;


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
    private String filepath1 = "file1.json";

    @Parameters(index = "1", description = "path to second file")
    private String filepath2 = "file2.json";

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = ".json";

    @Override
    public Integer call() throws Exception{
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();

        if (!Files.exists(path1)) {
            throw new Exception("File '" + path1 + "' does not exist");
        }

        if (!Files.exists(path2)) {
            throw new Exception("File '" + path2 + "' does not exist");
        }

        String jsonFile1 = Files.readString(path1);
        String jsonFile2 = Files.readString(path2);
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> mapFile1
                = objectMapper.readValue(jsonFile1, new TypeReference<Map<String,Object>>(){});
        Map<String, Object> mapFile2
                = objectMapper.readValue(jsonFile2, new TypeReference<Map<String,Object>>(){});
        System.out.println(generate(mapFile1, mapFile2));
        return 0;
    }
}
