package org.example.javahetic;

import java.util.Optional;

public class OperationStrategyFactory {
    public static Optional<OperationStrategy> getStrategy(String operator) {
        return switch (operator) {
            case "+" -> Optional.of(new AdditionStrategy());
            case "-" -> Optional.of(new SubtractionStrategy());
            case "*" -> Optional.of(new MultiplicationStrategy());
            default -> Optional.empty();
        };
    }
}
