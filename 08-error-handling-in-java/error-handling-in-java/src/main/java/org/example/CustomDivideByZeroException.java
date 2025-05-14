package org.example;

public class CustomDivideByZeroException extends Exception {
    private final double a;
    private final double b;

    public CustomDivideByZeroException(double a, double b) {
        super("Cannot divide " + a + " by " + b);
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }
}
