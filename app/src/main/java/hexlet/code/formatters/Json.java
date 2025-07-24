package hexlet.code.formatters;

import hexlet.code.DiffDTO;

import java.util.List;

public class Json {
    public static String formatAsJson(List<DiffDTO> difference) {
        List<String> formatedString = new java.util.ArrayList<>(difference.stream()
                .map(line ->
                        "\"" + line.key() + "\": "
                                + formatValue(line.value())
                )
                .toList());
        formatedString.addFirst("{");
        formatedString.add("}");
        return String.join("\n", formatedString);
    }
    public static String formatValue(Object obj) {
        if (obj instanceof String) {
            return "\"" + obj + "\"";
        }
        return obj + "";
    }
}
