package org.example.javahetic;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Incorrect usage. Please use the command as follows:");
            System.out.println("java -cp <your_jar_or_class_directory> org.example.javahetic.Main <directory_path>");
            System.out.println("Example:");
            System.out.println("java -cp target/classes org.example.javahetic.Main /path/to/directory");
            return;
        }

        FileProcessor fileProcessor = new FileProcessor();
        DirectoryProcessor directoryProcessor = new DirectoryProcessor(fileProcessor);
        directoryProcessor.processDirectory(args[0]);
    }
}
