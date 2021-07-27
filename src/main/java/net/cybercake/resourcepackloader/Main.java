package net.cybercake.resourcepackloader;

import net.cybercake.resourcepackloader.Commands.CommandListeners;
import net.cybercake.resourcepackloader.Commands.CommandManager;
import net.cybercake.resourcepackloader.Listeners.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main plugin;

    // vv "luckperms" may never be used but it's to tell the Utils class that luckperms does not exist
    public static boolean luckperms = false;

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();
        reloadConfig();

        registerCommand("rploader", new CommandManager());

        registerTabCompleter("rploader", new CommandManager());

        registerListener(new JoinEvent());
        registerListener(new CommandListeners());

        System.out.println("Enabled ResourcePackLoader v" + getPlugin(Main.class).getDescription().getVersion());
    }

    @Override
    public void onDisable() { System.out.println("Disabled ResourcePackLoader v" + getPlugin(Main.class).getDescription().getVersion()); }

    public static Main getPlugin() { return plugin; }

    // Used to get the configuration, easier than Main#getPlugin().getConfig()
    public static FileConfiguration getMainConfig() { return plugin.getConfig(); }

    public static void registerCommand(String name, CommandExecutor commandExecutor) { plugin.getCommand(name).setExecutor(commandExecutor); }
    public static void registerTabCompleter(String name, TabCompleter tabCompleter) { plugin.getCommand(name).setTabCompleter(tabCompleter); }
    public static void registerListener(Listener listener) { plugin.getServer().getPluginManager().registerEvents(listener, plugin); }
    public static void registerRunnable(Runnable runnable, long period) { Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, runnable, 10L, period); }

}
