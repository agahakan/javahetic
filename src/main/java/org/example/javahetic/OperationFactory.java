package org.example.javahetic;

public class OperationFactory {
    public static OperationStrategy getOperation(String operator) {
        return switch (operator) {
            case "+" -> new AddOperation();
            case "-" -> new SubtractOperation();
            case "*" -> new MultiplyOperation();
            default -> null;
        };
    }
}
