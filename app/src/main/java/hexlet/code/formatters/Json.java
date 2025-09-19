package hexlet.code.formatters;

import hexlet.code.DiffDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static hexlet.code.DiffDTO.LineIndicator.ADDED;
import static hexlet.code.DiffDTO.LineIndicator.DELETED;
import static hexlet.code.DiffDTO.LineIndicator.UNCHANGED;

public class Json {
    public static String formatAsJson(List<DiffDTO> difference) {
        List<String> formatedString = new ArrayList<>();
        for (var i = 0; i < difference.size(); i++) {
            if (difference.get(i).status().equals(DELETED)
                    && i < difference.size() - 1
                    && difference.get(i).key().equals(difference.get(i + 1).key())) {
                formatedString.add("  {\n    \"key\":  \"" + difference.get(i).key() + "\",\n"
                        + "    \"status\": \"changed\",\n"
                        + "    \"oldValue\": " + formatValue(difference.get(i).value()) + ",\n"
                        + "    \"newValue\": " + formatValue(difference.get(i + 1).value())
                        + "\n  }");
                i = i + 1;
            } else if (difference.get(i).status().equals(ADDED)) {
                formatedString.add("  {\n    \"key\":  \"" + difference.get(i).key() + "\",\n"
                        + "    \"status\": \"added\",\n"
                        + "    \"newValue\": " + formatValue(difference.get(i + 1).value())
                        + "\n  }");
            } else if (difference.get(i).status().equals(DELETED)) {
                formatedString.add("  {\n    \"key\":  \"" + difference.get(i).key() + "\",\n"
                        + "    \"status\": \"deleted\",\n"
                        + "    \"oldValue\": " + formatValue(difference.get(i).value())
                        + "\n  }");
            } else if (difference.get(i).status().equals(UNCHANGED)) {
                formatedString.add("  {\n    \"key\":  \"" + difference.get(i).key() + "\",\n"
                        + "    \"status\": \"unchanged\",\n"
                        + "    \"oldValue\": " + formatValue(difference.get(i).value())
                        + "\n  }");
            }
        }
        return "[\n" + String.join(",\n", formatedString) + "\n]";
    }
    public static String formatValue(Object obj) {
        if (obj instanceof String) {
            return "\"" + obj + "\"";
        }
        if (obj instanceof List<?> list) {
            if (!list.isEmpty() && list.getFirst() instanceof String) {
                List<String> formattedString = new java.util.ArrayList<>(list.stream()
                        .map(Json::formatValue)
                        .toList());
                return "[" + String.join(", ", formattedString) + "]";
            }
        }

        if (obj instanceof Map<?, ?> map) {
            if (!map.isEmpty()) {
                List<String> formattedString = map.entrySet().stream()
                        .map(entry -> "      \"" + entry.getKey() + "\""
                                + ": " + Json.formatValue(entry.getValue()))
                        .toList();

                return "{\n" + String.join(",\n", formattedString) + "\n    }";
            }
        }
        return obj + "";
    }
}
