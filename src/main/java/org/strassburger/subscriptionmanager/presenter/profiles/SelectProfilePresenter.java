package org.strassburger.subscriptionmanager.presenter.profiles;

import org.strassburger.subscriptionmanager.model.DatabaseManager;
import org.strassburger.subscriptionmanager.model.entity.Profile;
import org.strassburger.subscriptionmanager.presenter.MainMenuPresenter;
import org.strassburger.subscriptionmanager.view.MainMenuView;
import org.strassburger.subscriptionmanager.view.profiles.SelectProfileView;

public class SelectProfilePresenter {
    private final SelectProfileView view;
    private final DatabaseManager dbManager;

    public SelectProfilePresenter(SelectProfileView view, DatabaseManager dbManager) {
        this.view = view;
        this.dbManager = dbManager;
    }

    public void start() {
        while (true) {
            view.sendStartMessage();
            int profileId = view.readProfileId(dbManager.getProfileRepository().getProfiles());

            // -1 is exit
            if (profileId == -1) {
                view.showGoodbyeMessage();
                System.exit(0);
            }

            // TODO: Use other negative codes for adding and deleting profiles

            Profile profile = dbManager.getProfileRepository().getProfile(profileId);

            new MainMenuPresenter(new MainMenuView(), dbManager, profile).start();
        }
    }
}
