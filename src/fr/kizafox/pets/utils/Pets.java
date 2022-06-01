package fr.kizafox.pets.utils;

import org.bukkit.ChatColor;

public enum Pets {
	
	RubiksCube("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGY5MjMzYzEyNDdlMDNlOWZkMjc3NDI3MzdlNzllNGNjZWJkMjI1YTliMDU5ZDU5NmQ1Y2QzNGUyNmYyMTY1In19fQ==", "Rubik's Cube", ChatColor.AQUA + "" + ChatColor.BOLD + "", false),
	Turtle("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWFlOTAzNzEzN2I2Mjg4NDJhMjkzODBmODI4YzI2ZWM2NjE4NjY0YzM4MDJjOTk0NWY0YzAxYTk0M2FhNDBlOSJ9fX0=", "Turtle", ChatColor.GREEN + "" + ChatColor.BOLD + "", true);
	
	private String headURL, name, prefixColor;
	private boolean isGround;
	
	Pets(String headURL, String name, String prefixColor, boolean isGround){
		this.headURL = headURL;
		this.name = name;
		this.prefixColor = prefixColor;
		this.isGround = isGround;
	}

	public String getHeadURL() {
		return headURL;
	}
	
	public String getName() {
		return name;
	}

	public String getPrefixColor() {
		return prefixColor;
	}
	
	public boolean isGround(){
		return isGround;
	}
	
	public Double ground(){
		return 1.5D;
	}
}
