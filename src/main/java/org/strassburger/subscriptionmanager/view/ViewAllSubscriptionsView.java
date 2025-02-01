package org.strassburger.subscriptionmanager.view;

import org.strassburger.subscriptionmanager.model.entity.BillingPeriod;
import org.strassburger.subscriptionmanager.model.entity.Subscription;
import org.strassburger.subscriptionmanager.model.entity.SubscriptionOrder;
import org.strassburger.subscriptionmanager.util.DateCalculator;
import org.strassburger.tui4j.formatting.Printer;
import org.strassburger.tui4j.input.ContinueInput;
import org.strassburger.tui4j.input.SelectInput;

import java.util.*;

public class ViewAllSubscriptionsView {

    public void sendStartMessage() {
        Printer.println("");
        Printer.println("&a&nSubscriptions:");
        Printer.println("&7Please first choose, how you want to order the subscriptions.");
        Printer.println("");
    }

    /**
     * Reads the order in which the subscriptions should be displayed from the UI.
     * @return The order in which the subscriptions should be displayed.
     */
    public SubscriptionOrder getSubscriptionOrder() {
        return new SelectInput<SubscriptionOrder>()
                .addOptions(
                        Arrays.stream(SubscriptionOrder.values())
                                .map(so -> new SelectInput.Option<>(so.getDisplayName(), so))
                                .toList()
                )
                .setLabel("Order subscriptions by")
                .setRetryOnInvalid(true)
                .read();
    }

    /**
     * Shows all subscriptions in the UI.
     * @param subList List of subscriptions to show.
     */
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
            String startDateString = DateCalculator.convertLongToDate(subscription.getStartDate());

            String nextBillingDateString = subscription.getStartDate() != null
                    ? DateCalculator.convertLongToDate(
                        DateCalculator.getNextBillingDate(subscription.getBillingPeriod(), subscription.getStartDate())
                    )
                    : "/";

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
            columnWidths.set(3, Math.max(columnWidths.get(3), DateCalculator.convertLongToDate(subscription.getStartDate()).length()));
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
        Printer.println("&cNo Subscriptions available\n");
        Printer.println("");
    }

    public void enterToContinue() {
        new ContinueInput()
                .setLabel("Press ENTER to continue")
                .read();
    }
}
