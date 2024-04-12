package org.example.javahetic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OperationStrategyTest {

    private OperationStrategy additionStrategy;
    private OperationStrategy subtractionStrategy;
    private OperationStrategy multiplicationStrategy;

    @BeforeEach
    void setUp() {
        // Initialize strategies before each test
        additionStrategy = new AdditionStrategy();
        subtractionStrategy = new SubtractionStrategy();
        multiplicationStrategy = new MultiplicationStrategy();
    }

    @Test
    void additionOfTwoAndThreeShouldReturnFive() {
        // Act
        String result = additionStrategy.execute(2, 3);
        // Assert
        Assertions.assertEquals("5", result, "Addition strategy should correctly add two numbers.");
    }

    @Test
    void subtractionOfTwoFromThreeShouldReturnNegativeOne() {
        // Act
        String result = subtractionStrategy.execute(2, 3);
        // Assert
        Assertions.assertEquals("-1", result, "Subtraction strategy should correctly subtract two numbers.");
    }

    @Test
    void multiplicationOfTwoAndThreeShouldReturnSix() {
        // Act
        String result = multiplicationStrategy.execute(2, 3);
        // Assert
        Assertions.assertEquals("6", result, "Multiplication strategy should correctly multiply two numbers.");
    }
}
