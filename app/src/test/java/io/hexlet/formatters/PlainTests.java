package io.hexlet.formatters;

import hexlet.code.DiffDTO;
import hexlet.code.formatters.Plain;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlainTests {

    @Test
    public void testFormatValue() {
        String str = "Hello test!";
        List<Integer> list = List.of(1, 2, 3);
        LinkedHashMap<String, Integer> obj = new LinkedHashMap<>();
        obj.put("1", 1);
        obj.put("2", 2);
        obj.put("3", 3);
        int number = 12;

        var expected = "12";
        var actual = Plain.formatValue(number);
        assertEquals(expected, actual);

        expected = "'Hello test!'";
        actual = Plain.formatValue(str);
        assertEquals(expected, actual);

        expected = "[complex value]";
        actual = Plain.formatValue(list);
        assertEquals(expected, actual);

        actual = Plain.formatValue(obj);
        assertEquals(expected, actual);
    }

    @Test
    public void testFormatAsPlain() {
        List<DiffDTO> list = new ArrayList<>();
        list.add(new DiffDTO(DiffDTO.LineIndicator.ADDED, "key1", List.of(1, 2, 3)));
        list.add(new DiffDTO(DiffDTO.LineIndicator.DELETED, "key2", List.of(1, 2, 3)));
        list.add(new DiffDTO(DiffDTO.LineIndicator.DELETED, "key3", null));
        list.add(new DiffDTO(DiffDTO.LineIndicator.ADDED, "key3", "text"));
        list.add(new DiffDTO(DiffDTO.LineIndicator.DELETED, "key4", true));
        list.add(new DiffDTO(DiffDTO.LineIndicator.ADDED, "key4", null));
        list.add(new DiffDTO(DiffDTO.LineIndicator.NOCHANGES, "key5", 1));

        var expected = """
                Property 'key1' was added with value: [complex value]
                Property 'key2' was removed
                Property 'key3' was updated. From null to 'text'
                Property 'key4' was updated. From true to null""";
        var actual = Plain.formatAsPlain(list);
        assertEquals(expected, actual);
    }
}
