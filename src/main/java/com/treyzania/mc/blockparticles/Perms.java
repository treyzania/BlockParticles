package com.treyzania.mc.blockparticles;

import org.bukkit.Particle;

public class Perms {
	
	public static final String CMD_CREATE = "blockparticles.cmd.create";
	public static final String CMD_SET_TYPE = "blockparticles.cmd.settype";
	public static final String CMD_SET_DENSITY = "blockparticles.cmd.setdensity";
	public static final String CMD_SET_RANGE = "blockparticles.cmd.setrange";
	public static final String CMD_RELOAD = "blockparticles.cmd.relaod";
	
	public static String TYPE_PREFIX(Particle p) {
		return "blockparticles.type." + p.name().toLowerCase().replace("_", "");
	}
	
}
