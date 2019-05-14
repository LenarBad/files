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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Implements the functionality for reading files in any location in the file system
 */
public class UserFile extends BaseFile {

    private final File file;

    public UserFile(String fullFileName) {
        this.file = getFile(fullFileName);
    }

    public UserFile(String path, String fileName) {
        this.file = getFile(path, fileName);
    }

    @Override
    protected InputStream getStream() throws FileNotFoundException {
        return new FileInputStream(this.file);
    }

    private File getFile(String fullFileName) {
        return new File(fullFileName).getAbsoluteFile();
    }

    private File getFile(String parent, String child) {
        return new File(parent, child).getAbsoluteFile();
    }

}
