package org.strassburger.subscriptionmanager.view;

import org.strassburger.subscriptionmanager.presenter.MainMenuPresenter;
import org.strassburger.tui4j.formatting.Printer;
import org.strassburger.tui4j.input.SelectInput;

import java.util.Arrays;

public class MainMenuView {

    public void showMainMenuMessage() {
        Printer.println("");
        Printer.println("&a&nMain Menu");
        Printer.println("&7Please select an option:");
        Printer.println("");
    }

    public MainMenuPresenter.MainMenuOption getMainMenuSelection() {
        return new SelectInput<MainMenuPresenter.MainMenuOption>()
                .setLabel("Option: ")
                .addOptions(
                        Arrays.stream(MainMenuPresenter.MainMenuOption.values())
                                .map(bp -> new SelectInput.Option<MainMenuPresenter.MainMenuOption>(bp.getLabel(), bp))
                                .toList()
                )
                .setRetryOnInvalid(true)
                .read();
    }

}
