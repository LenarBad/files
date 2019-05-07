package io.lenar.files;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonResourceFile extends ResourceFile {

    public JsonResourceFile(String fileName) {
        super(fileName);
    }

    public <T> T fromJson(Class<T> clazz) {
        return new Gson().fromJson(content(), clazz);
    }

    public <T> T fromJson(Type typeOfT) {
        return new Gson().fromJson(content(), typeOfT);
    }

    public <T> List<T> fromJsonAsList() {
        return new Gson().fromJson(content(), new TypeToken<List<T>>(){}.getType());
    }

}
