package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String filepath) throws Exception {
        String file = readFile(filepath);

        var a = filepath.lastIndexOf('.');
        var format = filepath.substring(a + 1);

        if (format.equals("json")) {
            return jsonParser(file);
        } else if (format.equals("yaml") || format.equals("yml")) {
            return yamlParser(file);
        }
        throw new Exception("Unknown format");
    }

    public static String readFile(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        System.out.println(path);
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return Files.readString(path);
    }

    public static Map<String, Object> jsonParser(String jsonFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> parsedFile = new HashMap<>();
        try {
            parsedFile =
                    objectMapper.readValue(jsonFile, new TypeReference<>() { });
        } catch (Exception e) {
            System.out.println("Невалидный json");
        }
        return parsedFile;
    }

    public static Map<String, Object> yamlParser(String yamlFile) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        Map<String, Object> parsedFile = new HashMap<>();
        try {
            parsedFile = objectMapper.readValue(yamlFile, new TypeReference<>() { });
        } catch (Exception e) {
            System.out.println("Невалидный yaml");
        }
        return parsedFile;
    }
}
