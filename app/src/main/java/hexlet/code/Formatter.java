package hexlet.code;

import java.util.List;

import static hexlet.code.formatters.Json.formatAsJson;
import static hexlet.code.formatters.Plain.formatAsPlain;
import static hexlet.code.formatters.Stylish.formatAsStylish;

public class Formatter {
    public static String format(String formatName, List<DiffDTO> difference) {
        return switch (formatName.toLowerCase()) {
            case "plain" -> formatAsPlain(difference);
            case "json" -> formatAsJson(difference);
            default -> formatAsStylish(difference);
        };
    }
}
