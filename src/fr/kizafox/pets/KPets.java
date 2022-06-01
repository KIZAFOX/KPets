package fr.kizafox.pets;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import fr.kizafox.pets.utils.PetManager;
import fr.kizafox.pets.utils.Pets;

public class KPets extends JavaPlugin implements Listener {
	
	private static KPets instance;
	
	private PetManager petManager;
	private PetManager playerPetManager;
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		instance = this;
		this.petManager = new PetManager();
		
		this.getServer().getPluginManager().registerEvents(this, this);
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "KPets is enabled ! " + ChatColor.GRAY + "(" + ChatColor.GREEN + "Version: " + ChatColor.LIGHT_PURPLE + this.getDescription().getVersion() + ChatColor.GRAY + ")");
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		
		this.petManager.unloadCosmetic();
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "KPets is disabled !");
	}
	
	@EventHandler
	public void onLogin(PlayerJoinEvent event) {
		this.playerPetManager = new PetManager(event.getPlayer());
	}
	
	@EventHandler
	public void onLogout(PlayerQuitEvent event) {
		this.petManager.unloadCosmetic();
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		this.playerPetManager.loadCosmetic(Pets.RubiksCube);
		this.playerPetManager.loadCosmetic(Pets.Turtle);
	}
	
	public static KPets get() {
		return instance;
	}
}
