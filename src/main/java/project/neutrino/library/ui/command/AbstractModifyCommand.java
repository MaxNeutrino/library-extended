package project.neutrino.library.ui.command;

import project.neutrino.library.model.Media;
import project.neutrino.library.service.MediaService;
import project.neutrino.library.ui.CliController;

import java.io.IOException;
import java.util.List;

public abstract class AbstractModifyCommand implements Command {

    private final String fewBooksMessage =
            "we have few books with such name please choose one by typing a number of book:";

    protected MediaService mediaService;

    public AbstractModifyCommand(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @Override
    public void execute() {
        CliController.printMessage("Enter name:");
        try {
            String arg = CliController.getController().getInputtedLine();

            List<Media> mediaList = mediaService.findByNameContaining(arg);

            if (mediaList.size() > 1) {
                CliController.printMessage(fewBooksMessage);
                Media media = CliController
                        .getController()
                        .printAndWaitResponse(mediaList, Media::getName);

                modify(media);

            } else if (mediaList.size() == 1) {
                Media media = mediaList.get(0);
                modify(media);

            } else {
                CliController.printError("media with such name not found");
            }
        } catch (Exception e) {
            CliController.getController()
                    .printCanceledMessage();
        }
    }

    protected abstract void modify(Media media) throws IOException;
}
