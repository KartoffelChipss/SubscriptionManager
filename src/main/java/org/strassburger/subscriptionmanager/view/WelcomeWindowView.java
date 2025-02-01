package org.strassburger.subscriptionmanager.view;

import org.strassburger.tui4j.formatting.Printer;
import org.strassburger.tui4j.input.ContinueInput;

public class WelcomeWindowView {
    public void showWelcomeMessage() {
        Printer.println(" ");
        Printer.println("""
        &a          _____       _       __  __                                  \s
        &a         / ____|     | |     |  \\/  |                                 \s
        &a        | (___  _   _| |__   | \\  / | __ _ _ __   __ _  __ _  ___ _ __\s
        &a         \\___ \\| | | | '_ \\  | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '__|
        &a         ____) | |_| | |_) | | |  | | (_| | | | | (_| | (_| |  __/ |  \s
        &a        |_____/ \\__,_|_.__/  |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_|  \s
        &a                                                        __/ |         \s
        &a                                                       |___/          \s""");

        String spaces = " ".repeat(8);
        Printer.println(spaces + "&7             Welcome to the Subscription Manager!");

        for (int i = 0; i < 3; i++) {
            Printer.println(" ");
        }
    }

    public void enterToContinue() {
        new ContinueInput()
                .setLabel("                           Press ENTER to continue")
                .read();
    }
}
