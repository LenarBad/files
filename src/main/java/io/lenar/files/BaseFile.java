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

import java.io.*;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract
 * BaseFile provides the functionality for reading file content as String or list of String
 *
 * Requires InputStream getStream() to be implemented - the implementation depends on
 * the file location (in the resources folder or not)
 */
public abstract class BaseFile {

    protected abstract InputStream getStream() throws FileNotFoundException;

    public String content() throws IOException {
        return readContent();
    }

    public List<String> lines() throws IOException {
        return readLines();
    }

    public <T> T fromJson(Class<T> clazz) throws IOException {
        return new Gson().fromJson(content(), clazz);
    }

    public <T> List<T> fromJsonAsList(Class<T[]> clazz) throws IOException {
        return Arrays.asList(new Gson().fromJson(content(), clazz));
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
