package project.neutrino.library.ui.command.filter;

import project.neutrino.library.model.Media;

import java.io.IOException;
import java.util.List;

public interface MediaFilterChooseList {

    List<Media> filter() throws IOException;
}
