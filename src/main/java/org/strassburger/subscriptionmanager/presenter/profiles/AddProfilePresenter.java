package org.strassburger.subscriptionmanager.presenter.profiles;

import org.strassburger.subscriptionmanager.model.DatabaseManager;
import org.strassburger.subscriptionmanager.view.profiles.AddProfileView;
import org.strassburger.tui4j.formatting.TextFormatter;
import org.strassburger.tui4j.input.validationrules.ValidationRule;

public class AddProfilePresenter {
    private final AddProfileView view;
    private final DatabaseManager dbManager;

    public AddProfilePresenter(AddProfileView view, DatabaseManager dbManager) {
        this.view = view;
        this.dbManager = dbManager;
    }

    public void start() {
        view.sendStartMessage();

        boolean success = false;

        while (!success) {
            String name = view.readName(getNameAlreadyExistsValidationRule(dbManager));

            success = dbManager.getProfileRepository().createProfile(name);

            if (!success) view.showErrorMessage();
        }

        view.showSuccessMessage();
    }

    /**
     * Returns a validation rule that checks if a profile with the given name already exists.
     * @param dbManager DatabaseManager
     * @return ValidationRule
     */
    private ValidationRule<String> getNameAlreadyExistsValidationRule(DatabaseManager dbManager) {
        return new ValidationRule<String>() {
            @Override
            public boolean validate(String s) {
                return dbManager.getProfileRepository().getProfile(s).isEmpty();
            }

            @Override
            public String getErrorMessage() {
                return TextFormatter.format("&cSubscription with this name already exists.");
            }
        };
    }
}
