package org.strassburger.subscriptionmanager.view.profiles;

import org.strassburger.subscriptionmanager.model.entity.Profile;
import org.strassburger.tui4j.formatting.Printer;
import org.strassburger.tui4j.input.SelectInput;
import org.strassburger.tui4j.input.TextInput;
import org.strassburger.tui4j.input.validationrules.TextValidationRules;
import org.strassburger.tui4j.input.validationrules.ValidationRule;

import java.util.List;
import java.util.stream.Collectors;

public class EditProfileView {
    public void sendStartMessage() {
        Printer.println("");
        Printer.println("&a&nEdit Profile");
        Printer.println("&7Edit a profile");
        Printer.println("");
    }

    /**
     * Asks the user to select a profile from a list of profiles.
     * @param profiles The list of profiles to choose from.
     * @return The id of the selected profile.
     */
    public int selectProfile(List<Profile> profiles) {
        return new SelectInput<Integer>()
                .setLabel("Select a profile: ")
                .addOptions(
                        profiles.stream()
                                .map(p -> new SelectInput.Option<>(p.getName(), p.getId()))
                                .collect(Collectors.toList())
                )
                .read();
    }

    public void sendNoProfilesMessage() {
        Printer.println("&cThere are no profiles to edit.");
    }

    public void sendInvalidProfileMessage() {
        Printer.println("&cInvalid profile selected.");
    }

    /**
     * Asks the user to enter a new name for the profile.
     * @param nameAlreadyExistsValidationRule A validation rule that checks if a profile with the given name already exists.
     * @return The new name for the profile.
     */
    public String readNewName(ValidationRule<String> nameAlreadyExistsValidationRule) {
        return new TextInput()
                .setLabel("New name: ")
                .setInline(true)
                .addValidationRules(
                        TextValidationRules.maxLength(255),
                        TextValidationRules.minLength(1),
                        nameAlreadyExistsValidationRule,
                        new ValidationRule<String>() {
                            @Override
                            public boolean validate(String s) {
                                return !s.trim().isEmpty();
                            }

                            @Override
                            public String getErrorMessage() {
                                return "&cName cannot be empty.";
                            }
                        }
                )
                .read()
                .trim();
    }

    public void sendSuccessMessage() {
        Printer.println("&aProfile updated successfully.");
    }
}
