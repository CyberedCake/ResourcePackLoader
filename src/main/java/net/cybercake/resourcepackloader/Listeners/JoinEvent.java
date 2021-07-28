package net.cybercake.resourcepackloader.Listeners;

import net.cybercake.resourcepackloader.Main;
import net.cybercake.resourcepackloader.Utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Objects;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(!Main.getMainConfig().getBoolean("enabled")) { return; }

        Player p = e.getPlayer();

        sendRp(p);
    }

    public static boolean sendRp(Player p) {
        boolean sendRp = false;
        if(Main.getMainConfig().getBoolean("sendResourcePackBasedOnPermissions")) {
            if(!p.hasPermission("rploader.bypassrp")) {
                sendRp = true;
            }
        }else if(!Main.getMainConfig().getBoolean("sendResourcePackBasedOnPermissions")) {
            sendRp = true;
        }
        if(sendRp) {
            if(sendActualRP(p)) {
                sendActualRP(p);
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean sendActualRP(Player p) {
        p.sendTitle();
        //p.setResourcePack("https://www.dropbox.com/s/dq4trpeopcax3y8/%5Bv1.0%5D%20Sky%20Scapers%20Resources.zip?dl=1", "", false, Component.text(Utils.chat(Main.getMainConfig().getString("resourcePackPrompt"))));
        p.setResourcePack(Main.getMainConfig().getString("resourcePackLink"), "", false, Component.text(Utils.chat(Main.getMainConfig().getString("resourcePackPrompt"))));
        return true;
   }

}
