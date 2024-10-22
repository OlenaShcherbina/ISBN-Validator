package com.example;

public class StockManager {

    private ExternalISBNDataService webService;
    private ExternalISBNDataService databaseService;

    public void setWebService(ExternalISBNDataService webService){
        this.webService = webService;
    }

    public void setDatabaseService(ExternalISBNDataService databaseService){
        this.databaseService = databaseService;
    }

    public String getLocatorCode(String isbn) {

        Book book = databaseService.lookup(isbn);
        if(book == null){
            book = webService.lookup(isbn);
        }
        StringBuilder locator = new StringBuilder();
        //last 4 digits of ISBN + initial Letter of author + number of words in the title

        locator.append(isbn.substring(isbn.length() - 4));
        locator.append(book.getAuthor().substring(0, 1)); // = locator.append(book.getAuthor().charAt(0));
        locator.append(book.getTitle().split(" ").length);

        return locator.toString();
    }
}
