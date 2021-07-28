package net.cybercake.resourcepackloader.Commands.SubCommands;

import net.cybercake.resourcepackloader.Commands.CommandManager;
import net.cybercake.resourcepackloader.Commands.SubCommand;
import net.cybercake.resourcepackloader.Listeners.JoinEvent;
import net.cybercake.resourcepackloader.Main;
import net.cybercake.resourcepackloader.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

public class SendPack extends SubCommand {

    public SendPack() {
        super("sendpack", "rploader.sendpack", "Sends the resource pack to all online players.", "/rploader sendpack [-ignorePerms]", new String[]{"sp"});
    }

    @Override
    public void perform(CommandSender sender, String[] args, Command command) {
        if(Main.getMainConfig().getBoolean("sendResourcePackBasedOnPermissions")) {
            if(args.length <= 1) {
                sender.sendMessage(Utils.chat("&7Sending the texture pack to all online players, respecting permissions..."));
                for(Player p : Bukkit.getOnlinePlayers()) {
                    if(JoinEvent.sendRp(p)) {
                        sender.sendMessage(Utils.chat("&aSent resource pack to &e" + p.getName()));
                    }else{
                        sender.sendMessage(Utils.chat("&cFailed or did not send resource pack to &e" + p.getName()));
                    }
                }
            }else{
                if(args[1].equals("-ignorePerms")) {
                    sender.sendMessage(Utils.chat("&7Sending the texture pack to all online players, disrespecting permissions..."));
                    for(Player p : Bukkit.getOnlinePlayers()) {
                        JoinEvent.sendActualRP(p);
                        sender.sendMessage(Utils.chat("&aSent resource pack to &e" + p.getName()));
                    }
                }else{
                    sender.sendMessage(Utils.chat("&cUnknown or invalid flag: &8'" + args[1] + "&8'"));
                }
            }
        }else{
            sender.sendMessage(Utils.chat("&7Sending the texture pack to all online players, disrespecting permissions..."));
            for(Player p : Bukkit.getOnlinePlayers()) {
                JoinEvent.sendActualRP(p);
                sender.sendMessage(Utils.chat("&aSent resource pack to &e" + p.getName()));
            }
        }
    }

    @Override
    public List<String> tab(CommandSender sender, String[] args) {
        if(args.length <= 2) {
            if(Main.getMainConfig().getBoolean("sendResourcePackBasedOnPermissions")) {
                return CommandManager.createReturnList(Arrays.asList("-ignorePerms"), args[1]);
            }
        }
        return CommandManager.emptyList;
    }
}
