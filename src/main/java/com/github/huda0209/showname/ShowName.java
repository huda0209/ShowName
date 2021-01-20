package com.github.huda0209.showname;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShowName extends JavaPlugin implements CommandExecutor {

    static String pluginName = "ShowName";
    String ServerName;


    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        loadName();
        setMode();

        getCommand("serverName").setExecutor(new serverName());
        getCommand("sn").setExecutor(new serverName());
        getCommand("showname").setExecutor(new showName());

        String EnableMsg =
                "\n\n=============================\n" +
                "plugin name : " + pluginName + "\n" +
                "server name : " + ServerName + "\n" +
                "=============================\n";

        getLogger().info(EnableMsg);
    }

    @Override
    public void onDisable() {
        getLogger().info(pluginName + "was disable.");
    }


    public void loadName(){
        try{
            ServerName = getConfig().getString("ServerName");

            if(ServerName ==null) return;
            if (ServerName.isEmpty()) {
                ServerName = null;
            }
        }catch (Exception exception){
            ServerName = null;
        }
    }

    public void setMode(){
        if(ServerName == null){
            getLogger().warning("Server name DON'T set! this plugin was disabled.");
            setEnabled(false);
        }
    }

    private class serverName implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if(sender.hasPermission("serverName")){
                sender.sendMessage("§7[§bShow Name§7]§rServer name : §a" + ServerName);
            }else{
                sender.sendMessage("§cYou don't have permission!");
            }
            return true;
        }
    }

    private class showName implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if(sender.hasPermission("showname")) {
                if (args[0].equalsIgnoreCase("reload")) {
                    reloadConfig();
                    loadName();
                    setMode();
                    sender.sendMessage("§7[§bShow Name§7]§r§a reload successful.");
                }
            }else{
                sender.sendMessage("§cYou don't have permission!");
            }
            return true;
        }
    }
}
