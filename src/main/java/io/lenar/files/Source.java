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

import com.google.gson.Gson;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Abstract
 * Source provides the functionality for reading source's content
 *
 * Requires InputStream getStream() to be implemented - the implementation depends on
 * the source location/type
 */
public abstract class Source {

    /**
     * Returns InputStream for accessing source's content
     * @throws IOException
     */
    protected abstract InputStream getStream() throws IOException;

    public String content() throws IOException {
        return readContent();
    }

    public List<String> lines() throws IOException {
        return readLines();
    }

    public Properties properties() throws IOException {
        Properties properties = new Properties();
        properties.load(new StringReader(readContent()));
        return properties;
    }

    public <T> T fromJson(Class<T> clazz) throws IOException {
        return new Gson().fromJson(content(), clazz);
    }

    public <T> List<T> fromJsonAsList(Class<T[]> clazz) throws IOException {
        return Arrays.asList(new Gson().fromJson(content(), clazz));
    }

    public <T> T fromYaml(Class<T> clazz) throws IOException {
        try (InputStream inputStream = getStream();) {
            return new Yaml(new Constructor(clazz)).load(inputStream);
        } catch (IOException e) {
            throw e;
        }
    }

    public <T> List<T> fromYamlAsList(Class<T> clazz) throws IOException {
        try (InputStream inputStream = getStream();) {
            List<T> list = new ArrayList<>();
            new Yaml(new Constructor(clazz)).loadAll(inputStream)
                    .iterator()
                    .forEachRemaining(item -> list.add((T) item));
            return list;
        } catch (IOException e) {
            throw e;
        }
    }

    private String readContent() throws IOException {
        // Using try-catch just for safe closing resources in case of IOException.
        try (InputStream inputStream = getStream();
             ByteArrayOutputStream result = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString();
        } catch (IOException e) {
            throw e;
        }
    }

    private List<String> readLines() throws IOException {
        // Using try-catch just for safe closing resources in case of IOException.
        try (InputStream inputStream = getStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw e;
        }
    }

}
