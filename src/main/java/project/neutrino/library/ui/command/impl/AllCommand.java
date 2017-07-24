package project.neutrino.library.ui.command.impl;

import project.neutrino.library.ChosenMediaType;
import project.neutrino.library.model.Media;
import project.neutrino.library.model.Pair;
import project.neutrino.library.model.Status;
import project.neutrino.library.service.MediaService;
import project.neutrino.library.ui.CliController;
import project.neutrino.library.ui.command.Command;
import project.neutrino.library.ui.command.filter.MediaFilterChooseList;

import java.io.IOException;
import java.util.*;

public class AllCommand implements Command {

    public final static String NAME = "all";

    private MediaService mediaService;

    private Map<String, MediaFilterChooseList> filters;

    public AllCommand(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @Override
    public void execute() {
        try {
            if (filters == null)
                initializeFilters();

            CliController.printMessage("Choose filters");

            List<Pair> updatableFiltersName = createPairs();

            Pair filterName = CliController.getController()
                    .printAndWaitResponse(updatableFiltersName, Pair::getMessage);

            List<Media> mediaList = filters.get(filterName.getKey()).filter();
            print(mediaList);

        } catch (Exception e) {
            CliController.getController()
                    .printCanceledMessage();
        }
    }

    @Override
    public String getDescription() {
        return "all - to see all media in library";
    }

    private void initializeFilters() {
        filters = new HashMap<>();

        filters.put("all", () -> mediaService.findAll());

        filters.put("creator", () -> {
            String creator = printAndGetResponse("Enter " + ChosenMediaType.TYPE.creator);
            return mediaService.findByCreator(creator);
        });

        filters.put("collection", () -> {
            String collection = printAndGetResponse("Enter " + ChosenMediaType.TYPE.collection);
            return mediaService.findByCollection(collection);
        });

        filters.put("status", () -> {
            List<Status> statuses = Arrays.asList(Status.values());
            Status status = CliController
                    .getController()
                    .printAndWaitResponse(statuses, Status::name);

            return mediaService.findByStatus(status);
        });
    }

    private List<Pair> createPairs() {
        List<Pair> pairs = new ArrayList<>();
        pairs.add(new Pair("all", "all"));

        String creator = String.format("by %s", ChosenMediaType.TYPE.creator);
        pairs.add(new Pair("creator", creator));

        String collection = String.format("by %s", ChosenMediaType.TYPE.collection);
        pairs.add(new Pair("collection", collection));

        pairs.add(new Pair("status", "by status"));

        return pairs;
    }

    private String printAndGetResponse(String msg) throws IOException {
        CliController.printMessage(msg);
        String response = CliController.getController().getInputtedLine();

        if (response.length() == 0)
            throw new IllegalArgumentException();

        return response;
    }

    private void print(List<Media> mediaList) {
        if (mediaList.isEmpty()) {
            String emptyLibraryMessage = "Library is empty. Type 'help' to see how add " +
                    ChosenMediaType.TYPE
                            .name()
                            .toLowerCase();

            CliController.printMessage(emptyLibraryMessage);
        } else {
            CliController.printMessage(String.format("Our %s:", ChosenMediaType.TYPE.pluralName));
            mediaList.forEach(media -> CliController.printMessage(
                    String.format("\t%s \"%s\"", media.getCreator().orElse(""), media.getName())
            ));
        }
    }
}
