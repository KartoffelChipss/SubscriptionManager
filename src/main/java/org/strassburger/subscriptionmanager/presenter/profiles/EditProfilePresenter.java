package org.strassburger.subscriptionmanager.presenter.profiles;

import org.strassburger.subscriptionmanager.model.DatabaseManager;
import org.strassburger.subscriptionmanager.model.entity.Profile;
import org.strassburger.subscriptionmanager.view.profiles.EditProfileView;
import org.strassburger.tui4j.formatting.TextFormatter;
import org.strassburger.tui4j.input.validationrules.ValidationRule;

import java.util.List;
import java.util.Optional;

public class EditProfilePresenter {
    private final EditProfileView view;
    private final DatabaseManager dbManager;

    public EditProfilePresenter(EditProfileView view, DatabaseManager dbManager) {
        this.view = view;
        this.dbManager = dbManager;
    }

    public void start() {
        view.sendStartMessage();

        boolean success = false;

        while (!success) {
            List<Profile> profiles = dbManager.getProfileRepository().getProfiles();

            if (profiles.isEmpty()) {
                view.sendNoProfilesMessage();
                return;
            }

            int profileId = view.selectProfile(profiles);

            String newName = view.readNewName(getNameAlreadyExistsValidationRule(dbManager));

            success = dbManager.getProfileRepository().updateProfileName(profileId, newName);
        }

        view.sendSuccessMessage();
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
