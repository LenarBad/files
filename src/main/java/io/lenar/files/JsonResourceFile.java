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
import com.google.gson.reflect.TypeToken;
import io.lenar.files.base.BaseResourceFile;
import io.lenar.files.interfaces.EzJsonFile;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provides the functionality for reading JSON files in the resources folder
 */
public class JsonResourceFile extends BaseResourceFile implements EzJsonFile {

    public JsonResourceFile(String fileName) {
        super(fileName);
    }

    public <T> T fromJson(Class<T> clazz) {
        return new Gson().fromJson(readContent(), clazz);
    }

    public <T> T fromJson(Type typeOfT) {
        return new Gson().fromJson(readContent(), typeOfT);
    }

    public <T> List<T> fromJsonAsList(Class<T[]> clazz) {
        return Arrays.asList(new Gson().fromJson(readContent(), clazz));
    }

}
