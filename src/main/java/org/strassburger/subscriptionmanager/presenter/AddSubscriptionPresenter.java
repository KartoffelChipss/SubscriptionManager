package org.strassburger.subscriptionmanager.presenter;

import org.strassburger.subscriptionmanager.model.DatabaseManager;
import org.strassburger.subscriptionmanager.model.entity.BillingPeriod;
import org.strassburger.subscriptionmanager.view.AddSubscriptionView;
import org.strassburger.tui4j.formatting.TextFormatter;
import org.strassburger.tui4j.input.validationrules.ValidationRule;

import java.util.HashSet;
import java.util.Set;

public class AddSubscriptionPresenter {
    private final AddSubscriptionView view;
    private final DatabaseManager dbManager;

    public AddSubscriptionPresenter(AddSubscriptionView view, DatabaseManager dbManager) {
        this.view = view;
        this.dbManager = dbManager;
    }

    public void start() {
        view.sendStartMessage();

        String name = view.readName(getNameAlreadyExistsValidationRule(dbManager));
        BillingPeriod billingPeriod = view.readBillingPeriod();
        double price = view.readPrice();
        Long startDate = view.readStartDate();
        String category = view.chooseOrAddCategory(getCategoriesFromDB(dbManager));

        dbManager.getSubscriptionRepository().addSubscription(name, price, billingPeriod, startDate, category);

        view.sendSubscriptionAddSuccessMessage();
        view.enterToContinue();
    }

    /**
     * Returns a validation rule that checks if a subscription with the given name already exists.
     * @param dbManager Database manager used to check if the subscription already exists.
     * @return The validation rule.
     */
    private ValidationRule<String> getNameAlreadyExistsValidationRule(DatabaseManager dbManager) {
        return new ValidationRule<String>() {
            @Override
            public boolean validate(String s) {
                return dbManager.getSubscriptionRepository().getSubscriptionByName(s).isEmpty();
            }

            @Override
            public String getErrorMessage() {
                return TextFormatter.format("&cSubscription with this name already exists.");
            }
        };
    }

    /**
     * Retrieves all unique categories from the existing subscriptions in the database.
     * @param dbManager The database manager used to access subscription data.
     * @return A set containing all distinct category names found in the subscriptions.
     */

    private Set<String> getCategoriesFromDB(DatabaseManager dbManager) {
        Set<String> categories = new HashSet<>();

        dbManager.getSubscriptionRepository().getAllSubscriptions().forEach(subscription -> {
            categories.add(subscription.getCategory());
        });

        return categories;
    }

}
