package io.hexlet;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTests {
    @Test
    public void testGenerate() throws Exception {
        String expected = """
                  chars1: [a, b, c]
                - chars2: [d, e, f]
                + chars2: false
                - checked: false
                + checked: true
                - default: null
                + default: [value1, value2]
                - id: 45
                + id: null
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
        String previousFile = "src/test/java/resources/jsonFileTest1.json";
        String currentFile = "src/test/java/resources/jsonFileTest2.json";

        String actual = Differ.generate(previousFile, currentFile, "");
        assertEquals(expected, actual);
    }
}
