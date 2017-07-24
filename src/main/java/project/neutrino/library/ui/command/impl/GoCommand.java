package project.neutrino.library.ui.command.impl;

import project.neutrino.library.ChosenMediaType;
import project.neutrino.library.model.MediaType;
import project.neutrino.library.ui.CliController;
import project.neutrino.library.ui.command.Command;

import java.util.Arrays;

public class GoCommand implements Command {

    public final static String NAME = "go";

    @Override
    public void execute() {
        try {
            MediaType[] types = MediaType.values();
            CliController.printMessage("Choose media type which you want manage:");
            ChosenMediaType.TYPE = CliController.getController()
                    .printAndWaitResponse(Arrays.asList(types), MediaType::name);

            CliController.printMessage("You switched to " + ChosenMediaType.TYPE.pluralName);

        } catch (Exception e) {
            CliController.getController()
                    .printCanceledMessage();
        }
    }

    @Override
    public String getDescription() {
        return "go - choose media type which you want manage (book, music, movie)";
    }
}
