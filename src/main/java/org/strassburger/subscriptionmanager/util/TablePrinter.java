package org.strassburger.subscriptionmanager.util;

import org.strassburger.tui4j.formatting.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TablePrinter {
    private final List<String> headers;
    private final List<List<String>> rows;
    private List<Integer> columnWidths;

    /**
     * Creates a new TablePrinter with no headers and no rows.
     */
    public TablePrinter() {
        this.headers = new ArrayList<>();
        this.rows = new ArrayList<>();
        this.columnWidths = new ArrayList<>();
    }

    /**
     * Creates a new TablePrinter with the given headers and rows.
     * @param headers The headers of the table.
     * @param rows The rows of the table.
     */
    public TablePrinter(List<String> headers, List<List<String>> rows) {
        this.headers = new ArrayList<>(headers);
        this.rows = new ArrayList<>();
        for (List<String> row : rows) {
            this.rows.add(new ArrayList<>(row));
        }
        calculateColumnWidths();
    }

    /**
     * Sets the headers of the table.
     */
    public void setHeaders(List<String> headers) {
        this.headers.clear();
        this.headers.addAll(headers);
        calculateColumnWidths();
    }

    /**
     * Sets the rows of the table.
     */
    public void setRows(List<List<String>> rows) {
        this.rows.clear();
        for (List<String> row : rows) {
            this.rows.add(new ArrayList<>(row));
        }
        calculateColumnWidths();
    }

    /**
     * Adds a row to the table.
     */
    public void addRow(List<String> row) {
        rows.add(new ArrayList<>(row));
        calculateColumnWidths();
    }

    /**
     * Adds a row to the table at the given index.
     */
    public void addRow(int index, List<String> row) {
        rows.add(index, new ArrayList<>(row));
        calculateColumnWidths();
    }

    /**
     * Removes a row from the table at the given index.
     */
    public void removeRow(int index) {
        rows.remove(index);
        calculateColumnWidths();
    }

    /**
     * Clears all rows from the table.
     */
    public void clearRows() {
        rows.clear();
        calculateColumnWidths();
    }

    public List<String> getHeaders() {
        return headers;
    }

    public List<List<String>> getRows() {
        return rows;
    }

    public List<Integer> getColumnWidths() {
        return columnWidths;
    }

    public void printTable() {
        printHeader();
        printSeparator();
        printRows();
    }

    private void printHeader() {
        String format = buildFormatString(true);
        Printer.printfln(format, headers.toArray());
    }

    private void printRows() {
        String format = buildFormatString(false);
        for (List<String> row : rows) {
            Printer.printfln(format, row.toArray());
        }
    }

    private void printSeparator() {
        int totalLength = columnWidths.stream().reduce(0, Integer::sum) + (columnWidths.size() - 1) * 3 + 2;
        Printer.println("&8-".repeat(totalLength));
    }

    private void calculateColumnWidths() {
        columnWidths = headers.stream()
                .map(String::length)
                .collect(Collectors.toList());

        for (List<String> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                columnWidths.set(i, Math.max(columnWidths.get(i), row.get(i).length()));
            }
        }
    }

    private String buildFormatString(boolean isHeader) {
        StringBuilder format = new StringBuilder();
        for (int width : columnWidths) {
            format.append(isHeader ? " &l%-" : " %-").append(width).append("s &8|&r");
        }
        return format.substring(0, format.length() - 5); // Remove last "|&r"
    }
}