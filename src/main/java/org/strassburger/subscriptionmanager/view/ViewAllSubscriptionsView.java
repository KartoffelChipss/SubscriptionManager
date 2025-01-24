package org.strassburger.subscriptionmanager.view;

import org.strassburger.subscriptionmanager.model.entity.Subscription;
import org.strassburger.tui4j.formatting.Printer;
import org.strassburger.tui4j.input.ContinueInput;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewAllSubscriptionsView {

    public void sendStartMessage() {
        Printer.println("");
        Printer.println("&a&nSubscriptions:");
        Printer.println("");
    }

    public void showAllSubscriptions(List<Subscription> subList) {
        Printer.printfln(" &n%-20s   %-5s   %-14s   %-10s", "Name", "Price", "Billing Period", "Start date");

        for (Subscription subscription : subList) {
            String startDateString = convertLongToDate(subscription.getStartDate());
            Printer.printfln(" %-20s | %-5.2f | %-14s | %-10s", subscription.getName(), subscription.getPrice(), subscription.getBillingPeriod(), startDateString);
        }
        Printer.println("");
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
        if (dateLong != null) {
            Date date = new Date(dateLong);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            return calendar.get(Calendar.DAY_OF_MONTH) + ". " + (calendar.get(Calendar.MONTH) + 1) + ". " + calendar.get(Calendar.YEAR);
        } else {
            return "/";
        }
    }
}
