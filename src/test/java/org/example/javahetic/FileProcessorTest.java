package org.example.javahetic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class FileProcessorTest {

    private Path tempInputFile;
    private Path tempOutputFile;
    private FileProcessor fileProcessor;

    @BeforeEach
    void setUp(@TempDir Path tempDir) throws IOException {
        fileProcessor = new FileProcessor();
        tempInputFile = tempDir.resolve("test.op");
        tempOutputFile = tempDir.resolve("test.res");
        try (BufferedWriter writer = Files.newBufferedWriter(tempInputFile)) {
            writer.write("2 3 +");
            writer.newLine();
            writer.write("3 2 -");
            writer.newLine();
            writer.write("2 3 *");
        }
    }

    @Test
    void testProcessFile() throws IOException {
        fileProcessor.processFile(tempInputFile);
        Assertions.assertTrue(Files.exists(tempOutputFile));

        // Verify the content of the output file
        String content = Files.readString(tempOutputFile);
        String[] lines = content.split(System.lineSeparator());
        Assertions.assertEquals(3, lines.length);
        Assertions.assertEquals("5", lines[0]);
        Assertions.assertEquals("1", lines[1]);
        Assertions.assertEquals("6", lines[2]);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempInputFile);
        Files.deleteIfExists(tempOutputFile);
    }
}
