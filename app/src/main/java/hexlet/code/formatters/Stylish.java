package hexlet.code.formatters;

import hexlet.code.DiffDTO;

import java.util.List;
import java.util.stream.Collectors;

public class Stylish {
    public static String formatAsStylish(List<DiffDTO> difference) {
        return difference.stream()
                .map(line ->
                        line.lineIndicator().getIndicator() + " "
                                + line.key() + ": "
                                + line.value()
                )
                .collect(Collectors.joining("\n"));
    }
}
