package org.strassburger.subscriptionmanager.view;

import org.strassburger.tui4j.formatting.Printer;
import org.strassburger.tui4j.input.SelectInput;

public class MainMenuView {

    public String getMainMenuSelection() {
        return new SelectInput()
                .addOptions("Select Profile", "Create Profile", "Delete Profile", "Exit")
                .setLabel("Main Menu")
                .setRetryOnInvalid(true)
                .read();
    }

    public void showGoodbyeMessage() {
        Printer.println("&aGoodbye!");
    }

}
