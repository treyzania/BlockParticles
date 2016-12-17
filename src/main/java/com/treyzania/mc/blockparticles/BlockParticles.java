package com.treyzania.mc.blockparticles;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockParticles extends JavaPlugin {
	
	private ConfigSource config;
	
	private DataManager manager;
	
	@Override
	public void onLoad() {
		
		this.saveDefaultConfig();
		if (this.config == null) this.config = new YmlConfigSource(this.getConfig());
		
	}
	
	@Override
	public void onEnable() {
		
		// Utils.
		this.manager = new DataManager();
		
		// Listeners.
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new WorldLoadStateListener(this.manager), this);
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
