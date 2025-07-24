package io.hexlet;

import hexlet.code.Differ;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTests {
    @Test
    public void testGenerate() {
        String expected = """
                  chars1: [a, b, c]
                - chars2: [d, e, f]
                + chars2: false
                - checked: false
                + checked: true
                - key1: value1
                + key2: value2
                  numbers1: [1, 2, 3, 4]
                - numbers2: [2, 3, 4, 5]
                + numbers2: [22, 33, 44, 55]
                - numbers3: [3, 4, 5]
                + numbers4: [4, 5, 6]
                + obj1: {nestedKey=value, isNested=true}
                - setting1: Some value
                + setting1: Another value
                - setting2: 200
                + setting2: 300
                - setting3: true
                + setting3: none""";
        Map<String, Object> previousFile = new HashMap<>();
        previousFile.put("setting1", "Some value");
        previousFile.put("setting2", 200);
        previousFile.put("setting3", true);
        previousFile.put("key1", "value1");
        previousFile.put("numbers1", List.of(1, 2, 3, 4));
        previousFile.put("numbers2", List.of(2, 3, 4, 5));
        previousFile.put("checked", false);
        previousFile.put("numbers3", List.of(3, 4, 5));
        previousFile.put("chars1", List.of("a", "b", "c"));
        previousFile.put("chars2", List.of("d", "e", "f"));

        Map<String, Object> currentFile = new HashMap<>();
        currentFile.put("setting1", "Another value");
        currentFile.put("setting2", 300);
        currentFile.put("setting3", "none");
        currentFile.put("key2", "value2");
        currentFile.put("numbers1", List.of(1, 2, 3, 4));
        currentFile.put("numbers2", List.of(22, 33, 44, 55));
        currentFile.put("checked", true);
        currentFile.put("numbers4", List.of(4, 5, 6));
        currentFile.put("chars1", List.of("a", "b", "c"));
        currentFile.put("chars2", false);
        LinkedHashMap<String, Object> obj = new LinkedHashMap<>();
        obj.put("nestedKey", "value");
        obj.put("isNested", true);
        currentFile.put("obj1", obj);

        String actual = Differ.generate(previousFile, currentFile, "");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test with null object")
    public void testGenerate2() {
        String expected = """
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null""";
        Map<String, Object> previousFile = new HashMap<>();
        previousFile.put("id", 45);
        previousFile.put("default", null);

        Map<String, Object> currentFile = new HashMap<>();
        currentFile.put("id", null);
        currentFile.put("default", List.of("value1", "value2"));

        String actual = Differ.generate(previousFile, currentFile, "");
        assertEquals(expected, actual);
    }
}
