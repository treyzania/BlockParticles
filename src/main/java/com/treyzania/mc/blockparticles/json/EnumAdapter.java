package com.treyzania.mc.blockparticles.json;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class EnumAdapter<T extends Enum<T>> extends TypeAdapter<T> {
	
	private Class<T> clazz;
	
	public EnumAdapter(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T read(JsonReader r) {
		
		Logger log = Bukkit.getLogger();
		
		try {
			return (T) this.clazz.getMethod("valueOf", String.class).invoke(null, r.nextString());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | IOException e) {
			
			log.log(Level.SEVERE, "Problem deserializing!", e);
			return null;
			
		}
		
	}

	@Override
	public void write(JsonWriter w, T t) throws IOException {
		w.value(t != null ? t.name() : "null");
	}

}
