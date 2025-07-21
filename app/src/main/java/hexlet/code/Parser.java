package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String filepath) throws Exception {
        InputStream is = Parser.class.getClassLoader().getResourceAsStream(filepath);
        String file;
        if (is == null) {
            Path path = Paths.get(filepath).toAbsolutePath().normalize();
            if (!Files.exists(path)) {
                throw new Exception("File '" + path + "' does not exist");
            }
            file = Files.readString(path);
        } else {
            file = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
        var a = filepath.lastIndexOf('.');
        var format = filepath.substring(a + 1);
        if (format.equals("json")) {
            return jsonParser(file);
        } else if (format.equals("yaml") || format.equals("yml")) {
            return yamlParser(file);
        }
        throw new Exception("Unknown format");
    }

    public static Map<String, Object> jsonParser(String jsonFile) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonFile, new TypeReference<>() { });
    }

    public static Map<String, Object> yamlParser(String yamlFile) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(yamlFile, new TypeReference<>() { });
    }
}
