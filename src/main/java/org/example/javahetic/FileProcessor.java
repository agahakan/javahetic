package org.example.javahetic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

public class FileProcessor {

    public void processOperations(DataReader dataReader) {
        try {
            List<String> operations = dataReader.readData();
            Path outputPath = Path.of("./output.res");

            if (operations.isEmpty()) {
                System.err.println("No operations to process.");
                return;
            }

            try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
                for (String operation : operations) {
                    String result = compute(operation);
                    writer.write(result);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error processing operations: " + e.getMessage());
        }
    }

    public void processDirectory(Path directoryPath) {
        try (Stream<Path> stream = Files.walk(directoryPath)) {
            stream.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".op"))
                    .forEach(this::processFile);
        } catch (IOException e) {
            System.err.println("Error processing directory: " + e.getMessage());
        }
    }

    public void processFile(Path filePath) {
        Path outputPath = Paths.get(filePath.toString().replaceAll("\\.op$", ".res"));
        try (BufferedReader reader = Files.newBufferedReader(filePath);
             BufferedWriter writer = Files.newBufferedWriter(outputPath)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String result = compute(line);
                if (result != null) {
                    writer.write(result);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }

    private String compute(String inputLine) {
        String[] parts = inputLine.split(" ");
        if (parts.length != 3) {
            return "ERROR";
        }

        try {
            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[1]);
            String operator = parts[2];

            return OperationStrategyFactory.getStrategy(operator)
                    .map(strategy -> strategy.execute(num1, num2))
                    .orElse("ERROR");
        } catch (NumberFormatException e) {
            return "ERROR";
        }
    }
}