package project.neutrino.library.ui.command.impl;

import project.neutrino.library.ChosenMediaType;
import project.neutrino.library.model.Media;
import project.neutrino.library.service.MediaService;
import project.neutrino.library.ui.CliController;
import project.neutrino.library.ui.command.AbstractModifyCommand;

import java.io.IOException;

public class InfoCommand extends AbstractModifyCommand {

    public final static String NAME = "info";

    public InfoCommand(MediaService mediaService) {
        super(mediaService);
    }

    @Override
    protected void modify(Media media) throws IOException {
        String info = ChosenMediaType.TYPE.name() + "\n\t" +
                "name - " + media.getName() + "\n\t" +
                ChosenMediaType.TYPE.creator + " - " + media.getCreator().orElse("") + "\n\t" +
                ChosenMediaType.TYPE.collection + " - " + media.getCollection().orElse("") + "\n\t" +
                "genre - " + media.getGenre().orElse("") + "\n\t" +
                "status - " + media.getStatus().name();

        CliController.printMessage(info);
    }

    @Override
    public String getDescription() {
        return "info - print full info for chosen media";
    }
}
