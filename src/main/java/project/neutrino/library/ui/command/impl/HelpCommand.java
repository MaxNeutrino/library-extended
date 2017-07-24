package project.neutrino.library.ui.command.impl;

import project.neutrino.library.ui.CliController;
import project.neutrino.library.ui.command.Command;

import java.util.Map;

public class HelpCommand implements Command {

    public final static String NAME = "help";

    private Map<String, Command> commands;

    @Override
    public void execute() {
        StringBuilder helpMessage = new StringBuilder("Type:\n");

        commands.forEach((key, value) ->
                helpMessage.append(
                        String.format("\t%s\n", value.getDescription())
                ));

        CliController.printMessage(helpMessage.toString());
    }

    @Override
    public String getDescription() {
        return "help - print all commands";
    }

    public void setCommands(Map<String, Command> commands) {
        this.commands = commands;
    }
}
