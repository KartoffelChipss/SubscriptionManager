package org.strassburger.subscriptionmanager.view;

import org.strassburger.subscriptionmanager.model.entity.BillingPeriod;
import org.strassburger.subscriptionmanager.model.entity.Subscription;
import org.strassburger.tui4j.formatting.Printer;
import org.strassburger.tui4j.input.ContinueInput;

import java.util.*;

public class ViewAllSubscriptionsView {

    public void sendStartMessage() {
        Printer.println("");
        Printer.println("&a&nSubscriptions:");
        Printer.println("");
    }

    public void showAllSubscriptions(List<Subscription> subList) {
        List<String> headers = List.of("Name", "Price", "Normalized Price", "Start date", "Next billing date");
        List<Integer> columnWidths = calculateColumnWidths(headers, subList);

        printHeader(headers, columnWidths);

        printSeparator(columnWidths);

        String rowFormat = String.format(
                " %%-%ds &8|&r %%-%ds &8|&r %%-%ds &8|&r %%-%ds &8|&r %%-%ds",
                columnWidths.get(0), columnWidths.get(1), columnWidths.get(2), columnWidths.get(3), columnWidths.get(4)
        );

        for (Subscription subscription : subList) {
            String startDateString = convertLongToDate(subscription.getStartDate());
            String nextBillingDateString = convertLongToDate(getNextBillingDate(subscription));
            Printer.printfln(
                    rowFormat,
                    subscription.getName(),
                    String.format("%.2f", subscription.getPrice()) + subscription.getBillingPeriod().getPriceString(),
                    String.format("%.2f", subscription.getNormalizedPrice()) +BillingPeriod.MONTHLY.getPriceString(),
                    startDateString,
                    nextBillingDateString
            );
        }

        Printer.println("");
    }

    private List<Integer> calculateColumnWidths(List<String> headers, List<Subscription> subList) {
        List<Integer> columnWidths = new ArrayList<>(Collections.nCopies(headers.size(), 0));

        for (int i = 0; i < headers.size(); i++) {
            columnWidths.set(i, headers.get(i).length());
        }

        for (Subscription subscription : subList) {
            columnWidths.set(0, Math.max(columnWidths.get(0), subscription.getName().length()));
            columnWidths.set(1, Math.max(columnWidths.get(1), (
                    String.format("%.2f", subscription.getPrice()) + subscription.getBillingPeriod().getPriceString()
            ).length()));
            columnWidths.set(2, Math.max(columnWidths.get(2), (
                    String.format("%.2f", subscription.getNormalizedPrice()) + BillingPeriod.MONTHLY.getPriceString()
            ).length()));
            columnWidths.set(3, Math.max(columnWidths.get(3), convertLongToDate(subscription.getStartDate()).length()));
            columnWidths.set(4, 10);
        }

        return columnWidths;
    }

    private void printHeader(List<String> headers, List<Integer> columnWidths) {
        String headerFormat = String.format(
                " &l%%-%ds &8|&r &l%%-%ds &8|&r &l%%-%ds &8|&r &l%%-%ds &8|&r &l%%-%ds",
                columnWidths.get(0), columnWidths.get(1), columnWidths.get(2), columnWidths.get(3), columnWidths.get(4)
        );
        Printer.printfln(headerFormat, headers.get(0), headers.get(1), headers.get(2), headers.get(3), headers.get(4));
    }

    private void printSeparator(List<Integer> columnWidths) {
        // Total length: sum of all column widths + 3 spaces between each column + 2 spaces in front and at the end
        int totalLength = columnWidths.stream().reduce(0, Integer::sum) + (columnWidths.size() - 1) * 3 + 2;
        Printer.println("&8-".repeat(totalLength));
    }

    public void sendNoSubscriptionsAvailableMessage() {
        Printer.println(" &cNo Subscriptions available");
        Printer.println("");
    }

    public void enterToContinue() {
        new ContinueInput()
                .setLabel("Press ENTER to continue")
                .read();
    }

    /**
     * Convert time in milliseconds to String representation of date.
     * @param dateLong Time in milliseconds.
     * @return Date as String.
     */
    private String convertLongToDate(Long dateLong) {
        if (dateLong == null) return "/";

        Date date = new Date(dateLong);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_MONTH) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR);
    }

    /**
     * Calculate the next billing date for a subscription.
     * @param subscription Subscription to calculate next billing date for.
     * @return Next billing date in milliseconds.
     */
    private Long getNextBillingDate(Subscription subscription) {
        if (subscription.getStartDate() == null) return null;

        Date currentDate = new Date(System.currentTimeMillis());

        Calendar nextBillingDateCalendar = Calendar.getInstance();
        nextBillingDateCalendar.setTime(currentDate);
        nextBillingDateCalendar.setLenient(true);

        switch (subscription.getBillingPeriod()) {
            case BillingPeriod.WEEKLY:
                nextBillingDateCalendar.add(Calendar.WEEK_OF_YEAR, 1);
                nextBillingDateCalendar.set(Calendar.DAY_OF_WEEK, 0);
                break;
            case BillingPeriod.MONTHLY:
                nextBillingDateCalendar.add(Calendar.MONTH, 1);
                nextBillingDateCalendar.set(Calendar.DAY_OF_MONTH, 1);
                break;
            case BillingPeriod.QUARTERLY:
                int month = nextBillingDateCalendar.get(Calendar.MONTH);
                if (month < 3) {
                    nextBillingDateCalendar.set(Calendar.MONTH, 3);
                } else if (month < 6) {
                    nextBillingDateCalendar.set(Calendar.MONTH, 6);
                } else if (month < 9) {
                    nextBillingDateCalendar.set(Calendar.MONTH, 9);
                } else {
                    nextBillingDateCalendar.set(Calendar.MONTH, 0);
                }

                nextBillingDateCalendar.set(Calendar.DAY_OF_MONTH, 1);
                break;
                // BillingPeriod.YEARLY
            default:
                nextBillingDateCalendar.add(Calendar.YEAR, 1);
                nextBillingDateCalendar.set(Calendar.MONTH, 0);
                nextBillingDateCalendar.set(Calendar.DAY_OF_MONTH, 1);
                break;
        }

        return nextBillingDateCalendar.getTimeInMillis();
    }
}
