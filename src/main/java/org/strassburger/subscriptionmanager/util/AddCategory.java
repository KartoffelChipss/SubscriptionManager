package org.strassburger.subscriptionmanager.util;

import org.strassburger.subscriptionmanager.model.DatabaseManager;
import org.strassburger.tui4j.input.SelectInput;
import org.strassburger.tui4j.input.TextInput;
import org.strassburger.tui4j.input.validationrules.TextValidationRules;
import org.strassburger.tui4j.input.validationrules.ValidationRule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddCategory {
    /**
     * Asks User to choose between categories or to add a new category
     * @return chosen category or runs methode askForNewCategory to add new category
     */
    public static String chooseOrAddCategory(DatabaseManager dbManager) {

        Set<String> existingCategories = getCategoriesFromDB(dbManager);

        SelectInput<String> selectInput = new SelectInput<String>()
                .addOptions(existingCategories.stream().map(cat -> new SelectInput.Option<>(cat, cat)).toList())
                .addOption("Add new category", "NEW")
                .setLabel("Choose a category or add a new one:")
                .setRetryOnInvalid(true);

        String selection = selectInput.read();

        if ("NEW".equals(selection)) {
            return askForNewCategory();
        }

        return selection;
    }

    /**
     * Prompts User to enter new Category
     * @return A new Category with first letter capitalized
     */

    private static String askForNewCategory() {
        String newCategory = new TextInput()
                .setLabel("Enter new category:")
                .setRetryOnInvalid(true)
                .addValidationRules(
                        TextValidationRules.minLength(1),
                        TextValidationRules.maxLength(255),
                        new ValidationRule<String>() {
                            @Override
                            public boolean validate(String s) {
                                return !s.isBlank();
                            }

                            @Override
                            public String getErrorMessage() {
                                return "&cCategory must not be empty";
                            }
                        }
                )
                .read()
                .trim()
                .toLowerCase();



        return newCategory.substring(0, 1).toUpperCase() + newCategory.substring(1);
    }

    /**
     * Retrieves all unique categories from the existing subscriptions in the database.
     * @param dbManager The database manager used to access subscription data.
     * @return A set containing all distinct category names found in the subscriptions.
     */

    private static Set<String> getCategoriesFromDB(DatabaseManager dbManager) {
        Set<String> categories = new HashSet<>();

        dbManager.getSubscriptionRepository().getAllSubscriptions().forEach(subscription -> {
            categories.add(subscription.getCategory());
        });

        return categories;
    }
}
