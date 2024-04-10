package org.example.javahetic;

public class OperationStrategyFactory {
    public static OperationStrategy getStrategy(String operator) {
        return switch (operator) {
            case "+" -> new AdditionStrategy();
            case "-" -> new SubtractionStrategy();
            case "*" -> new MultiplicationStrategy();
            default -> null;
        };
    }
}
