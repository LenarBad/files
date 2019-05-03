package io.lenar.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResourceFile {

    private final String fileName;

    public ResourceFile(String fileName) {
        this.fileName = fileName;
    }

    public List<String> lines() throws IOException {
        return readLines();
    }

    public String string() throws IOException {
        return readLines().stream().collect(Collectors.joining("\n"));
    }

    private List<String> readLines() throws IOException {
        InputStream inputStream = ResourceFile.class.getResourceAsStream("/" + fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> result = reader.lines().collect(Collectors.toList());
        reader.close();
        return result;
    }

}
