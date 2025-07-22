package io.hexlet;

import hexlet.code.Differ;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTests {
    @Test
    public void testJson() throws Exception {
        String expected = """
                - follow: false
                  host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                + timeout: 20
                + verbose: true""";
        Map<String, Object> previousFile = new HashMap<>();
        previousFile.put("host", "hexlet.io");
        previousFile.put("timeout", 50);
        previousFile.put("proxy", "123.234.53.22");
        previousFile.put("follow", false);

        Map<String, Object> currentFile = new HashMap<>();
        currentFile.put("timeout", 20);
        currentFile.put("verbose", true);
        currentFile.put("host", "hexlet.io");

        String actual = Differ.generate(previousFile, currentFile, "");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test with null object")
    public void testJson2() {
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
