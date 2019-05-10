package io.lenar.files;

import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UserHomeFileTest {

    private static final String NON_EXISTING_TEST_RESOURCE_FILE = "non-existing-test.txt";

    @Test(expectedExceptions = FileNotFoundException.class)
    public void fileNotFoundExceptionTestOnReadContent() throws IOException {
        new UserHomeFile(NON_EXISTING_TEST_RESOURCE_FILE).content();
    }

    @Test(expectedExceptions = FileNotFoundException.class)
    public void fileNotFoundExceptionTestOnReadLines() throws IOException {
        new UserHomeFile(NON_EXISTING_TEST_RESOURCE_FILE).lines();
    }

}
