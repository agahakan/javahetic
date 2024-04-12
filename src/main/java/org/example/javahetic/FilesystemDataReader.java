package org.example.javahetic;

import java.io.BufferedReader;
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

    public Path getDirectoryPath() {
        return directoryPath;
    }

    @Override
    public List<String> readData() throws IOException {
        List<String> operations = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(directoryPath)) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".op"))
                    .forEach(path -> {
                        try (BufferedReader reader = Files.newBufferedReader(path)) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                operations.add(line);
                            }
                        } catch (IOException e) {
                            System.err.println("Failed to read file: " + path);
                        }
                    });
        }
        return operations;
    }
}