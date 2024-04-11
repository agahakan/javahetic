package org.example.javahetic;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FilesystemDataReader implements DataReader {

    private final Path directoryPath;

    public FilesystemDataReader(String directoryPath) {
        this.directoryPath = Paths.get(directoryPath);
    }

    @Override
    public List<String> readData() throws IOException {
        List<String> operations = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(directoryPath)) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".op"))
                    .forEach(path -> {
                        // Logic to read files and extract operations
                        // Add extracted operations to the operations list
                    });
        }
        return operations;
    }
}
