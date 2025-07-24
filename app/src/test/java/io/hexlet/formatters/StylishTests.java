package io.hexlet.formatters;

import hexlet.code.DiffDTO;
import hexlet.code.formatters.Stylish;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StylishTests {
    @Test
    public void testFormatAsStylish() {
        List<DiffDTO> list = new ArrayList<>();
        list.add(new DiffDTO(DiffDTO.LineIndicator.ADDED, "key1", List.of(1, 2, 3)));
        list.add(new DiffDTO(DiffDTO.LineIndicator.DELETED, "key2", List.of(1, 2, 3)));
        list.add(new DiffDTO(DiffDTO.LineIndicator.DELETED, "key3", null));
        list.add(new DiffDTO(DiffDTO.LineIndicator.ADDED, "key3", "text"));
        list.add(new DiffDTO(DiffDTO.LineIndicator.DELETED, "key4", true));
        list.add(new DiffDTO(DiffDTO.LineIndicator.ADDED, "key4", null));
        list.add(new DiffDTO(DiffDTO.LineIndicator.NOCHANGES, "key5", 1));

        var expected = """
                {
                  + key1: [1, 2, 3]
                  - key2: [1, 2, 3]
                  - key3: null
                  + key3: text
                  - key4: true
                  + key4: null
                    key5: 1
                }""";
        var actual = Stylish.formatAsStylish(list);
        assertEquals(expected, actual);
    }
}
