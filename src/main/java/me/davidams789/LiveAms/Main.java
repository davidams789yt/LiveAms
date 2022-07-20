package me.davidams789.LiveAms;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import me.davidams789.LiveAms.commands.Live;

public class Main extends JavaPlugin{

    public String rutaConfig;

    public void onEnable(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&8&l[&5&lLiveAms&8&l] &aEl plugin se activo correctamente"));
        registerCommands();
        registerConfig();
    }
    
    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&8&l[&5&lLiveAms&8&l] &cEl plugin ha sido desactivado"));
    }

    public void registerCommands(){
        getCommand("directo").setExecutor(new Live(this));
    }

    public void registerConfig(){
        File config = new File(this.getDataFolder(), "cooldowns.yml");
        rutaConfig = config.getPath();
        if(!config.exists()){
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }
}
