package com.treyzania.mc.blockparticles;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.treyzania.mc.blockparticles.cmd.ComandStartSession;
import com.treyzania.mc.blockparticles.cmd.CommandEndSession;
import com.treyzania.mc.blockparticles.cmd.CommandReload;

public class BlockParticles extends JavaPlugin {
	
	private ConfigSource config;
	
	private DataManager dataManager;
	
	@Override
	public void onLoad() {
		
		this.saveDefaultConfig();
		if (this.config == null) this.config = new YmlConfigSource(this.getConfig());
		
	}
	
	@Override
	public void onEnable() {
		
		// Utils.
		this.dataManager = new DataManager();
		
		// Listeners.
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new WorldLoadStateListener(this.dataManager), this);
		pm.registerEvents(new GroupActivationListener(this.dataManager, this.config.getUpdateDistance()), this);
		pm.registerEvents(new BlockPlacementListener(this.dataManager), this);
		
		// Commands.
		this.getCommand("bpcreate").setExecutor(new ComandStartSession(this.dataManager, this.config));
		this.getCommand("bpstop").setExecutor(new CommandEndSession(this.dataManager));
		this.getCommand("bpreload").setExecutor(new CommandReload(this));
		
		// Background thingy.
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new ParticleSpawnProcedure(this.dataManager, this.getServer()), 0, 1);
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
