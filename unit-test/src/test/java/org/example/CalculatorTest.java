package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testAddition() {
        //Arrange
        Calculator calculator = new Calculator();

        //Act
        int result = calculator.add(3, 2);

        //Assert
        assertEquals(5, result);
    }

    @Test
    void testSubstraction() {
        //Arrange
        Calculator calculator = new Calculator();

        //Act
        int result = calculator.subtract(3, 2);

        //Assert
        assertEquals(1, result);
    }

    @Test
    void testMultiplication() {
        //Arrange
        Calculator calculator = new Calculator();

        //Act
        int result = calculator.multiply(3, 2);

        //Assert
        assertEquals(6, result);
    }

    @Test
    void testDivision() {
        //Arrange
        Calculator calculator = new Calculator();

        //Act
        double result = calculator.divide(3, 2);

        //Assert
        assertEquals(1.5, result);
    }

    @Test
    void testDivisionByZero() {
        //Arrange
        Calculator calculator = new Calculator();

        // Act & Assert
        assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));
    }
}