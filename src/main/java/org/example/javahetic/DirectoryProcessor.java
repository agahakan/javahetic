package org.example.javahetic;

import java.io.IOException;
import java.nio.file.*;

public class DirectoryProcessor {

    private final FileProcessor fileProcessor;

    public DirectoryProcessor(FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    public void processDirectory(String directoryPath) {
        Path dirPath = Paths.get(directoryPath);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath, "*.op")) {
            for (Path entry : stream) {
                fileProcessor.processFile(entry);
            }
        } catch (IOException e) {
            System.err.println("Error processing directory: " + e.getMessage());
        }
    }
}
