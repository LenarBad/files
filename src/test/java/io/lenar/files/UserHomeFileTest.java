package io.lenar.files;

import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class UserHomeFileTest {

    private static final String NON_EXISTING_TEST_RESOURCE_FILE = "non-existing-test.txt";

    @Test(expectedExceptions = FileNotFoundException.class)
    public void fileNotFoundExceptionTestOnReadContentTest() throws IOException {
        new UserHomeFile(NON_EXISTING_TEST_RESOURCE_FILE).content();
    }

    @Test(expectedExceptions = FileNotFoundException.class)
    public void fileNotFoundExceptionTestOnReadLinesTest() throws IOException {
        new UserHomeFile(NON_EXISTING_TEST_RESOURCE_FILE).lines();
    }

    @Test(expectedExceptions = FileNotFoundException.class)
    public void fileNotFoundExceptionTestOnPropertiesTest() throws IOException {
        new UserHomeFile(NON_EXISTING_TEST_RESOURCE_FILE).properties();
    }

}
