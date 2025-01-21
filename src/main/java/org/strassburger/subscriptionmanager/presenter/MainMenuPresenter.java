package org.strassburger.subscriptionmanager.presenter;

import org.strassburger.subscriptionmanager.model.DatabaseManager;
import org.strassburger.subscriptionmanager.view.AddSubscriptionView;
import org.strassburger.subscriptionmanager.view.MainMenuView;

public class MainMenuPresenter {
    private final MainMenuView view;
    private final DatabaseManager dbManager;

    public MainMenuPresenter(MainMenuView view, DatabaseManager dbManager) {
        this.view = view;
        this.dbManager = dbManager;
    }

    public void start() {
        while (true) {
            MainMenuOption choice = view.getMainMenuSelection();
            switch (choice) {
                case MainMenuOption.ADD_SUBSCRIPTION:
                    new AddSubscriptionPresenter(new AddSubscriptionView(), dbManager).start();
                    break;
                default:
                    view.showGoodbyeMessage();
                    System.exit(0);
                    return;
            }
        }
    }

    public enum MainMenuOption {
        ADD_SUBSCRIPTION("Add Subscription"),
        EXIT("Exit");

        private final String label;

        MainMenuOption(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
}
