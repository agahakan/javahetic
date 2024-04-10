package org.example.javahetic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOperationProcessor {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java FileOperationProcessor <directory path>");
            return;
        }

        Path dirPath = Paths.get(args[0]);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath, "*.op")) {
            for (Path entry : stream) {
                processFile(entry);
            }
        } catch (IOException e) {
            System.err.println("Error processing directory: " + e.getMessage());
        }
    }

    private static void processFile(Path filePath) {
        Path outputPath = Paths.get(filePath.toString().replaceAll("\\.op$", ".res"));
        try (BufferedReader reader = Files.newBufferedReader(filePath);
             BufferedWriter writer = Files.newBufferedWriter(outputPath)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String result = compute(line);
                writer.write(result);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }

    private static String compute(String inputLine) {
        String[] parts = inputLine.split(" ");
        if (parts.length != 3) {
            return "ERROR";
        }

        try {
            int num1 = Integer.parseInt(parts[0]);
            int num2 =  Integer.parseInt(parts[1]);
            String operator = parts[2];
            OperationStrategy strategy = OperationFactory.getOperation(operator);
            if (strategy == null) {
                return "ERROR";
            }
            return strategy.execute(num1, num2);
        } catch (NumberFormatException e) {
            return "ERROR";
        }
    }
}
