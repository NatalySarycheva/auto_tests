package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TriangleTest {

    private Triangle triangle;

    @BeforeEach
    void init() {
        triangle = new Triangle();
    }

    @Test
    public void whenAllSitesAreZeroThenSquareIsEqualToZero() throws Exception {
        assertEquals(triangle.triangleSquare(0,0,0), 0);
    }

    @Test
    public void shouldReturnNonZeroSquareForValidTriangle() throws Exception {
        assertEquals(triangle.triangleSquare(3,4,5), 6 );
    }

    @Test
    public void whenSemiPerimeterIsEqualToOneSideThenSquareIsEqualToZero() throws Exception {
        assertEquals(triangle.triangleSquare(1, 2, 3), 0);
    }

    @Test
    public void whenOneSideIsNegativeThenShouldThrowTriangleNotExistException() {
        Throwable exception = assertThrows(
                Triangle.NotExistException.class, () -> {
                    triangle.triangleSquare(-3, 4, -5);
                }
        );

        assertEquals(exception.getMessage(), "Triangle not exists");
    }


    @Test
    public void whenOneSideGreaterThanSemiPerimeterThenShouldThrowTriangleNotExistException() {
        Throwable exception = assertThrows(
                Triangle.NotExistException.class, () -> {
                    triangle.triangleSquare(3, 8, 4);
                }
        );

        assertEquals(exception.getMessage(), "Triangle not exists");
    }
}


