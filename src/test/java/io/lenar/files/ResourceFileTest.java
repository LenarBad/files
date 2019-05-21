package io.lenar.files;

import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.testng.Assert.*;

public class ResourceFileTest {

    private static final String TEST_RESOURCE_FILE = "test.txt";
    private static final String TEST_RESOURCE_PROPERTY_FILE = "test.properties";
    private static final String NON_EXISTING_TEST_RESOURCE_FILE = "non-existing-test.txt";

    private static final String TEST_OBJECT_JSON_RESOURCE_FILE = "test-object.json";
    private static final String TEST_OBJECT_LIST_JSON_RESOURCE_FILE = "test-object-list.json";

    private static final String TEST_OBJECT_YAML_RESOURCE_FILE = "test-object.yaml";
    private static final String TEST_OBJECT_LIST_YAML_RESOURCE_FILE = "test-object-list.yaml";

    private static final String TEST_RESOURCE_FILE_LINE_1 = "test line 1";
    private static final String TEST_RESOURCE_FILE_LINE_2 = "test line 2";

    private static final String TEST_OBJECT_VALUE_1 = "testValue1";
    private static final String TEST_OBJECT_VALUE_2 = "testValue2";

    private static final String TEST_PROPERTY_VALUE_1 = "value1";
    private static final String TEST_PROPERTY_VALUE_2 = "value2";
    private static final String TEST_PROPERTY_NAME_1 = "property1";
    private static final String TEST_PROPERTY_NAME_2 = "property2";

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

    @Test(expectedExceptions = FileNotFoundException.class)
    public void noResourceFileNotFoundExceptionOnPropertiesTest() throws IOException {
        new ResourceFile(NON_EXISTING_TEST_RESOURCE_FILE).properties();
    }

    @Test
    public void propertiesTest() throws IOException {
        ResourceFile propertiesFile = new ResourceFile(TEST_RESOURCE_PROPERTY_FILE);
        Properties properties = propertiesFile.properties();
        assertEquals(properties.getProperty(TEST_PROPERTY_NAME_1), TEST_PROPERTY_VALUE_1);
        assertEquals(properties.getProperty(TEST_PROPERTY_NAME_2), TEST_PROPERTY_VALUE_2);
    }

    @Test
    public void fromJsonToObjectTest() throws IOException {
        ResourceFile jsonResourceFile = new ResourceFile(TEST_OBJECT_JSON_RESOURCE_FILE);
        TestObject testObject = jsonResourceFile.fromJson(TestObject.class);
        assertNotNull(testObject);
        assertEquals(testObject.getValue(), TEST_OBJECT_VALUE_1);
    }

    @Test
    public void fromJsonToListOfObjects() throws IOException {
        ResourceFile jsonResourceFile = new ResourceFile(TEST_OBJECT_LIST_JSON_RESOURCE_FILE);
        List<TestObject> list = jsonResourceFile.fromJsonAsList(TestObject[].class);
        assertNotNull(list);
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getValue(), TEST_OBJECT_VALUE_1);
        assertEquals(list.get(1).getValue(), TEST_OBJECT_VALUE_2);
    }

    @Test
    public void fromYamlToObjectTest() throws IOException {
        ResourceFile yamlResourceFile = new ResourceFile(TEST_OBJECT_YAML_RESOURCE_FILE);
        TestObject testObject = yamlResourceFile.fromYaml(TestObject.class);
        assertNotNull(testObject);
        assertEquals(testObject.getValue(), TEST_OBJECT_VALUE_1);
    }

    @Test
    public void fromYamlToListOfObjects() throws IOException {
        ResourceFile yamlResourceFile = new ResourceFile(TEST_OBJECT_LIST_YAML_RESOURCE_FILE);
        List<TestObject> list = yamlResourceFile.fromYamlAsList(TestObject.class);
        assertNotNull(list);
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getValue(), TEST_OBJECT_VALUE_1);
        assertEquals(list.get(1).getValue(), TEST_OBJECT_VALUE_2);
    }

    @Test(expectedExceptions = FileNotFoundException.class)
    public void noResourceFileNotFoundExceptionOnFromJsonAsListTest() throws IOException {
        new ResourceFile(NON_EXISTING_TEST_RESOURCE_FILE).fromJsonAsList(TestObject[].class);
    }

    @Test(expectedExceptions = FileNotFoundException.class)
    public void noResourceFileNotFoundExceptionOnFromJsonTest() throws IOException {
        new ResourceFile(NON_EXISTING_TEST_RESOURCE_FILE).fromJson(TestObject.class);
    }

    @Test(expectedExceptions = FileNotFoundException.class)
    public void noResourceFileNotFoundExceptionOnFromYamlAsListTest() throws IOException {
        new ResourceFile(NON_EXISTING_TEST_RESOURCE_FILE).fromYamlAsList(TestObject.class);
    }

    @Test(expectedExceptions = FileNotFoundException.class)
    public void noResourceFileNotFoundExceptionOnFromYamlTest() throws IOException {
        new ResourceFile(NON_EXISTING_TEST_RESOURCE_FILE).fromYaml(TestObject.class);
    }
}
