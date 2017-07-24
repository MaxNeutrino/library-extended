package project.neutrino.library.ui.command.impl;

import project.neutrino.library.model.Media;
import project.neutrino.library.service.MediaService;
import project.neutrino.library.ui.CliController;
import project.neutrino.library.ui.command.AbstractModifyCommand;

import java.io.IOException;

public class RemoveCommand extends AbstractModifyCommand {

    public final static String NAME = "remove";

    public RemoveCommand(MediaService mediaService) {
        super(mediaService);
    }

    @Override
    protected void modify(Media media) throws IOException {
        mediaService.delete(media.getId());
        String message = String.format("Media %s was removed", media.getName());
        CliController.printMessage(message);
    }

    @Override
    public String getDescription() {
        return "remove [book name] - to remove book";
    }
}
