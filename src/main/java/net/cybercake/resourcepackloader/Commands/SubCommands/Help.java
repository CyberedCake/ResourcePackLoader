package net.cybercake.resourcepackloader.Commands.SubCommands;

import net.cybercake.resourcepackloader.Commands.CommandManager;
import net.cybercake.resourcepackloader.Commands.SubCommand;
import net.cybercake.resourcepackloader.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Help extends SubCommand {

    public Help() {
        super("help", "", "Prints this help message.", "/rploader help", "");
    }

    @Override
    public void perform(CommandSender sender, String[] args, Command command) {
        // Overridden and moved to main CommandManager class
        sender.sendMessage(Utils.chat("&cAn error occurred!"));
    }

    @Override
    public List<String> tab(CommandSender sender, String[] args) {
        return CommandManager.emptyList;
    }
}
