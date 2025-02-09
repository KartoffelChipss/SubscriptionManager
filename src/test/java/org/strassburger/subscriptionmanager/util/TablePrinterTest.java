package org.strassburger.subscriptionmanager.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TablePrinterTest {
    private TablePrinter tablePrinter;

    @BeforeEach
    void setUp() {
        List<String> headers = List.of("Name", "Price", "Category");
        List<List<String>> rows = List.of(
                List.of("Netflix", "$15.99", "Entertainment"),
                List.of("Spotify", "$9.99", "Music"),
                List.of("Adobe", "$20.99", "Software")
        );

        tablePrinter = new TablePrinter(headers, rows);
    }

    @Test
    void testHeadersAreSetCorrectly() {
        List<String> expectedHeaders = List.of("Name", "Price", "Category");
        assertEquals(expectedHeaders, tablePrinter.getHeaders());
    }

    @Test
    void testRowsAreSetCorrectly() {
        List<List<String>> expectedRows = List.of(
                List.of("Netflix", "$15.99", "Entertainment"),
                List.of("Spotify", "$9.99", "Music"),
                List.of("Adobe", "$20.99", "Software")
        );
        assertEquals(expectedRows, tablePrinter.getRows());
    }

    @Test
    void testAddRow() {
        List<String> newRow = List.of("YouTube", "$11.99", "Video");
        tablePrinter.addRow(newRow);

        assertEquals(4, tablePrinter.getRows().size());
        assertEquals(newRow, tablePrinter.getRows().get(3));
    }

    @Test
    void testRemoveRow() {
        tablePrinter.removeRow(1);
        assertEquals(2, tablePrinter.getRows().size());
        assertFalse(tablePrinter.getRows().stream().anyMatch(row -> row.contains("Spotify")));
    }

    @Test
    void testClearRows() {
        tablePrinter.clearRows();
        assertTrue(tablePrinter.getRows().isEmpty());
    }

    @Test
    void testSetHeaders() {
        List<String> newHeaders = List.of("Service", "Cost", "Type");
        tablePrinter.setHeaders(newHeaders);
        assertEquals(newHeaders, tablePrinter.getHeaders());
    }

    @Test
    void testSetRows() {
        List<List<String>> newRows = List.of(
                List.of("Disney+", "$7.99", "Entertainment"),
                List.of("Hulu", "$6.99", "Streaming")
        );
        tablePrinter.setRows(newRows);
        assertEquals(2, tablePrinter.getRows().size());
        assertEquals(newRows, tablePrinter.getRows());
    }

    @Test
    void testCalculateColumnWidths() {
        TablePrinter smallTable = new TablePrinter(
                List.of("A", "B"),
                List.of(List.of("123", "456"), List.of("7890", "12"))
        );

        List<Integer> expectedWidths = List.of(4, 3); // "7890" and "456" are the longest in each column
        assertEquals(expectedWidths, smallTable.getColumnWidths());
    }

    @Test
    void testEmptyTable() {
        TablePrinter emptyTable = new TablePrinter();
        assertTrue(emptyTable.getHeaders().isEmpty());
        assertTrue(emptyTable.getRows().isEmpty());
    }
}