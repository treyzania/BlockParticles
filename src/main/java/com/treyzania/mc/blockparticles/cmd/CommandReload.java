package com.treyzania.mc.blockparticles.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import com.treyzania.mc.blockparticles.Perms;

public class CommandReload implements CommandExecutor {
	
	private Plugin plugin;
	
	public CommandReload(Plugin p) {
		this.plugin = p;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!sender.hasPermission(Perms.CMD_RELOAD)) {
			
			sender.sendMessage("You don't have permission to run this command.");
			return true;
			
		}
		
		// XXX SHITTY SHITTY SHITTY SHITTY.
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.disablePlugin(this.plugin);
		pm.enablePlugin(this.plugin);
		
		return true;
		
	}

}
