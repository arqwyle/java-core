package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void isPalindrome_level_ReturnsTrue() {
        //Arrange
        StringUtils stringUtils = new StringUtils();

        //Act
        boolean result = stringUtils.isPalindrome("level");

        //Assert
        assertTrue(result);
    }

    @Test
    void isPalindrome_hello_ReturnsFalse() {
        //Arrange
        StringUtils stringUtils = new StringUtils();

        //Act
        boolean result = stringUtils.isPalindrome("hello");

        //Assert
        assertFalse(result);
    }

    @Test
    void isPalindrome_empty_ReturnsTrue() {
        //Arrange
        StringUtils stringUtils = new StringUtils();

        //Act
        boolean result = stringUtils.isPalindrome("");

        //Assert
        assertTrue(result);
    }

    @Test
    void isPalindrome_null_ReturnsFalse() {
        //Arrange
        StringUtils stringUtils = new StringUtils();

        //Act
        boolean result = stringUtils.isPalindrome(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void isPalindrome_RaceCar_ReturnsTrue() {
        //Arrange
        StringUtils stringUtils = new StringUtils();

        //Act
        boolean result = stringUtils.isPalindrome("RaceCar");

        //Assert
        assertTrue(result);
    }

    @Test
    void countVowels_hello_Returns2() {
        //Arrange
        StringUtils stringUtils = new StringUtils();

        //Act
        int result = stringUtils.countVowels("hello");

        //Assert
        assertEquals(2, result);
    }

    @Test
    void countVowels_xуz_Returns0() {
        //Arrange
        StringUtils stringUtils = new StringUtils();

        //Act
        int result = stringUtils.countVowels("xуz");

        //Assert
        assertEquals(0, result);
    }

    @Test
    void countVowels_empty_Returns0() {
        //Arrange
        StringUtils stringUtils = new StringUtils();

        //Act
        int result = stringUtils.countVowels("");

        //Assert
        assertEquals(0, result);
    }

    @Test
    void countVowels_null_Returns0() {
        //Arrange
        StringUtils stringUtils = new StringUtils();

        //Act
        int result = stringUtils.countVowels(null);

        //Assert
        assertEquals(0, result);
    }

    @Test
    void countVowels_AEIOU_Returns5() {
        //Arrange
        StringUtils stringUtils = new StringUtils();

        //Act
        int result = stringUtils.countVowels("AEIOU");

        //Assert
        assertEquals(5, result);
    }
}