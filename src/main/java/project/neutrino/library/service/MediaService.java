package project.neutrino.library.service;

import project.neutrino.library.model.Media;
import project.neutrino.library.model.Status;

import java.util.List;

public interface MediaService {

    Media find(int id);

    List<Media> find(String name);

    List<Media> findByNameContaining(String chars);

    List<Media> findAll();

    List<Media> findByGenre(String genre);

    List<Media> findByCollection(String collection);

    List<Media> findByCreator(String creator);

    List<Media> findByStatus(Status status);

    int save(Media media);

    void delete(int id);
}
