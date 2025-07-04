package io.hexlet;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTests {
    @Test
    public void testDiffer() throws Exception {
        String expected = """
                - follow: false
                  host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                + timeout: 20
                + verbose: true
                """;
        var previousFilePath = Paths.get("./src/test/resources/file1.json").toAbsolutePath().normalize();
        var currentFilePath = Paths.get("./src/test/resources/file2.json").toAbsolutePath().normalize();
        var previousFile = Files.readString(previousFilePath).trim();
        var currentFile = Files.readString(currentFilePath).trim();
        String actual = Differ.generate(previousFile, currentFile);
        assertEquals(expected.replace("\n", ""), actual.replace("\n", ""));
    }
}
