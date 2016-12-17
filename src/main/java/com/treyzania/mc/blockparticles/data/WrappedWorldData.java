package com.treyzania.mc.blockparticles.data;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.World;

public class WrappedWorldData implements WorldData {
	
	private World world;
	private Map<String, ParticleGroup> groups;
	
	public WrappedWorldData(World w, Map<String, ParticleGroup> groups) {
		
		this.world = w;
		this.groups = groups;
		
	}
	
	@Override
	public World getWorld() {
		return this.world;
	}
	
	@Override
	public void addGroup(String name, ParticleGroup pg) {
		
		pg.setFlushCallback(this::flush);
		this.groups.put(name, pg);
		
	}
	
	@Override
	public ParticleGroup getGroup(String name) {
		return this.groups.get(name);
	}
	
	@Override
	public Map<String, ParticleGroup> getGroups() {
		return new HashMap<>(this.groups);
	}

	@Override
	public void flush() {
		
		// TODO
		
	}
	
}
