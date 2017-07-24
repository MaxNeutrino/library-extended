package project.neutrino.library.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project.neutrino.library.ChosenMediaType;
import project.neutrino.library.model.Media;
import project.neutrino.library.model.Status;
import project.neutrino.library.repository.MediaRepository;
import project.neutrino.library.service.MediaService;

import java.util.List;

public class MediaServiceImpl implements MediaService {

    private Logger LOG = LogManager.getLogger(this.getClass());

    private MediaRepository repository;

    public MediaServiceImpl(MediaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Media find(int id) {
        LOG.info("find by id = ", id);
        return repository.findById(id, ChosenMediaType.TYPE);
    }

    @Override
    public List<Media> find(String name) {
        LOG.info("find by name = " + name);
        return repository.findByName(name, ChosenMediaType.TYPE);
    }

    @Override
    public List<Media> findByNameContaining(String chars) {
        LOG.info("findByNameContaining " + chars);
        return repository.findByNameContaining(chars, ChosenMediaType.TYPE);
    }

    @Override
    public List<Media> findAll() {
        LOG.info("findAll");
        return repository.findAll(ChosenMediaType.TYPE);
    }

    @Override
    public List<Media> findByGenre(String genre) {
        LOG.info("findByGenre " + genre);
        return repository.findByGenre(genre, ChosenMediaType.TYPE);
    }

    @Override
    public List<Media> findByCollection(String collection) {
        LOG.info("findByCollection " + collection);
        return repository.findByCollection(collection, ChosenMediaType.TYPE);
    }

    @Override
    public List<Media> findByCreator(String creator) {
        LOG.info("findByCreator " + creator);
        return repository.findByCreator(creator, ChosenMediaType.TYPE);
    }

    @Override
    public List<Media> findByStatus(Status status) {
        LOG.info("findByStatus " + status);
        return repository.findByStatus(status, ChosenMediaType.TYPE);
    }

    @Override
    public int save(Media media) {
        LOG.info("save or update media = ", media);
        if (media.getId() == null) {
            return repository.create(media);
        } else {
            repository.update(media);
            return media.getId();
        }
    }

    @Override
    public void delete(int id) {
        LOG.info("delete id = ", id);
        repository.delete(id);
    }
}
