package project.neutrino.library.ui.command.impl;

import project.neutrino.library.ChosenMediaType;
import project.neutrino.library.model.Media;
import project.neutrino.library.model.Status;
import project.neutrino.library.service.MediaService;
import project.neutrino.library.ui.CliController;
import project.neutrino.library.ui.command.Command;

import java.io.IOException;
import java.util.Arrays;

public class AddCommand implements Command {

    public final static String NAME = "add";

    private MediaService mediaService;

    public AddCommand(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @Override
    public void execute() {

        try {
            Media media = fillFields();
            mediaService.save(media);
            String message = String.format("media %s was added", media.getName());
            CliController.printMessage(message);
        } catch (Exception e) {
            CliController.getController()
                    .printCanceledMessage();
        }
    }

    @Override
    public String getDescription() {
        return "add - to add book";
    }

    private Media fillFields() throws IOException {
        String name = printAndGetNotNullInputted("name");
        String creator = printAndGetInputted(ChosenMediaType.TYPE.creator);
        String collection = printAndGetInputted(ChosenMediaType.TYPE.collection);
        String genre = printAndGetInputted("genre");
        Status status = printStatusesAndWaitResponse();

        return new Media(name, creator, collection, genre, status, ChosenMediaType.TYPE);

    }

    private String printAndGetNotNullInputted(String fieldName) throws IOException {
        String inputted = printAndGetInputted(fieldName);
        if (inputted.length() == 0)
            throw new IllegalArgumentException();

        return inputted;
    }

    private String printAndGetInputted(String fieldName) throws IOException {
        String message = String.format("Enter %s", fieldName);
        CliController.printMessage(message);
        return CliController
                .getController()
                .getInputtedLine();
    }

    private Status printStatusesAndWaitResponse() throws IOException {
        Status[] statuses = Status.values();
        CliController.printMessage("Choose status");
        return CliController
                .getController()
                .printAndWaitResponse(Arrays.asList(statuses), Status::name);
    }
}
