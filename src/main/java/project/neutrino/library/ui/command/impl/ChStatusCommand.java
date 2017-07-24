package project.neutrino.library.ui.command.impl;

import project.neutrino.library.model.Media;
import project.neutrino.library.model.Status;
import project.neutrino.library.service.MediaService;
import project.neutrino.library.ui.CliController;
import project.neutrino.library.ui.command.AbstractModifyCommand;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ChStatusCommand extends AbstractModifyCommand {

    public final static String NAME = "chstatus";

    public ChStatusCommand(MediaService mediaService) {
        super(mediaService);
    }

    @Override
    protected void modify(Media media) throws IOException {
        List<Status> statuses = Arrays.asList(Status.values());
        Status status = CliController.getController()
                .printAndWaitResponse(statuses, Status::name);

        media.setStatus(status);
        mediaService.save(media);
        String message = String.format("\"%s\" status was changed to %s", media.getName(), status.name());
        CliController.printMessage(message);
    }

    @Override
    public String getDescription() {
        return "chstatus - change status for chosen media";
    }
}
