package org.example.javahetic;

public class ErrorHandler {

    public void handleException(Exception e) {
        System.err.println("Error: " + e.getMessage());
        showUsage();
    }

    private static void showUsage() {
        System.out.println("Incorrect usage. Please check the implementation type and file path in application.properties:");
        System.out.println("For FILE implementation, ensure file.path is set:");
        System.out.println("implementation=FILE");
        System.out.println("file.path=<path_to_directory>");
    }
}
