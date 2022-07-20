package me.davidams789.LiveAms.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.davidams789.LiveAms.Main;
import me.davidams789.LiveAms.utils.Cooldown;

public class Live implements CommandExecutor{

    private Main plugin;
    public Live(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player p = (Player) sender;

        if (p.hasPermission("directo.liveams")){
            FileConfiguration config = plugin.getConfig();
            String pathtime = "Players."+p.getUniqueId()+".cooldown-recompensa";
            Cooldown c = new Cooldown(plugin);
            String cooldown = c.getCooldown(p);

            if (args.length == 1){
                String directo = args[0];

                if(directo.startsWith("https://www.youtube") || directo.startsWith("https://www.twitch")){
                    if(cooldown.equals("-1")){
                        long millis = System.currentTimeMillis();
                        config.set(pathtime, millis);
                        plugin.saveConfig();
                        
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8&m------------------------------------------------------------------------------------------"));
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"               &d&l" +  p.getName() + " &5&lEsta en directo"));
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "         &eLink: &c" + directo));
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8&m------------------------------------------------------------------------------------------"));
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDebes esperar &4" + cooldown + " &cPara volver a utilizar ese comando!"));
                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Ese link no es valido!"));
                }

                
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&5&lLiveAms&8&l] &cUso: /directo <Link>"));
            }
        } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo tienes permisos para ejecutar ese comando"));
        }

        return true;
    }
    
}
