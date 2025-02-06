package org.strassburger.subscriptionmanager.presenter;

import org.strassburger.subscriptionmanager.model.DatabaseManager;
import org.strassburger.subscriptionmanager.model.entity.Profile;
import org.strassburger.subscriptionmanager.view.AddSubscriptionView;
import org.strassburger.subscriptionmanager.view.DeleteSubscriptionView;
import org.strassburger.subscriptionmanager.view.MainMenuView;
import org.strassburger.subscriptionmanager.view.ViewAllSubscriptionsView;

public class MainMenuPresenter {
    private final MainMenuView view;
    private final DatabaseManager dbManager;
    private final Profile profile;

    public MainMenuPresenter(MainMenuView view, DatabaseManager dbManager, Profile profile) {
        this.view = view;
        this.dbManager = dbManager;
        this.profile = profile;
    }

    public void start() {
        while (true) {
            view.showMainMenuMessage();
            MainMenuOption choice = view.getMainMenuSelection();
            switch (choice) {
                case MainMenuOption.ADD_SUBSCRIPTION:
                    new AddSubscriptionPresenter(new AddSubscriptionView(), dbManager, profile).start();
                    break;
                case MainMenuOption.VIEW_ALL_SUBSCRIPTIONS:
                    new ViewAllSubscriptionsPresenter(new ViewAllSubscriptionsView(), dbManager, profile).start();
                    break;
                case MainMenuOption.DELETE_SUBSCRIPTION:
                    new DeleteSubscriptionPresenter(new DeleteSubscriptionView(), dbManager, profile).start();
                    break;
                default:
                    return;
            }
        }
    }

    public enum MainMenuOption {
        ADD_SUBSCRIPTION("Add Subscription"),
        VIEW_ALL_SUBSCRIPTIONS("View Subscriptions"),
        DELETE_SUBSCRIPTION("Delete Subscription"),
        EXIT("Back to Profile Selection");

        private final String label;

        MainMenuOption(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
}