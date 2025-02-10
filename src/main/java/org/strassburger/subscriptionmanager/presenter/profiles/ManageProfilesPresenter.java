package org.strassburger.subscriptionmanager.presenter.profiles;

import org.strassburger.subscriptionmanager.model.DatabaseManager;
import org.strassburger.subscriptionmanager.view.profiles.AddProfileView;
import org.strassburger.subscriptionmanager.view.profiles.ManageProfilesView;

public class ManageProfilesPresenter {
    private final ManageProfilesView view;
    private final DatabaseManager dbManager;

    public ManageProfilesPresenter(ManageProfilesView view, DatabaseManager dbManager) {
        this.view = view;
        this.dbManager = dbManager;
    }

    public void start() {
        while (true) {
            view.sendStartMessage();

            ManageProfilesAction action = view.readAction();

            switch (action) {
                case ADD_PROFILE:
                    new AddProfilePresenter(new AddProfileView(), dbManager).start();
                    break;
                default:
                    return;
            }
        }
    }

    /**
     * The possible actions that can be performed in the Manage Profiles view.
     */
    public enum ManageProfilesAction {
        ADD_PROFILE,
        DELETE_PROFILE,
        EDIT_PROFILE,
        BACK
    }
}
