package com.treyzania.mc.blockparticles;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;

import com.treyzania.mc.blockparticles.data.WorldData;
import com.treyzania.mc.blockparticles.data.WorldImporter;

public class DataManager {
	
	private WorldImporter loader = new WorldImporter();
	private List<WorldData> database = new ArrayList<>();
	
	public DataManager() {
		
	}
	
	public WorldData loadWorld(World w) {
		
		WorldData d = this.loader.load(w);
		this.database.add(d);
		return d;
		
	}
	
	public void unloadWorld(World w) {
		this.database.remove(this.getWorldData(w.getName()));
	}
	
	public void flushAndUnloadWorld(World w) {
		
		WorldData wd = this.getWorldData(w.getName());
		wd.flush();
		
		this.database.remove(wd);
		
	}
	
	public WorldData getWorldData(String worldName) {
		
		for (WorldData wd : this.database) {
			if (wd.getWorld().getName().equals(worldName)) return wd;
		}
		
		return null;
		
	}
	
}
