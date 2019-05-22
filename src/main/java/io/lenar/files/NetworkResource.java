package io.lenar.files;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class NetworkResource extends Resource {

    private String stringUrl;

    public NetworkResource(String stringUrl) {
        this.stringUrl = stringUrl;
    }

    @Override
    protected InputStream getStream() throws IOException {
        return new URL(stringUrl).openStream();
    }
}
