package io.hexlet;

import hexlet.code.Parser;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTests {
    @Test
    public void testReadFile() throws Exception {
        var expected = """
                {
                  "host": "hexlet.io",
                  "timeout": 50,
                  "proxy": "123.234.53.22",
                  "follow": false
                }""";
        var actual = Parser.readFile("src/test/java/resources/file1.json");
        assertEquals(expected, actual);
        //actual = Parser.readFile("/Users/natalamuratova/java-project-71/app/src/test/java/resources/file1.json");
        //assertEquals(expected, actual);
    }
    @Test
    public  void testParseJson() throws Exception {
        Map<String, Object> expected = new HashMap<>();
        expected.put("host", "hexlet.io");
        expected.put("timeout", 50);
        expected.put("proxy", "123.234.53.22");
        expected.put("follow", false);

        var actual = Parser.parse("src/test/java/resources/file1.json");
        assertEquals(expected, actual);
    }
    @Test
    public  void testParseYaml() throws Exception {
        Map<String, Object> expected = new HashMap<>();
        expected.put("host", "hexlet.io");
        expected.put("timeout", 50);
        expected.put("proxy", "123.234.53.22");
        expected.put("follow", false);

        var actual = Parser.parse("src/test/java/resources/yamlFileTest1.yaml");
        assertEquals(expected, actual);

        actual = Parser.parse("src/test/java/resources/yamlFileTest2.yml");
        assertEquals(expected, actual);
    }
}
