package io.hexlet.formatters;

import hexlet.code.DiffDTO;
import hexlet.code.Formatter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatterTests {
    @Test
    public void formatTest() {
        var format = "stylish";
        List<DiffDTO> list = new ArrayList<>();
        list.add(new DiffDTO(DiffDTO.LineIndicator.ADDED, "key1", List.of(1, 2, 3)));
        list.add(new DiffDTO(DiffDTO.LineIndicator.DELETED, "key2", List.of(1, 2, 3)));
        var expected = """
                + key1: [1, 2, 3]
                - key2: [1, 2, 3]""";
        var actual = Formatter.format(format, list);
        assertEquals(expected, actual);

        format = "";
        actual = Formatter.format(format, list);
        assertEquals(expected, actual);

        format = "plain";
        expected = """
                Property 'key1' was added with value: [complex value]
                Property 'key2' was removed""";
        actual = Formatter.format(format, list);
        assertEquals(expected, actual);
    }
}
