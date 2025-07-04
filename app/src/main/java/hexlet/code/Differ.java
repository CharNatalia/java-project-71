package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Differ {
    //если искомого ключа нет, то +
    //если ключ есть, но значение отличается -старое +новое
    //если ключ есть и значение такое же, то просто вывод
    //если осталось не проверенное значение, то -

    public static String generate(String jsonFile1, String jsonFile2) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> previousFile
                = objectMapper.readValue(jsonFile1, new TypeReference<Map<String, Object>>() { });
        Map<String, Object> currentFile
                = objectMapper.readValue(jsonFile2, new TypeReference<Map<String, Object>>() { });

        List<DiffDTO> difference = new ArrayList<>();
        for (var line : currentFile.entrySet()) {
            if (previousFile.get(line.getKey()) == null) {
                difference.add(new DiffDTO("+", line.getKey() + ": " + line.getValue()));
            } else if (previousFile.get(line.getKey()).equals(line.getValue())) {
                difference.add(new DiffDTO(" ", line.getKey() + ": " + line.getValue()));
            } else {
                difference.add(new DiffDTO("-",
                        line.getKey() + ": " + previousFile.get(line.getKey()) + "\n"
                                + "+ " + line.getKey() + ": " + line.getValue()));
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
