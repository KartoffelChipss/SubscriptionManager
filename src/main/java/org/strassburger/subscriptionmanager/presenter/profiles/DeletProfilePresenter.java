package org.strassburger.subscriptionmanager.presenter.profiles;

import org.strassburger.subscriptionmanager.model.DatabaseManager;
import org.strassburger.subscriptionmanager.view.profiles.DeleteProfileView;
import org.strassburger.tui4j.input.validationrules.ValidationRule;

public class DeletProfilePresenter {
    private final DeleteProfileView view;
    private final DatabaseManager dbManager;

    public DeletProfilePresenter(DeleteProfileView view, DatabaseManager dbManager) {
        this.view = view;
        this.dbManager = dbManager;
    }

    public void start() {
        view.sendStartMessage();

        boolean success = false;

        while (!success) {
            String name = view.readName(getNameDoesNotExistValidationRule(dbManager));

            if (name.isBlank()) {
                view.sendCancelMessage();
                return;
            }

            success = dbManager.getProfileRepository().deleteProfile(name);

            if (!success) view.showErrorMessage();
        }

        view.showSuccessMessage();
    }

    public ValidationRule<String> getNameDoesNotExistValidationRule(DatabaseManager dbManager) {
        return new ValidationRule<String>() {
            @Override
            public boolean validate(String s) {
                return s.isBlank() || dbManager.getProfileRepository().getProfile(s).isPresent();
            }

            @Override
            public String getErrorMessage() {
                return "&cProfile with this name does not exist.";
            }
        };
    }
}
