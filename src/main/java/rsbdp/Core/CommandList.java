package rsbdp.Core;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import rsbdp.commands.*;

public class CommandList {

    private Map<String, Consumer<String>> commandMap;

    public CommandList() {
        commandMap = new HashMap<>();

        // Add commands and their corresponding actions
        commandMap.put("echo", echo::main);
        commandMap.put("getvar", getvar::main);
        commandMap.put("help", help::main);
        commandMap.put("hlp", help::main);
        commandMap.put("log", log::main);
        commandMap.put("cls", ConsoleManagement::clearConsole);
        commandMap.put("clear", ConsoleManagement::clearConsole);
    }

    // Method to execute a command based on input
    public void executeCommand(String command, String argument) {
        Consumer<String> action = commandMap.get(command.toLowerCase());
        if (action != null) {
            action.accept(argument);  // Pass the argument to the command
            Main.UserInput();
        } else {
            System.out.println("Unknown command: " + command);
            Main.UserInput();
        }
    }
}
