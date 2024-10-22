package com.example;

public class ValidateISBN {

    public boolean isISBN(String isbn){
       if(!isISBNNumeric(isbn)){
           return false;
       }

        if(checkNumberOfDigits10DigitISBN(isbn)){
            if(check10DigitISBN(isbn)){
                return true;
            }else {
                return false;
            }
        }else if(checkNumberOfDigits13DigitISBN(isbn)){
            if(check13DigitISBN(isbn)){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public boolean check10DigitISBN(String isbn){

       // long numericISBN = Long.parseLong(isbn);
        int total = 0;
        for (int i = 0; i < isbn.length(); i++) {
            total += Character.getNumericValue(isbn.charAt(i)) * (10 - i); //(isbn.charAr(i) - '0') * (10-i)
        }

        if (total % 11 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean check13DigitISBN(String isbn){
        //long numericISBN = Long.parseLong(isbn);
        int total = 0;
        for(int i = 0; i < isbn.length(); i++){
            if(i % 2 == 0) {
                total += Character.getNumericValue(isbn.charAt(i));
            }else{
                total += Character.getNumericValue(isbn.charAt(i)) * 3;
            }
        }

        if(total % 10 == 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean checkNumberOfDigits10DigitISBN(String isbn){
        if(isbn.length() == 10){
            return true;
        }else {
            return false;
        }
    }

    public boolean checkNumberOfDigits13DigitISBN(String isbn){
        if(isbn.length() == 13){
            return true;
        }else {
            return false;
        }
    }

    public boolean isISBNNumeric(String isbn){
        long numericISBN = Long.parseLong(isbn);
        return true;
    }
}
