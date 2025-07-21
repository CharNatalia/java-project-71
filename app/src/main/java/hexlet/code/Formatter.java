package hexlet.code;

import java.util.List;
import java.util.stream.Collectors;

public class Formatter {
    public static String stylish(List<DiffDTO> difference) {
        return difference.stream()
                .map(DiffDTO::toString)
                .collect(Collectors.joining("\n"));
    }
}
