package rsbdp.commands;

import rsbdp.Core.CommandList;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GraphicsCard;

public class polarfetch {
    public static void main(String arguementThatIsntLoved) {
        // Sorry for this very inefficient code
        CommandList commandList = new CommandList();
        commandList.executeCommand2("echo", "Test");

        commandList.executeCommand2("echo", "Test2 (Test got replaced by Test2)");
    }
}
