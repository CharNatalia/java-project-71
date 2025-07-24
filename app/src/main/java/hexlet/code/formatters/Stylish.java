package hexlet.code.formatters;

import hexlet.code.DiffDTO;

import java.util.List;

public class Stylish {
    public static String formatAsStylish(List<DiffDTO> difference) {
        List<String> formatedString = new java.util.ArrayList<>(difference.stream()
                .map(line ->
                        line.lineIndicator().getIndicator() + " "
                                + line.key() + ": "
                                + line.value()
                )
                .toList());
        formatedString.addFirst("{");
        formatedString.add("}");
        return String.join("\n", formatedString);
    }
}
