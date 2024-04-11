package org.example.javahetic;

import java.io.IOException;
import java.util.List;

public interface DataReader {
    List<String> readData() throws IOException;
}
