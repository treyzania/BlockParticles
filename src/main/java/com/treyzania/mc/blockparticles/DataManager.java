package com.treyzania.mc.blockparticles;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import org.bukkit.World;
import org.bukkit.entity.Player;

import com.treyzania.mc.blockparticles.data.ParticleGroup;
import com.treyzania.mc.blockparticles.data.WorldData;
import com.treyzania.mc.blockparticles.data.WorldImporter;

public class DataManager {
	
	private WorldImporter loader = new WorldImporter();
	private List<WorldData> database = new ArrayList<>();
	
	private WeakHashMap<Player, PlacementSession> sessions = new WeakHashMap<>();
	
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
	
	public WorldData getWorldData(World world) {
		
		for (WorldData wd : this.database) {
			if (wd.getWorld() == world) return wd;
		}
		
		return null;
		
	}
	
	public PlacementSession startSession(World w, Player p) {
		
		PlacementSession ps = new PlacementSession(this.getWorldData(w.getName()), new ParticleGroup());
		
		this.sessions.put(p, ps);
		return ps;
		
	}
	
	public void endSession(Player p) {
		this.sessions.remove(p);
	}
	
}
