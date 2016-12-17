package com.treyzania.mc.blockparticles.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.World;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.treyzania.mc.blockparticles.json.IoUtils;

public class WorldImporter {
	
	public WorldImporter() {
		
	}
	
	public WrappedWorldData load(World w) {
		
		File f = IoUtils.getFileForWorld(w);
		Logger log = Bukkit.getLogger();
		
		if (!f.exists()) {
			
			log.info("Creating WorldData for " + w.getName() + ": " + f.getAbsolutePath());
			
			try {
				
				f.getParentFile().mkdirs();
				f.createNewFile();
				
			} catch (IOException e) {
				log.log(Level.SEVERE, "Could not create new world data save file.", e);
			}
			
			return new WrappedWorldData(w, new HashMap<>());
			
		}
		
		try {
			
			Gson g = IoUtils.makeGson();
			Type t = new TypeToken<Map<String, ParticleGroup>>(){}.getType();
			Map<String, ParticleGroup> data = g.fromJson(new FileReader(f), t);
			
			return new WrappedWorldData(w, data);
			
		} catch (JsonIOException e) {
			log.log(Level.SEVERE, "Other problem parsing JSON.", e);
		} catch (JsonSyntaxException e) {
			log.log(Level.SEVERE, "Problem parsing JSON.", e);
		} catch (FileNotFoundException e) {
			
			// Shouldn't be possible
			log.warning("The impossible happened.");
			
		}
		
		// Ah fuck.
		return new WrappedWorldData(w, new HashMap<>());
		
	}
	
}
