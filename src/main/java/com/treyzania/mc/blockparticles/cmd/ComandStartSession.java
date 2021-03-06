package com.treyzania.mc.blockparticles.cmd;

import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.treyzania.mc.blockparticles.ConfigSource;
import com.treyzania.mc.blockparticles.DataManager;
import com.treyzania.mc.blockparticles.Perms;
import com.treyzania.mc.blockparticles.PlacementSession;

public class ComandStartSession implements CommandExecutor {
	
	private ConfigSource config;
	private DataManager manager;
	
	public ComandStartSession(DataManager man, ConfigSource cfg) {
		
		this.manager = man;
		this.config = cfg;
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!sender.hasPermission(Perms.CMD_CREATE)) {
			
			sender.sendMessage("You don't have permission to use this command.");
			return true;
			
		}
		
		if (!(sender instanceof Player)) {
			
			sender.sendMessage("You need to be a player to run this.");
			return true;
			
		}
		
		Player player = (Player) sender;
		
		if (args.length != 2) return false;
		
		String name = args[0];
		String typeStr = args[1];
		Particle p = null;
		
		try {
			p = Particle.valueOf(typeStr);
		} catch (Exception e) {
			
			sender.sendMessage("Uh, sorry, but " + typeStr + " isn't a particle.");
			if (p == null) return false;
			
		}
		
		if (!sender.hasPermission(Perms.TYPE_PREFIX(p))) {
			
			sender.sendMessage("You don't have permission to use this particle type.");
			return true;
			
		}
		
		PlacementSession ps = this.manager.startSession(player.getWorld(), player, name);
		ps.group.setRange((float) this.config.getDefaultActivationRange());
		ps.group.setType(p);
		
		sender.sendMessage("Started new session for placing blocks in " + player.getWorld().getName() + " for group " + name + ".");
		sender.sendMessage("Using meta type: " + p.getDataType().getName());
		
		return true;
		
	}

}
