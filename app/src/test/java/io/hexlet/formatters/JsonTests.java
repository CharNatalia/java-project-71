//package io.hexlet.formatters;
//import hexlet.code.DiffDTO;
//import hexlet.code.formatters.Json;
//import org.junit.jupiter.api.Test;
//
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//public class JsonTests {
//
//    private static Path getFixturePath(String fileName) {
//        return Paths.get("src", "test", "resources", "resources", fileName)
//                .toAbsolutePath().normalize();
//    }
//
//    private static String readFixture(String fileName) throws Exception {
//        Path filePath = getFixturePath(fileName);
//        return Files.readString(filePath).trim();
//    }
//
//    @Test
//    public void test1() {
//        List<DiffDTO> list = new ArrayList<>();
//        list.add(new DiffDTO(DiffDTO.LineIndicator.ADDED, "key1", List.of(1, 2, 3)));
//        list.add(new DiffDTO(DiffDTO.LineIndicator.DELETED, "key2", List.of(1, 2, 3)));
//        list.add(new DiffDTO(DiffDTO.LineIndicator.DELETED, "key3", null));
//        list.add(new DiffDTO(DiffDTO.LineIndicator.ADDED, "key3", "text"));
//        list.add(new DiffDTO(DiffDTO.LineIndicator.DELETED, "key4", true));
//        list.add(new DiffDTO(DiffDTO.LineIndicator.ADDED, "key4", null));
//        list.add(new DiffDTO(DiffDTO.LineIndicator.UNCHANGED, "key5", 1));
//        list.add(new DiffDTO(DiffDTO.LineIndicator.UNCHANGED, "key6", 1));
//
//        var expected
//        var actual = Json.formatAsJson(list);
//
//        assertEquals(expected, actual);
//
//    }
//}
