package org.strassburger.subscriptionmanager.presenter;

import org.strassburger.subscriptionmanager.model.DatabaseManager;
import org.strassburger.subscriptionmanager.presenter.profiles.SelectProfilePresenter;
import org.strassburger.subscriptionmanager.view.WelcomeWindowView;
import org.strassburger.subscriptionmanager.view.profiles.SelectProfileView;

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

        new SelectProfilePresenter(new SelectProfileView(), dbManager).start();
    }
}
