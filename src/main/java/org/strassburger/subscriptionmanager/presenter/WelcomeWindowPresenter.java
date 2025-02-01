package org.strassburger.subscriptionmanager.presenter;

import org.strassburger.subscriptionmanager.model.DatabaseManager;
import org.strassburger.subscriptionmanager.view.MainMenuView;
import org.strassburger.subscriptionmanager.view.WelcomeWindowView;

public class WelcomeWindowPresenter {
    private final WelcomeWindowView view;
    private final DatabaseManager dbManager;

    public WelcomeWindowPresenter(WelcomeWindowView view, DatabaseManager dbManager) {
        this.view = view;
        this.dbManager = dbManager;
    }

    public void start() {
        view.showWelcomeMessage();
        view.enterToContinue();

        new MainMenuPresenter(new MainMenuView(), dbManager).start();
    }
}
