package net.cybercake.resourcepackloader.Commands.SubCommands;

import net.cybercake.resourcepackloader.Commands.CommandManager;
import net.cybercake.resourcepackloader.Commands.SubCommand;
import net.cybercake.resourcepackloader.Main;
import net.cybercake.resourcepackloader.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Reload extends SubCommand {

    public Reload() {
        super("reload", "rploader.reload", "Reloads the configuration file.", "/rploader reload", new String[]{"rl"});
    }

    @Override
    public void perform(CommandSender sender, String[] args, Command command) {
        long mss = System.currentTimeMillis();
        Exception ex = null;
        try { Main.getPlugin().reloadConfig(); } catch (Exception e) { ex = e; }
        long msAfter = System.currentTimeMillis() - mss;
        if(ex != null) {
            sender.sendMessage(Utils.chat("&cFailed to reload the configuration file! Try again later: &8" + ex.toString()));
            for(StackTraceElement element : ex.getStackTrace()) {
                Bukkit.getLogger().severe(element.toString());
            }
        }else{
            sender.sendMessage(Utils.chat("&aSuccessfully reloaded the configuration file in &f" + msAfter + "&fms&a!"));
        }
    }

    @Override
    public List<String> tab(CommandSender sender, String[] args) {
        return CommandManager.emptyList;
    }

}
