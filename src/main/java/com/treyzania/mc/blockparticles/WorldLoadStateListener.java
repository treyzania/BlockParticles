package com.treyzania.mc.blockparticles;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;

public class WorldLoadStateListener implements Listener {
	
	private DataManager manager;
	
	public WorldLoadStateListener(DataManager man) {
		this.manager = man;
	}
	
	@EventHandler
	public void onWorldLoad(WorldLoadEvent event) {
		
		World w = event.getWorld();
		
		Bukkit.getLogger().info("Loading BlockParticles data for " + w.getName() + ".");
		this.manager.loadWorld(w);
		Bukkit.getLogger().fine("Done!");
		
	}
	
	@EventHandler
	public void onWorldUnload(WorldUnloadEvent event) {

		World w = event.getWorld();
		
		Bukkit.getLogger().info("Saving and unloading BlockParticles data for " + w.getName() + ".");
		this.manager.flushAndUnloadWorld(w);
		Bukkit.getLogger().fine("Done!");
		
	}
	
}
