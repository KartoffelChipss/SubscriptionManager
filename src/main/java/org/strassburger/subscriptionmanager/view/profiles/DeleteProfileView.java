package org.strassburger.subscriptionmanager.view.profiles;

import org.strassburger.tui4j.formatting.Printer;
import org.strassburger.tui4j.input.TextInput;
import org.strassburger.tui4j.input.validationrules.ValidationRule;

public class DeleteProfileView {
    public void sendStartMessage() {
        Printer.println("");
        Printer.println("&a&nDelete Profile");
        Printer.println("&7Please enter the name of the profile you want to delete (Or space to cancel).");
        Printer.println("");
    }

    /**
     * Reads the name of the profile from the user.
     * @param nameDoesNotExistValidationRule the validation rule that checks if the name does not exist
     * @return the name of the profile
     */
    public String readName(ValidationRule<String> nameDoesNotExistValidationRule) {
        return new TextInput()
                .setLabel("Name: ")
                .setRetryOnInvalid(true)
                .setInline(true)
                .addValidationRules(nameDoesNotExistValidationRule)
                .read();
    }

    public void sendCancelMessage() {
        Printer.printfln("&eDeletion cancelled.\n");
    }

    public void showErrorMessage() {
        Printer.println("&cFailed to create profile. Please try again.");
    }

    public void showSuccessMessage() {
        Printer.println("&aProfile deleted successfully.");
    }
}
