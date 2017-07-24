package project.neutrino.library.ui.command.impl;

import project.neutrino.library.ChosenMediaType;
import project.neutrino.library.model.Media;
import project.neutrino.library.model.Status;
import project.neutrino.library.service.MediaService;
import project.neutrino.library.ui.CliController;
import project.neutrino.library.ui.command.AbstractModifyCommand;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EditCommand extends AbstractModifyCommand {

    public final static String NAME = "edit";

    public EditCommand(MediaService mediaService) {
        super(mediaService);
    }

    @Override
    protected void modify(Media media) throws IOException {
        media = fillFields(media);
        mediaService.save(media);

        String message = String.format("%s was updated",
                ChosenMediaType.TYPE
                .name()
                .toLowerCase());
        CliController.printMessage(message);
    }

    @Override
    public String getDescription() {
        return "edit - to edit book";
    }

    private Media fillFields(Media media) throws IOException {
        String name = printAndGetInputted("name", media.getName());
        String creator = printAndGetInputted(ChosenMediaType.TYPE.creator, media.getCreator().get());
        String collection = printAndGetInputted(ChosenMediaType.TYPE.collection, media.getCollection().get());
        String genre = printAndGetInputted("genre", media.getGenre().get());

        List<Status> statuses = Arrays.asList(Status.values());
        Status status = CliController.getController()
                .printAndWaitResponse(statuses, Status::name);

        media.setName(name);
        media.setCreator(creator);
        media.setCollection(collection);
        media.setGenre(genre);
        media.setStatus(status);

        return media;
    }

    private String printAndGetInputted(String fieldName, String oldValue) throws IOException {
        String message = String.format("Type new %s. If you don't want change it, just press 'Enter'", fieldName);
        CliController.printMessage(message);
        String inputted = CliController.getController().getInputtedLine();
        if (inputted.length() == 0)
            return oldValue;
        else
            return inputted;
    }
}
