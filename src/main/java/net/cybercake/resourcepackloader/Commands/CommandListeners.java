package net.cybercake.resourcepackloader.Commands;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class CommandListeners implements Listener {

    @EventHandler
    public void onCommandSendEvent(PlayerCommandSendEvent e) {
        Player p = e.getPlayer();

        CommandManager manager = new CommandManager();

        e.getCommands().remove("resourcepackloader:rploader");
        e.getCommands().remove("resourcepackloader:resourcepackloader");
        e.getCommands().remove("resourcepackloader:rpl");

        if(manager.getSubCommandsOnlyWithPerms(p).size() <= 1) {
            e.getCommands().remove("rploader");
            e.getCommands().remove("resourcepackloader");
            e.getCommands().remove("rpl");
        }
    }

}
