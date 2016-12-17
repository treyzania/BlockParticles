package com.treyzania.mc.blockparticles.data;

import java.util.Map;

import org.bukkit.World;

public interface WorldData {
	
	World getWorld();
	
	void addGroup(String name, ParticleGroup pg);
	
	ParticleGroup getGroup(String name);
	Map<String, ParticleGroup> getGroups();
	
	void flush();
	
}
