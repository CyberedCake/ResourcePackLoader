package net.cybercake.resourcepackloader.Listeners;

import net.cybercake.resourcepackloader.Main;
import net.cybercake.resourcepackloader.Utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(!Main.getMainConfig().getBoolean("enabled")) { return; }

        Player p = e.getPlayer();

        if(sendRp(p)) {
            p.setResourcePack(Objects.requireNonNull(Main.getMainConfig().getString("resourcePackLink")), "", false, Component.text(Utils.chat(Main.getMainConfig().getString("resourcePackPrompt"))));

        }
    }

    public static boolean sendRp(Player p) {
        if(Main.getMainConfig().getBoolean("sendResourcePackBasedOnPermissions")) {
            if(!p.hasPermission("rploader.bypassrp")) {
                return true;
            }
        }else if(!Main.getMainConfig().getBoolean("sendResourcePackBasedOnPermissions")) {
            return true;
        }
        return false;
    }

}
