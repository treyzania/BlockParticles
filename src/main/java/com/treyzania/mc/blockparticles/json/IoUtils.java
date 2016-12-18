package com.treyzania.mc.blockparticles.json;

import java.io.File;

import org.bukkit.Particle;
import org.bukkit.World;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.treyzania.mc.blockparticles.data.Point3i;

public class IoUtils {
	
	private static final String DATA_FILENAME = "blockparticles.json";
	
	public static Gson makeGson() {
		
		GsonBuilder gb = new GsonBuilder();
		
		// Config.
		gb.setPrettyPrinting();
		gb.serializeNulls();
		
		// Adapters
		gb.registerTypeAdapter(Point3i.class, new Point3iAdapter());
		gb.registerTypeAdapter(Particle.class, new EnumAdapter<Particle>(Particle.class));
		
		return gb.create();
		
	}
	
	public static File getFileForWorld(World w) {
		return new File(w.getWorldFolder(), DATA_FILENAME);
	}
	
}
