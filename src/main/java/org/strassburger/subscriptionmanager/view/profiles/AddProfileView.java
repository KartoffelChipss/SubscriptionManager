package org.strassburger.subscriptionmanager.view.profiles;

import org.strassburger.tui4j.formatting.Printer;
import org.strassburger.tui4j.input.TextInput;
import org.strassburger.tui4j.input.validationrules.TextValidationRules;
import org.strassburger.tui4j.input.validationrules.ValidationRule;

public class AddProfileView {
    public void sendStartMessage() {
        Printer.println("");
        Printer.println("&a&nCreate Profile");
        Printer.println("&7Please enter the following information");
        Printer.println("");
    }

    /**
     * Reads the name of the profile from the user.
     * @param nameAlreadyExistsValidationRule the validation rule that checks if the name already exists
     * @return the name of the profile
     */
    public String readName(ValidationRule<String> nameAlreadyExistsValidationRule) {
        return new TextInput()
                .setLabel("Name: ")
                .setInline(true)
                .addValidationRules(
                        TextValidationRules.maxLength(255),
                        TextValidationRules.minLength(1),
                        nameAlreadyExistsValidationRule,
                        noSpaceAllowed()
                )
                .read()
                .trim();
    }

    public void showErrorMessage() {
        Printer.println("&cFailed to create profile. Please try again.");
    }

    public void showSuccessMessage() {
        Printer.println("&aProfile created successfully.");
    }

    /**
     * Validation rule that checks if the name is not empty.
     * @return the validation rule
     */
    private ValidationRule<String> noSpaceAllowed() {
        return new ValidationRule<String>() {
            @Override
            public boolean validate(String s) {
                return !s.trim().isEmpty();
            }

            @Override
            public String getErrorMessage() {
                return "&cName cannot be empty.";
            }
        };
    }
}
