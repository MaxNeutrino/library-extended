package project.neutrino.library.repository.jdbi;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import project.neutrino.library.model.Media;
import project.neutrino.library.model.MediaType;
import project.neutrino.library.model.Status;
import project.neutrino.library.repository.MediaRepository;
import project.neutrino.library.repository.jdbi.binder.BindMedia;
import project.neutrino.library.repository.jdbi.binder.BindMediaType;
import project.neutrino.library.repository.jdbi.binder.BindStatus;
import project.neutrino.library.repository.jdbi.mapper.MediaMapper;

import java.util.List;

@RegisterMapper(MediaMapper.class)
public interface JdbiMediaRepository extends MediaRepository {

    @Override
    @SqlQuery("SELECT * FROM media WHERE id = :id AND type = :type")
    Media findById(@Bind("id") int id, @BindMediaType MediaType type);

    @Override
    @SqlQuery("SELECT * FROM media WHERE name = :name AND type = :type")
    List<Media> findByName(@Bind("name") String name, @BindMediaType MediaType type);

    @Override
    @SqlQuery("SELECT * FROM media WHERE name LIKE concat(:chars, '%') AND type = :type ORDER BY name")
    List<Media> findByNameContaining(@Bind("chars") String chars, @BindMediaType MediaType type);

    @Override
    @SqlQuery("SELECT * FROM media WHERE type = :type ORDER BY name")
    List<Media> findAll(@BindMediaType MediaType type);

    @Override
    @SqlQuery("SELECT * FROM media WHERE genre = :genre AND type = :type ORDER BY name")
    List<Media> findByGenre(@Bind("genre") String genre, @BindMediaType MediaType type);

    @Override
    @SqlQuery("SELECT * FROM media WHERE collection = :collection AND type = :type ORDER BY name")
    List<Media> findByCollection(@Bind("collection") String collection, @BindMediaType MediaType type);

    @Override
    @SqlQuery("SELECT * FROM media WHERE creator = :creator AND type = :type ORDER BY name")
    List<Media> findByCreator(@Bind("creator") String creator, @BindMediaType MediaType type);

    @Override
    @SqlQuery("SELECT * FROM media WHERE status = :status AND type = :type ORDER BY name")
    List<Media> findByStatus(@BindStatus Status status, @BindMediaType MediaType type);

    @Override
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO media (name, creator, collection, genre, status, type) " +
            "VALUES (:name, :creator, :collection, :genre, :status, :type)")
    int create(@BindMedia Media media);

    @Override
    @SqlUpdate("UPDATE media SET " +
            "name = :name, " +
            "creator = :creator, " +
            "collection = :collection, " +
            "genre = :genre, " +
            "status = :status, " +
            "type = :type " +
            "WHERE id = :id")
    void update(@BindMedia Media media);

    @Override
    @SqlUpdate("DELETE FROM media WHERE id = :id")
    void delete(@Bind("id") int id);

    void close();
}
