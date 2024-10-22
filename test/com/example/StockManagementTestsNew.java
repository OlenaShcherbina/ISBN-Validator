

package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StockManagementTestsNew {
// 1. Re-write everything from scratch.
    @Test
    public void testStockManagement(){

        ExternalDataServiceISBN database = new ExternalDataServiceISBN() {
            @Override
            public BookNew lookup(String isbn) {
                return null;
            }
        };

        ExternalDataServiceISBN webService = new ExternalDataServiceISBN() {
            @Override
            public BookNew lookup(String isbn) {
                return new BookNew("9781405288194", "The Little Prince", "Harper Collins");
            }
        };

        StockManagerNew stockManager = new StockManagerNew();
        stockManager.setDatabaseISBN(database);
        stockManager.setWebServiceISBN(webService);
        String isbn = "9781405288194";
        String result = stockManager.getLocatorCode(isbn);

        assertEquals(result, "8194C3", "Book 'The Little Prince', author: Harper Collins, ISBN 9781405288194");
    }

    @Test
    public void testStockManagerWhenValidDataInDatabase(){
        ExternalDataServiceISBN database = mock(ExternalDataServiceISBN.class);
        ExternalDataServiceISBN webService = mock(ExternalDataServiceISBN.class);

        when(database.lookup("9781405288194")).thenReturn(new BookNew("9781405288194", "The Little Prince", "Harper Collins"));

        StockManagerNew stockManager = new StockManagerNew();
        stockManager.setDatabaseISBN(database);
        stockManager.setWebServiceISBN(webService);
        String locatorCode = stockManager.getLocatorCode("9781405288194");

        verify(database, times(1)).lookup("9781405288194");
        verify(webService, never()).lookup(anyString());
    }

    @Test
    public void testStockManagerWhenThereIsNoDataInDatabase(){
        // 2. Write the missing test webServiceIsUsedWhenDataIsNotPresentInDatabase

        StockManagerNew stockManager = new StockManagerNew();
        ExternalDataServiceISBN database = mock(ExternalDataServiceISBN.class);
        ExternalDataServiceISBN webService = mock(ExternalDataServiceISBN.class);
        stockManager.setDatabaseISBN(database);
        stockManager.setWebServiceISBN(webService);

        String isbn = "9781405288194";

        when(database.lookup(isbn)).thenReturn(null);
        when(webService.lookup(isbn)).thenReturn(new BookNew("9781405288194", "The Little Prince", "Harper Collins"));

        String locatorCode = stockManager.getLocatorCode(isbn);

        //verify that database and webservice were called
        verify(database).lookup(isbn);
        verify(webService).lookup(isbn);
    }
}
