package project.neutrino.library.repository;

import project.neutrino.library.model.Media;
import project.neutrino.library.model.MediaType;
import project.neutrino.library.model.Status;

import java.util.List;

public interface MediaRepository {

    Media findById(int id, MediaType type);

    List<Media> findByName(String name, MediaType type);

    List<Media> findByNameContaining(String chars, MediaType type);

    List<Media> findAll(MediaType type);

    List<Media> findByGenre(String genre, MediaType type);

    List<Media> findByCollection(String collection, MediaType type);

    List<Media> findByCreator(String creator, MediaType type);

    List<Media> findByStatus(Status status, MediaType type);

    int create(Media media);

    void update(Media media);

    void delete(int id);
}
