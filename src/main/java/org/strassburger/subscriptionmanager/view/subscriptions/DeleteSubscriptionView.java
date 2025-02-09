package org.strassburger.subscriptionmanager.view.subscriptions;

import org.strassburger.tui4j.formatting.Printer;
import org.strassburger.tui4j.input.ContinueInput;
import org.strassburger.tui4j.input.TextInput;
import org.strassburger.tui4j.input.validationrules.TextValidationRules;
import org.strassburger.tui4j.input.validationrules.ValidationRule;

public class DeleteSubscriptionView {
    public void sendStartMessage() {
        Printer.println("");
        Printer.println("&a&nDelete Subscription");
        Printer.println("&7Enter the name of the subscription you want to delete. (Or space to cancel)");
        Printer.println("");
    }

    /**
     * Reads the name of the subscription from the UI
     * @param nameDoesNotExistValidationRule Validation rule to check if the name exists.
     * @return The name of the subscription.
     */
    public String readName(ValidationRule<String> nameDoesNotExistValidationRule) {
        return new TextInput()
                .setLabel("Name: ")
                .setRetryOnInvalid(true)
                .setInline(true)
                .addValidationRules(
                        TextValidationRules.minLength(1),
                        TextValidationRules.maxLength(255),
                        nameDoesNotExistValidationRule
                )
                .read();
    }

    public void sendCancelMessage() {
        Printer.printfln("&eDeletion cancelled.\n");
    }

    public void sendSubscriptionDeleteSuccessMessage() {
        Printer.println("&aSubscription deleted successfully.\n");
    }

    public void sendSubscriptionDeleteFailMessage() {
        Printer.println("&cFailed to delete Subscription.");
    }

    public void enterToContinue() {
        new ContinueInput()
                .setLabel("Press ENTER to continue")
                .read();
    }
}


