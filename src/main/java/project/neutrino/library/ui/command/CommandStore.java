package project.neutrino.library.ui.command;

import project.neutrino.library.service.MediaService;
import project.neutrino.library.ui.command.impl.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandStore {

    private Map<String, Command> commandStore;

    private MediaService service;

    public CommandStore(MediaService service) {
        this.service = service;
        createCommands();
    }

    public Optional<Command> getCommand(String commandName) {
        return Optional.ofNullable(commandStore.get(commandName));
    }

    private void createCommands() {
        Map<String, Command> commands = new HashMap<>();

        commands.put(AddCommand.NAME, new AddCommand(service));
        commands.put(AllCommand.NAME, new AllCommand(service));
        commands.put(EditCommand.NAME, new EditCommand(service));
        commands.put(ExitCommand.NAME, new ExitCommand());
        commands.put(HelpCommand.NAME, new HelpCommand());
        commands.put(RemoveCommand.NAME, new RemoveCommand(service));
        commands.put(ChStatusCommand.NAME, new ChStatusCommand(service));
        commands.put(GoCommand.NAME, new GoCommand());
        commands.put(InfoCommand.NAME, new InfoCommand(service));

        commandStore = Collections.unmodifiableMap(commands);

        HelpCommand helpCommand = (HelpCommand) commands.get(HelpCommand.NAME);
        helpCommand.setCommands(commands);
    }
}
