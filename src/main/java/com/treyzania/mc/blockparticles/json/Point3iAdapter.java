package com.treyzania.mc.blockparticles.json;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.treyzania.mc.blockparticles.data.Point3i;

public class Point3iAdapter extends TypeAdapter<Point3i> {

	@Override
	public Point3i read(JsonReader r) throws IOException {
		
		String s = r.nextString();
		String[] parts = s.split(",");
		
		return new Point3i(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
		
	}

	@Override
	public void write(JsonWriter w, Point3i p) throws IOException {
		w.value(String.format("%s,%s,%s", p.x, p.y, p.z));
	}

}
