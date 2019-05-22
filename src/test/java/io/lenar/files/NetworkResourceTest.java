/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Lenar Badretdinov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.lenar.files;

import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Properties;

import static org.testng.Assert.*;

public class NetworkResourceTest {

    private static final String BASE_URL = "https://raw.githubusercontent.com/LenarBad/files/master/src/test/resources/";

    private static final String INVALID_URL = "invalid-url.txt";

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

    @Test(enabled = false)
    public void readUrlFileAsStringTest() throws IOException {
        NetworkResource file = new NetworkResource(BASE_URL + TEST_RESOURCE_FILE);
        String content = file.content();
        assertNotNull(content);
        assertTrue(content.contains(TEST_RESOURCE_FILE_LINE_1));
        assertTrue(content.contains(TEST_RESOURCE_FILE_LINE_2));
    }

    @Test(enabled = false)
    public void readUrlFileAsLinesTest() throws IOException {
        NetworkResource file = new NetworkResource(BASE_URL + TEST_RESOURCE_FILE);
        List<String> lines = file.lines();
        assertNotNull(lines);
        assertFalse(lines.isEmpty());
        assertEquals(lines.get(0), TEST_RESOURCE_FILE_LINE_1);
        assertEquals(lines.get(1), TEST_RESOURCE_FILE_LINE_2);
    }

    @Test(enabled = false)
    public void propertiesTest() throws IOException {
        NetworkResource file = new NetworkResource(BASE_URL + TEST_RESOURCE_PROPERTY_FILE);
        Properties properties = file.properties();
        assertEquals(properties.getProperty(TEST_PROPERTY_NAME_1), TEST_PROPERTY_VALUE_1);
        assertEquals(properties.getProperty(TEST_PROPERTY_NAME_2), TEST_PROPERTY_VALUE_2);
    }

    @Test(enabled = false)
    public void fromJsonToObjectTest() throws IOException {
        NetworkResource file = new NetworkResource(BASE_URL + TEST_OBJECT_JSON_RESOURCE_FILE);
        TestObject testObject = file.fromJson(TestObject.class);
        assertNotNull(testObject);
        assertEquals(testObject.getValue(), TEST_OBJECT_VALUE_1);
    }

    @Test(enabled = false)
    public void fromJsonToListOfObjects() throws IOException {
        NetworkResource file = new NetworkResource(BASE_URL + TEST_OBJECT_LIST_JSON_RESOURCE_FILE);
        List<TestObject> list = file.fromJsonAsList(TestObject[].class);
        assertNotNull(list);
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getValue(), TEST_OBJECT_VALUE_1);
        assertEquals(list.get(1).getValue(), TEST_OBJECT_VALUE_2);
    }

    @Test(enabled = false)
    public void fromYamlToObjectTest() throws IOException {
        NetworkResource file = new NetworkResource(BASE_URL + TEST_OBJECT_YAML_RESOURCE_FILE);
        TestObject testObject = file.fromYaml(TestObject.class);
        assertNotNull(testObject);
        assertEquals(testObject.getValue(), TEST_OBJECT_VALUE_1);
    }

    @Test(enabled = false)
    public void fromYamlToListOfObjects() throws IOException {
        NetworkResource file = new NetworkResource(BASE_URL + TEST_OBJECT_LIST_YAML_RESOURCE_FILE);
        List<TestObject> list = file.fromYamlAsList(TestObject.class);
        assertNotNull(list);
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getValue(), TEST_OBJECT_VALUE_1);
        assertEquals(list.get(1).getValue(), TEST_OBJECT_VALUE_2);
    }

    @Test(expectedExceptions = FileNotFoundException.class, enabled = false)
    public void noUrlFileNotFoundExceptionOnReadLinesTest() throws IOException {
        new NetworkResource(BASE_URL + NON_EXISTING_TEST_RESOURCE_FILE).lines();
    }

    @Test(expectedExceptions = FileNotFoundException.class, enabled = false)
    public void noUrlFileNotFoundExceptionOnReadContentTest() throws IOException {
        new NetworkResource(BASE_URL + NON_EXISTING_TEST_RESOURCE_FILE).content();
    }

    @Test(expectedExceptions = FileNotFoundException.class, enabled = false)
    public void noUrlFileNotFoundExceptionOnPropertiesTest() throws IOException {
        new NetworkResource(BASE_URL + NON_EXISTING_TEST_RESOURCE_FILE).properties();
    }

    @Test(expectedExceptions = FileNotFoundException.class, enabled = false)
    public void noUrlFileNotFoundExceptionOnFromJsonAsListTest() throws IOException {
        new NetworkResource(BASE_URL + NON_EXISTING_TEST_RESOURCE_FILE).fromJsonAsList(TestObject[].class);
    }

    @Test(expectedExceptions = FileNotFoundException.class, enabled = false)
    public void noUrlFileNotFoundExceptionOnFromJsonTest() throws IOException {
        new NetworkResource(BASE_URL + NON_EXISTING_TEST_RESOURCE_FILE).fromJson(TestObject.class);
    }

    @Test(expectedExceptions = FileNotFoundException.class, enabled = false)
    public void noUrlFileNotFoundExceptionOnFromYamlAsListTest() throws IOException {
        new NetworkResource(BASE_URL + NON_EXISTING_TEST_RESOURCE_FILE).fromYamlAsList(TestObject.class);
    }

    @Test(expectedExceptions = FileNotFoundException.class, enabled = false)
    public void noUrlFileNotFoundExceptionOnFromYamlTest() throws IOException {
        new NetworkResource(BASE_URL + NON_EXISTING_TEST_RESOURCE_FILE).fromYaml(TestObject.class);
    }

    @Test(expectedExceptions = MalformedURLException.class)
    public void invalidUrlMalformedURLExceptionOnReadLinesTest() throws IOException {
        new NetworkResource(INVALID_URL).lines();
    }

    @Test(expectedExceptions = MalformedURLException.class)
    public void invalidUrlMalformedURLExceptionOnReadContentTest() throws IOException {
        new NetworkResource(INVALID_URL).content();
    }

    @Test(expectedExceptions = MalformedURLException.class)
    public void invalidUrlMalformedURLExceptionOnPropertiesTest() throws IOException {
        new NetworkResource(INVALID_URL).properties();
    }

    @Test(expectedExceptions = MalformedURLException.class)
    public void invalidUrlMalformedURLExceptionOnFromJsonAsListTest() throws IOException {
        new NetworkResource(INVALID_URL).fromJsonAsList(TestObject[].class);
    }

    @Test(expectedExceptions = MalformedURLException.class)
    public void invalidUrlMalformedURLExceptionOnFromJsonTest() throws IOException {
        new NetworkResource(INVALID_URL).fromJson(TestObject.class);
    }

    @Test(expectedExceptions = MalformedURLException.class)
    public void invalidUrlMalformedURLExceptionOnFromYamlAsListTest() throws IOException {
        new NetworkResource(INVALID_URL).fromYamlAsList(TestObject.class);
    }

    @Test(expectedExceptions = MalformedURLException.class)
    public void invalidUrlMalformedURLExceptionOnFromYamlTest() throws IOException {
        new NetworkResource(INVALID_URL).fromYaml(TestObject.class);
    }

}
