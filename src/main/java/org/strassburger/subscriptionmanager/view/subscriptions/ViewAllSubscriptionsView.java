package org.strassburger.subscriptionmanager.view.subscriptions;

import org.strassburger.subscriptionmanager.model.entity.BillingPeriod;
import org.strassburger.subscriptionmanager.model.entity.Subscription;
import org.strassburger.subscriptionmanager.model.entity.SubscriptionOrder;
import org.strassburger.subscriptionmanager.util.DateCalculator;
import org.strassburger.subscriptionmanager.util.TablePrinter;
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
        List<String> headers = List.of("Name", "Price", "Normalized Price", "Start date", "Next billing date", "Category");

        List<List<String>> rows = subList.stream()
                .map(subscription -> List.of(
                        subscription.getName(),
                        String.format("%.2f", subscription.getPrice()) + subscription.getBillingPeriod().getPriceString(),
                        String.format("%.2f", subscription.getNormalizedPrice()) + BillingPeriod.MONTHLY.getPriceString(),
                        DateCalculator.convertLongToDate(subscription.getStartDate()),
                        getNextBillingDateString(subscription),
                        subscription.getCategory()
                ))
                .toList();

        new TablePrinter(headers, rows).printTable();

        Printer.println("");
    }

    private String getNextBillingDateString(Subscription subscription) {
        return subscription.getStartDate() != null
                ? DateCalculator.convertLongToDate(
                    DateCalculator.getNextBillingDate(subscription.getBillingPeriod(), subscription.getStartDate())
                )
                : "/";
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
