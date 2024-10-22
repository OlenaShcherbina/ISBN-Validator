package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class StockManagementTests {

    @Test
    public void testSCanGetCorrectLocatorCode(){

        ExternalISBNDataService testWebService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book("0582827647", "Of Mice and Men", "John Steinbeck");
            }
        };
        ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return null;
            }
        };
        StockManager stockManager = new StockManager();
        stockManager.setDatabaseService(testDatabaseService);
        stockManager.setWebService(testWebService);


        String isbn = "0582827647";
        String locatorCode = stockManager.getLocatorCode(isbn);

        assertEquals("7647J4", locatorCode);

    }

    @Test
    public void databaseIsUsedWhenDataIsPresent(){
     /*
        ExternalISBNDataService testWebService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book("0582827647", "Of Mice and Men", "John Steinbeck");
            }
        };
        ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return null;
            }
        };

      */
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class); //creates the demo class for us
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class); //creates the demo class for us

        when(databaseService.lookup("0582827647")).thenReturn(new Book("1234567890", "The Title", "Sample Author"));

        StockManager stockManager = new StockManager();
        stockManager.setDatabaseService(databaseService);
        stockManager.setWebService(webService);


        String isbn = "0582827647";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(databaseService).lookup("0582827647");
        verify(webService, times(0)).lookup(anyString()); // = verify(webService, never()).lookup("0582827647");

        //fail("Not yet implemented");
    }

    @Test
    public void webServiceIsUsedWhenDataIsNotPresentInDatabase(){
        // 2. Write the missing test webServiceIsUsedWhenDataIsNotPresentInDatabase
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        StockManager stockManager = new StockManager();
        stockManager.setDatabaseService(databaseService);
        stockManager.setWebService(webService);

        String isbn = "0582827647";
        when(databaseService.lookup(isbn)).thenReturn(null);
        when(webService.lookup(isbn)).thenReturn(new Book("0582827647", "The Title", "The Author"));

        String locatorCode = stockManager.getLocatorCode(isbn);

    //    verify(databaseService, times(1)).lookup(isbn); //do we need to call this one?
        verify(webService, atLeast(1)).lookup(isbn);


    }
}
