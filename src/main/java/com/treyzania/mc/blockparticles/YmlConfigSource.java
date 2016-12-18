package com.treyzania.mc.blockparticles;

import org.bukkit.configuration.file.FileConfiguration;

public class YmlConfigSource implements ConfigSource {
	
	private int defaultActivationRange = 0;
	private int maxGroupSize = 0;
	private double updateDistance = 1F;
	
	public YmlConfigSource(FileConfiguration fc) {
		
		this.defaultActivationRange = fc.getInt("default_activation_radius");
		this.maxGroupSize = fc.getInt("max_group_size");
		this.updateDistance = fc.getDouble("update_distance");
		
	}
	
	@Override
	public int getDefaultActivationRange() {
		return this.defaultActivationRange;
	}
	
	@Override
	public int getMaxGroupSize() {
		return this.maxGroupSize;
	}

	@Override
	public float getUpdateDistance() {
		return (float) this.updateDistance;
	}
	
}
