package io.lenar.files;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class JsonResourceFileTest {

    private static final String TEST_OBJECT_JSON_RESOURCE_FILE = "test-object.json";
    private static final String TEST_OBJECT_LIST_JSON_RESOURCE_FILE = "test-object-list.json";
    private static final String TEST_OBJECT_VALUE_1 = "testValue1";
    private static final String TEST_OBJECT_VALUE_2 = "testValue2";

    @Test
    public void fromJsonToObjectTest() {
        JsonResourceFile jsonResourceFile = new JsonResourceFile(TEST_OBJECT_JSON_RESOURCE_FILE);
        TestObject testObject = jsonResourceFile.fromJson(TestObject.class);
        assertNotNull(testObject);
        assertEquals(testObject.getValue(), TEST_OBJECT_VALUE_1);
    }

    @Test
    public void fromJsonToListOfObjects() {
        JsonResourceFile jsonResourceFile = new JsonResourceFile(TEST_OBJECT_LIST_JSON_RESOURCE_FILE);
        List<TestObject> list = jsonResourceFile.fromJsonAsList(TestObject[].class);
        assertNotNull(list);
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getValue(), TEST_OBJECT_VALUE_1);
        assertEquals(list.get(1).getValue(), TEST_OBJECT_VALUE_2);
    }
}
