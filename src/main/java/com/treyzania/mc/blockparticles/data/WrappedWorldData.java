package com.treyzania.mc.blockparticles.data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.World;

import com.google.gson.Gson;
import com.treyzania.mc.blockparticles.json.IoUtils;

public class WrappedWorldData implements WorldData {
	
	private World world;
	private Map<String, ParticleGroup> groups;
	
	public WrappedWorldData(World w, Map<String, ParticleGroup> groups) {
		
		this.world = w;
		this.groups = groups;
		
		Bukkit.getLogger().warning("WORLD: " + w.getName() + " GROUPS: " + groups);
		
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
		return this.groups;
	}

	@Override
	public void flush() {
		
		Gson g = IoUtils.makeGson();
		
		try {
			
			FileWriter fw = new FileWriter(IoUtils.getFileForWorld(this.world));
			
			fw.write(g.toJson(this.groups));
			fw.close();
			
		} catch (IOException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Could not flush data properly!", e);
		}
		
	}
	
}
