package hexlet.code.formatters;

import hexlet.code.DiffDTO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static hexlet.code.DiffDTO.LineIndicator.ADDED;
import static hexlet.code.DiffDTO.LineIndicator.DELETED;

public class Plain {
    public static String formatAsPlain(List<DiffDTO> difference) {
        List<String> formatedString = new ArrayList<>();
        for (var i = 0; i < difference.size(); i++) {
            if (difference.get(i).lineIndicator().equals(DELETED)
                    && difference.get(i).key().equals(difference.get(i + 1).key())) {
                formatedString.add("Property '" + difference.get(i).key() + "' was updated. From "
                        + formatValue(difference.get(i).value()) + " to "
                        + formatValue(difference.get(i + 1).value()));
                i = i + 1;
            } else if (difference.get(i).lineIndicator().equals(ADDED)) {
                formatedString.add("Property '" + difference.get(i).key()
                        + "' was added with value: " + formatValue(difference.get(i).value()));
            } else if (difference.get(i).lineIndicator().equals(DELETED)) {
                formatedString.add("Property '" + difference.get(i).key() + "' was removed");
            }
        }
        return String.join("\n", formatedString);
    }

    public static String formatValue(Object obj) {
        if (obj instanceof List || obj instanceof LinkedHashMap<?, ?>) {
            return "[complex value]";
        } else if (obj instanceof String) {
            return "'" + obj + "'";
        }
        return obj + "";
    }
}
