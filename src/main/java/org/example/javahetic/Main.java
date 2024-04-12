package org.example.javahetic;

import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        ConfigLoader config = new ConfigLoader();
        String implementationType = config.getProperty("implementation").toUpperCase();

        switch (implementationType) {
            case "JDBC" -> {
                String dbUrl = config.getProperty("database.url");
                String username = config.getProperty("database.username");
                String password = config.getProperty("database.password");
                DataReader dataReader = new JdbcDataReader(dbUrl, username, password);
                processOperations(dataReader);
            }
            case "FILE" -> {
                String directoryPath = config.getProperty("file.path");
                if (directoryPath == null || directoryPath.isEmpty()) {
                    System.out.println("File processing requires the file path specified in application.properties.");
                    showUsage();
                    return;
                }
                DataReader dataReader = new FilesystemDataReader(directoryPath);
                FileProcessor fileProcessor = new FileProcessor();
                fileProcessor.processDirectory(Paths.get(directoryPath));
                fileProcessor.processOperations(dataReader);
            }
            default -> showUsage();
        }
    }

    private static void processOperations(DataReader dataReader) {
        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.processOperations(dataReader);
    }

    private static void showUsage() {
        System.out.println("Incorrect usage. Please check the implementation type and file path in application.properties:");
        System.out.println("For FILE implementation, ensure file.path is set:");
        System.out.println("implementation=FILE");
        System.out.println("file.path=<path_to_directory>");
    }
}
