package hexlet.code;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static hexlet.code.DiffDTO.LineIndicator.ADDED;
import static hexlet.code.DiffDTO.LineIndicator.DELETED;
import static hexlet.code.DiffDTO.LineIndicator.NOCHANGES;
import static hexlet.code.Formatter.format;

public class Differ {
    //если искомого ключа нет, то +
    //если ключ есть, но значение отличается -старое +новое
    //если ключ есть и значение такое же, то просто вывод
    //если осталось не проверенное значение, то -

    public static String generate(
            String filepath1,
            String filepath2,
            String formatName
    ) throws Exception {
        Map<String, Object> previousFile = Parser.parse(filepath1);
        Map<String, Object> currentFile = Parser.parse(filepath2);

        List<DiffDTO> difference = new ArrayList<>();

        for (var currentKeyAndValue : currentFile.entrySet()) {
            var currentKey = currentKeyAndValue.getKey();
            var currentValue = currentKeyAndValue.getValue();

            if (!previousFile.containsKey(currentKey)) { //добавлен новый ключ
                difference.add(new DiffDTO(ADDED, currentKey, currentValue));
            } else if (Objects.equals(previousFile.get(currentKey), currentValue)) {
                difference.add(new DiffDTO(NOCHANGES, currentKey, currentValue));
            } else { //значение ключа изменилось
                var previousValue = previousFile.get(currentKey);
                difference.add(new DiffDTO(DELETED, currentKey, previousValue));
                difference.add(new DiffDTO(ADDED, currentKey, currentValue));
            }
        }
        //ключ был удален
        difference.addAll(previousFile.entrySet().stream()
                .filter(previousKeyAndValue ->
                        !currentFile.containsKey(previousKeyAndValue.getKey()))
                .map(previousKeyAndValue ->
                        new DiffDTO(DELETED,
                                previousKeyAndValue.getKey(),
                                previousKeyAndValue.getValue()))
                .toList());
        difference.sort(Comparator.comparing(DiffDTO::key));
        return format(formatName, difference);
    }
}
