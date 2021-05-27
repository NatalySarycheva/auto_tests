package org.example;

public class Triangle {
    public double triangleSquare(double a, double b, double c) throws NotExistException {
        if (a < 0 || b < 0 || c < 0) {
            throw new NotExistException();
        }

        double p = (a + b + c) / 2;
        if (p < a || p < b || p < c) {
            throw new NotExistException();
        }
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public static class NotExistException extends Exception {
        public NotExistException() {
            super("Triangle not exists");
        }
    }
}
