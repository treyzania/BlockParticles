package com.treyzania.mc.blockparticles;

import org.bukkit.plugin.java.JavaPlugin;

public class BlockParticles extends JavaPlugin {
	
	private ConfigSource config;
	
	@Override
	public void onLoad() {
		
		this.saveDefaultConfig();
		if (this.config == null) this.config = new YmlConfigSource(this.getConfig());
		
	}
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
