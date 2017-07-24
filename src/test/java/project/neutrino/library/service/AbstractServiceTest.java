package project.neutrino.library.service;

import org.junit.Before;
import project.neutrino.library.ChosenMediaType;
import project.neutrino.library.model.MediaType;
import project.neutrino.library.repository.MediaRepository;
import project.neutrino.library.repository.ConnectionManager;
import project.neutrino.library.repository.RepositoryFactory;
import project.neutrino.library.repository.jdbi.JdbiMediaRepository;
import project.neutrino.library.service.impl.MediaServiceImpl;

public class AbstractServiceTest {

    protected MediaService mediaService;

    @Before
    public void setUp() {
        ConnectionManager.createAndConnect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        ConnectionManager connectionManager = ConnectionManager.getManager();
        connectionManager.executeSqlScript("db/initDB.sql");

        MediaRepository mediaRepository = RepositoryFactory.create(JdbiMediaRepository.class);
        mediaService = new MediaServiceImpl(mediaRepository);

        ChosenMediaType.TYPE = MediaType.BOOK;
    }
}
