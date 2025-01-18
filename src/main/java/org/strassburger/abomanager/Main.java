package org.strassburger.abomanager;

import org.strassburger.abomanager.model.DatabaseManager;
import org.strassburger.abomanager.model.SQLiteDatabaseManager;
import org.strassburger.abomanager.presenter.MainMenuPresenter;
import org.strassburger.abomanager.view.MainMenuView;

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
        MainMenuView view = new MainMenuView();
        MainMenuPresenter presenter = new MainMenuPresenter(view, dbManager);

        presenter.start();
    }
}
