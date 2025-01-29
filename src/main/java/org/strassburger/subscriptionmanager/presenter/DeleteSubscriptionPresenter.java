package org.strassburger.subscriptionmanager.presenter;

import org.strassburger.subscriptionmanager.model.DatabaseManager;
import org.strassburger.subscriptionmanager.model.entity.Subscription;
import org.strassburger.subscriptionmanager.view.DeleteSubscriptionView;
import org.strassburger.tui4j.formatting.TextFormatter;
import org.strassburger.tui4j.input.validationrules.ValidationRule;

import javax.xml.crypto.Data;
import java.util.Optional;

public class DeleteSubscriptionPresenter {
    private final DeleteSubscriptionView view;
    private final DatabaseManager dbManager;

    public DeleteSubscriptionPresenter(DeleteSubscriptionView view, DatabaseManager dbManager) {
        this.view = view;
        this.dbManager = dbManager;
    }


    public void start() {
        view.sendStartMessage();

        String name = view.readName(getNameDoesNotExistValidationRule(dbManager));
        Subscription subscription = dbManager.getSubscriptionRepository().getSubscriptionByName(name).get();
        dbManager.getSubscriptionRepository().deleteSubscription(subscription.getId());

        view.sendSubscriptionDeleteSuccessMessage();
        view.enterToContinue();
    }

    /**
     * Returns a validation rule that checks if the subscription with the given name does not exist.
     * @param dbManager Database manager used to check if the subscription does not exist.
     * @return The validation rule.
     */
    private ValidationRule<String> getNameDoesNotExistValidationRule(DatabaseManager dbManager) {
        return new ValidationRule<String>() {
            @Override
            public boolean validate(String s) {
                return dbManager.getSubscriptionRepository().getSubscriptionByName(s).isPresent();
            }

            @Override
            public String getErrorMessage() {
                return TextFormatter.format("&cSubscription with this name does not exist.");
            }
        };
    }
}
