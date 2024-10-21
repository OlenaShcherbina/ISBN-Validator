package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateISBNTest {

    @Test
    public void checkValid10DigitISBN(){
        ValidateISBN validateISBN = new ValidateISBN();

        boolean result = validateISBN.check10DigitISBN("0735211299");
        assertTrue(result, "first valid ISBN");

        boolean result1 = validateISBN.check10DigitISBN("1962795071");
        assertTrue(result1, "second valid ISBN");
    }

    @Test
    public void checkInValid10DigitISBN(){
        ValidateISBN validateISBN = new ValidateISBN();

        boolean result = validateISBN.check10DigitISBN("1234567890");
        assertFalse(result, "first invalid ISBN");
    }

    @Test
    public void nonNumericISBNIsNotAllowed(){
        ValidateISBN validateISBN = new ValidateISBN();

       assertThrows(NumberFormatException.class, () -> validateISBN.isISBNNumeric("helloworld"), "invalid input 'helloworld'");
    }

    @Test
    public void checkValid13DigitISBN(){
        ValidateISBN validateISBN = new ValidateISBN();

        boolean result = validateISBN.check13DigitISBN("9780306406157");
        assertTrue(result, "valid ISBN 978-0-306-40615-7");
    }

    @Test
    public void checkInvalid13DigitISBN(){
        ValidateISBN validateISBN = new ValidateISBN();

        boolean result = validateISBN.check13DigitISBN("9780306406156");
        assertFalse(result, "invalid ISBN 978-0-306-40615-6");
    }

    @Test
    public void checkValidNumberOf10DigitISBN(){
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkNumberOfDigits10DigitISBN("0735211299");
        assertTrue(result);
    }


    @Test
    public void checkInvalidNumberOf10DigitISBN(){
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkNumberOfDigits10DigitISBN("07352112991");
        assertFalse(result);
    }

    @Test
    public void checkValidNumberOf13DigitISBN(){
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkNumberOfDigits13DigitISBN("9780306406157");
        assertTrue(result);
    }


    @Test
    public void checkInvalidNumberOf13DigitISBN(){
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkNumberOfDigits13DigitISBN("07352112991");
        assertFalse(result);
    }

}
