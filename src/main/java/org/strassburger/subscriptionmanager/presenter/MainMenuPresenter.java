package org.strassburger.subscriptionmanager.presenter;

import org.strassburger.subscriptionmanager.model.DatabaseManager;
import org.strassburger.subscriptionmanager.view.MainMenuView;
import org.strassburger.tui4j.formatting.Printer;

public class MainMenuPresenter {
    private final MainMenuView view;
    private final DatabaseManager dbManager;

    public MainMenuPresenter(MainMenuView view, DatabaseManager dbManager) {
        this.view = view;
        this.dbManager = dbManager;
    }

    public void start() {
        while (true) {
            String choice = view.getMainMenuSelection();
            switch (choice) {
                case "Select Profile":
                    // TODO: Handle profile selection
                    Printer.println("&eFeature not implemented yet.");
                    break;
                case "Create Profile":
                    // TODO: Handle profile creation
                    Printer.println("&eFeature not implemented yet.");
                    break;
                case "Delete Profile":
                    // TODO: Handle profile deletion
                    Printer.println("&eFeature not implemented yet.");
                    break;
                default:
                    view.showGoodbyeMessage();
                    System.exit(0);
                    return;
            }
        }
    }
}
