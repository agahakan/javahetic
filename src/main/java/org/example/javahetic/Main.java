package org.example.javahetic;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            showUsage();
            return;
        }

        String dataSourceType = args[0].toUpperCase();
        ConfigLoader config = new ConfigLoader();

        switch (dataSourceType) {
            case "JDBC":
                String dbUrl = config.getProperty("db.url");
                String username = config.getProperty("db.user");
                String password = config.getProperty("db.password");
                DataReader dataReader = new JdbcDataReader(dbUrl, username, password);
                processOperations(dataReader);
                break;
            case "FILE":
                if (args.length != 2) {
                    System.out.println("File processing requires the directory path as an argument.");
                    showUsage();
                    return;
                }
                String directoryPath = args[1];
                dataReader = new FilesystemDataReader(directoryPath);
                processOperations(dataReader);
                break;
            default:
                showUsage();
                break;
        }
    }

    private static void processOperations(DataReader dataReader) {
        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.processOperations(dataReader);
    }

    private static void showUsage() {
        System.out.println("Incorrect usage. Please specify the data source type and relevant parameters:");
        System.out.println("For file processing: java -cp <your_jar_or_class_directory> org.example.javahetic.Main FILE <directory_path>");
        System.out.println("For JDBC: java -cp <your_jar_or_class_directory> org.example.javahetic.Main JDBC");
        System.out.println("Example for FILE:");
        System.out.println("java -cp target/classes org.example.javahetic.Main FILE /path/to/directory");
        System.out.println("Example for JDBC:");
        System.out.println("java -cp target/classes org.example.javahetic.Main JDBC");
    }
}
