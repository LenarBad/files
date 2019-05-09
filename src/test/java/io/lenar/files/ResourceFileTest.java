package io.lenar.files;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ResourceFileTest {

    private static final String TEST_RESOURCE_FILE = "test.txt";
    private static final String TEST_RESOURCE_FILE_LINE_1 = "test line 1";
    private static final String TEST_RESOURCE_FILE_LINE_2 = "test line 2";

    @Test
    public void readResourceFileAsStringTest() {
        ResourceFile resourceFile = new ResourceFile(TEST_RESOURCE_FILE);
        String content = resourceFile.content();
        assertNotNull(content);
        assertTrue(content.contains(TEST_RESOURCE_FILE_LINE_1));
        assertTrue(content.contains(TEST_RESOURCE_FILE_LINE_2));
    }

    @Test
    public void readResourceFileAsLinesTest() {
        ResourceFile resourceFile = new ResourceFile(TEST_RESOURCE_FILE);
        List<String> lines = resourceFile.lines();
        assertNotNull(lines);
        assertFalse(lines.isEmpty());
        assertEquals(lines.get(0), TEST_RESOURCE_FILE_LINE_1);
        assertEquals(lines.get(1), TEST_RESOURCE_FILE_LINE_2);
    }
}
