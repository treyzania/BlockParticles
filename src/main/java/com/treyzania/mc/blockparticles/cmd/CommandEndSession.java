package com.treyzania.mc.blockparticles.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.treyzania.mc.blockparticles.DataManager;
import com.treyzania.mc.blockparticles.PlacementSession;

public class CommandEndSession implements CommandExecutor {
	
	private DataManager manager;
	
	public CommandEndSession(DataManager man) {
		this.manager = man;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			
			sender.sendMessage("You need to be a player to execute this command.");
			return true;
			
		}
		
		Player player = (Player) sender;
		
		PlacementSession ps = this.manager.getSession(player);
		if (ps != null) {
			
			ps.group.flush();
			this.manager.endSession(player);
			sender.sendMessage("Session ended.  You can place blocks normally now.");
			
		} else {
			sender.sendMessage("You didn't have any sessions active.");
		}
		
		return true;
		
	}

}
