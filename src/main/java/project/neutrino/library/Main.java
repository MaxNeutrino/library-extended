package project.neutrino.library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project.neutrino.library.repository.MediaRepository;
import project.neutrino.library.repository.RepositoryFactory;
import project.neutrino.library.repository.jdbi.JdbiMediaRepository;
import project.neutrino.library.service.MediaService;
import project.neutrino.library.service.impl.MediaServiceImpl;
import project.neutrino.library.ui.CliController;

public class Main {

    public static void main(String[] args) {
        System.out.println("Please wait");
        final Logger LOG = LogManager.getLogger();
        try {
            MediaRepository repository = RepositoryFactory.create(JdbiMediaRepository.class);
            MediaService service = new MediaServiceImpl(repository);

            CliController.initializeController(service);
            CliController controller = CliController.getController();
            controller.run();

        } catch (Exception e) {
            LOG.error("", e);
            String logFilePath = System.getProperty("user.home") + "/.library.log";
            System.err.println(
                    String.format("Program finished with error, see file %s for details", logFilePath));
            System.exit(1);
        }
    }
}
