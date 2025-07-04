package hexlet.code;

import java.util.*;
import java.util.stream.Collectors;

public class Differ {
    //если искомого ключа нет, то +
    //если ключ есть, но значение отличается -старое +новое
    //если ключ есть и значение такое же, то просто вывод
    //если осталось не проверенное значение, то -

    public static String generate(Map<String, Object> previousFile, Map<String, Object> currentFile) {
        List<DiffDTO> difference = new ArrayList<>();
        for (var line : currentFile.entrySet()) {
            if (previousFile.get(line.getKey()) == null) {
                difference.add(new DiffDTO("+", line.getKey() + ": " + line.getValue()));
            } else if (previousFile.get(line.getKey()).equals(line.getValue())) {
                difference.add(new DiffDTO(" ", line.getKey() + ": " + line.getValue()));
            } else {
                difference.add(new DiffDTO("-",
                        line.getKey() + ": " + line.getValue() + "\n"
                                + "+ " + line.getKey() + ": " + previousFile.get(line.getKey())));
            }
        }

        difference.addAll(previousFile.entrySet().stream()
                .filter(i -> currentFile.get(i.getKey()) == null)
                .map(i -> new DiffDTO("-", i.getKey() + ": " + i.getValue()))
                .toList());
        difference.sort(Comparator.comparing(DiffDTO::keyAndValue));
        return formatter(difference);
    }
    public static String formatter(List<DiffDTO> difference) {
        return difference.stream()
                .map(line -> line.lineIndicator() + " " + line.keyAndValue())
                .collect(Collectors.joining("\n"));
    }
}
