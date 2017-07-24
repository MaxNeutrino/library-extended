package project.neutrino.library.ui;

import project.neutrino.library.service.MediaService;
import project.neutrino.library.ui.command.Command;
import project.neutrino.library.ui.command.CommandNotFoundExecutor;
import project.neutrino.library.ui.command.CommandStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;

public class CliController {

    private final String helloText = "Print 'help' for see commands list";

    private final String canceledMessage = "canceled by user";

    private CommandStore commandStore;

    private static CliController CONTROLLER;

    private BufferedReader reader;

    private CliController() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void printCanceledMessage() {
        printMessage(canceledMessage);
    }

    public String getInputtedLine() throws IOException {
        return reader.readLine().trim();
    }

    public <T> T printAndWaitResponse(List<T> list, Function<T, String> function) throws IOException {
        int i = 0;
        for (T item : list) {
            String message = String.format("\t%d. %s", i + 1, function.apply(item));
            printMessage(message);
            i++;
        }

        int response = Integer.parseInt(getInputtedLine());
        if (response > list.size() || response <= 0)
            throw new IllegalArgumentException();

        return list.get(response - 1);
    }

    public void run() throws IOException {
        commandStore.getCommand("go")
                .ifPresent(Command::execute);

        printMessage(helloText);
        String inputted = "";

        while (!inputted.equals("exit")) {
            inputted = getInputtedLine();
            executeCommand(inputted);
        }
    }

    public void close() throws IOException {
        reader.close();
    }

    public static void initializeController(MediaService service) throws Exception {
        if (CONTROLLER == null) {
            CONTROLLER = new CliController();
            CONTROLLER.commandStore = new CommandStore(service);
        }
    }

    public static CliController getController() {
        return CONTROLLER;
    }

    public static void printMessage(String msg) {
        System.out.println(msg);
    }

    public static void printError(String err) {
        System.err.println(err);
    }

    private void executeCommand(String commandName) {
        Command command = commandStore
                .getCommand(commandName)
                .orElse(new CommandNotFoundExecutor(commandName));

        command.execute();
    }

    private void executeCommand(String inputted, int commandEndIndex) {
        String commandName = inputted.substring(0, commandEndIndex);
        String arg = inputted
                .substring(commandEndIndex)
                .trim();

        executeCommand(commandName);
    }
}
