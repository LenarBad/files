package io.lenar.files;

import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

public class ResourceFileTest {

    private static final String TEST_RESOURCE_FILE = "test.txt";
    private static final String NON_EXISTING_TEST_RESOURCE_FILE = "non-existing-test.txt";

    private static final String TEST_RESOURCE_FILE_LINE_1 = "test line 1";
    private static final String TEST_RESOURCE_FILE_LINE_2 = "test line 2";

    @Test
    public void readResourceFileAsStringTest() throws IOException {
        ResourceFile resourceFile = new ResourceFile(TEST_RESOURCE_FILE);
        String content = resourceFile.content();
        assertNotNull(content);
        assertTrue(content.contains(TEST_RESOURCE_FILE_LINE_1));
        assertTrue(content.contains(TEST_RESOURCE_FILE_LINE_2));
    }

    @Test
    public void readResourceFileAsLinesTest() throws IOException {
        ResourceFile resourceFile = new ResourceFile(TEST_RESOURCE_FILE);
        List<String> lines = resourceFile.lines();
        assertNotNull(lines);
        assertFalse(lines.isEmpty());
        assertEquals(lines.get(0), TEST_RESOURCE_FILE_LINE_1);
        assertEquals(lines.get(1), TEST_RESOURCE_FILE_LINE_2);
    }

    @Test(expectedExceptions = FileNotFoundException.class)
    public void noResourceFileNotFoundExceptionOnReadLinesTest() throws IOException {
        new ResourceFile(NON_EXISTING_TEST_RESOURCE_FILE).lines();
    }

    @Test(expectedExceptions = FileNotFoundException.class)
    public void noResourceFileNotFoundExceptionOnReadContentTest() throws IOException {
        new ResourceFile(NON_EXISTING_TEST_RESOURCE_FILE).content();
    }
}
