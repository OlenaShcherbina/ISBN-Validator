package com.example;

import static org.mockito.Mockito.mock;

public class StockManagerNew {
    // 1. Re-write everything from scratch.
    private ExternalDataServiceISBN database;
    private ExternalDataServiceISBN webService;

    public void setDatabaseISBN(ExternalDataServiceISBN database){
        this.database = database;
    }

    public void setWebServiceISBN(ExternalDataServiceISBN webService){
        this.webService = webService;
    }


    public String getLocatorCode(String isbn){
    /*
        ExternalDataServiceISBN service = new ExternalDataServiceISBN() {
            @Override
            public BookNew lookup(String isbn) {
                return new BookNew("9781405288194", "The Little Prince", "Harper Collins");
            }
        };

    */

        BookNew book = database.lookup(isbn);
        if(book == null){
            book = webService.lookup(isbn);
        }

        StringBuilder locatorCode = new StringBuilder();
        locatorCode.append(book.getIsbn().substring(book.getIsbn().length() - 4));
        // 3. Re-implement the locatorCode logic to use the author's lastname's first char instead of using first name's first char.
        locatorCode.append(book.getAuthor().split(" ")[book.getAuthor().split(" ").length - 1].charAt(0)); // gets the first letter of the last word of the author's name
        locatorCode.append(book.getTitle().split(" ").length);

        return locatorCode.toString();
    }
}
