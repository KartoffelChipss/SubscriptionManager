package org.strassburger.subscriptionmanager.view.profiles;

import org.strassburger.subscriptionmanager.presenter.profiles.ManageProfilesPresenter;
import org.strassburger.tui4j.formatting.Printer;
import org.strassburger.tui4j.input.SelectInput;

public class ManageProfilesView {
    public void sendStartMessage() {
        Printer.println("");
        Printer.println("&a&nManage Profiles");
        Printer.println("&7Please choose what you want to do");
        Printer.println("");
    }

    public ManageProfilesPresenter.ManageProfilesAction readAction() {
        SelectInput<ManageProfilesPresenter.ManageProfilesAction> select =
                new SelectInput<ManageProfilesPresenter.ManageProfilesAction>()
                .setLabel("Action: ")
                .addOption("Create Profile", ManageProfilesPresenter.ManageProfilesAction.ADD_PROFILE)
                .addOption("Delete Profile", ManageProfilesPresenter.ManageProfilesAction.DELETE_PROFILE)
                //.addOption("Edit Profile", ManageProfilesPresenter.ManageProfilesAction.EDIT_PROFILE)
                .addOption("Back", ManageProfilesPresenter.ManageProfilesAction.BACK);

        return select.read();
    }
}
