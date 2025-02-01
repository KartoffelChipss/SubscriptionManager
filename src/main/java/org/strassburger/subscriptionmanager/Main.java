package org.strassburger.subscriptionmanager;

import org.strassburger.subscriptionmanager.model.DatabaseManager;
import org.strassburger.subscriptionmanager.model.SQLiteDatabaseManager;
import org.strassburger.subscriptionmanager.presenter.WelcomeWindowPresenter;
import org.strassburger.subscriptionmanager.view.WelcomeWindowView;

import java.util.logging.LogManager;

/**
 * Entry point of the application.
 */
public class Main {
    static {
        // Disable JOOQ logging
        LogManager.getLogManager().reset();
    }

    public static void main(String[] args) {
        DatabaseManager dbManager = new SQLiteDatabaseManager();
        WelcomeWindowPresenter presenter = new WelcomeWindowPresenter(new WelcomeWindowView(), dbManager);

        presenter.start();
    }
}
