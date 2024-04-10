package org.example.javahetic;

public class MultiplyOperation implements OperationStrategy {
    @Override
    public String execute(int num1, int num2) {
        return String.valueOf(num1 * num2);
    }
}
