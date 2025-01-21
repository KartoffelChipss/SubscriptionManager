package org.strassburger.subscriptionmanager.view;

import org.strassburger.subscriptionmanager.presenter.MainMenuPresenter;
import org.strassburger.tui4j.formatting.Printer;
import org.strassburger.tui4j.input.SelectInput;

import java.util.Arrays;

public class MainMenuView {

    public MainMenuPresenter.MainMenuOption getMainMenuSelection() {
        return new SelectInput<MainMenuPresenter.MainMenuOption>()
                .setLabel("Main Menu")
                .addOptions(
                        Arrays.stream(MainMenuPresenter.MainMenuOption.values())
                                .map(bp -> new SelectInput.Option<MainMenuPresenter.MainMenuOption>(bp.getLabel(), bp))
                                .toList()
                )
                .setRetryOnInvalid(true)
                .read();
    }

    public void showGoodbyeMessage() {
        Printer.println("&aGoodbye!");
    }

}
