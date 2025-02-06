package org.strassburger.subscriptionmanager.view.profiles;

import org.strassburger.subscriptionmanager.model.entity.Profile;
import org.strassburger.tui4j.formatting.Printer;
import org.strassburger.tui4j.input.SelectInput;

import java.util.List;

public class SelectProfileView {

    public void sendStartMessage() {
        Printer.println("");
        Printer.println("&a&nSelect Profile");
        Printer.println("&7Please select a profile:");
        Printer.println("");
    }

    public int readProfileId(List<Profile> profiles) {
        SelectInput<Integer> select = new SelectInput<Integer>()
                .setLabel("Profile: ")
                .addOptions(profiles.stream().map(
                        p -> new SelectInput.Option<>(p.getName(),p.getId())
                ).toList());

        // TODO: Implement option to add or delete profiles (use negative numbers)

        select.addOption("Exit", -1);

        return select.read();
    }

    public void showGoodbyeMessage() {
        Printer.println("&aGoodbye!");
    }

}
