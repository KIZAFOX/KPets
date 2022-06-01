package fr.kizafox.pets.utils;

import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import fr.kizafox.pets.KPets;
import fr.kizafox.pets.utils.head.CustomSkull;

public class PetManager {
	
	private Player player;
	private final BlockFace[] axis = { BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };
	
	public PetManager(Player player) {
		this.player = player;
	}
	
	public PetManager(){}

	private BlockFace yawToFace(float yaw, boolean useSubCardinalDirections) {
        if (useSubCardinalDirections)
            return axis[Math.round(yaw / 45f) & 0x7].getOppositeFace();
 
        return axis[Math.round(yaw / 90f) & 0x3].getOppositeFace();
    }
	
	public void unloadCosmetic(Pets pet) {
		Bukkit.getServer().getWorlds().forEach(worlds -> {
			worlds.getEntities().forEach(entities -> {
				if(entities instanceof ArmorStand){
					if(entities.getName().equalsIgnoreCase(pet.getPrefixColor() + pet.getName())) entities.remove();
				}
			});
		});
	}
	
	public void unloadCosmetic() {
		Stream.of(Pets.values()).forEach(pets -> this.unloadCosmetic(pets));;
	}
	
	public void loadCosmetic(Pets pet) {
		Bukkit.getScheduler().runTaskLater(KPets.get(), ()->{			
			unloadCosmetic(pet);
			
			Location location = new Location(player.getWorld(), 0, 0, 0);
			
			switch (yawToFace(player.getLocation().getYaw(), false)) {
			case NORTH:
				if(pet.isGround()){
					location = new Location(player.getWorld(), player.getLocation().getBlockX() + 1, player.getLocation().getBlockY() - pet.ground(), player.getLocation().getBlockZ());
				}else{
					location = new Location(player.getWorld(), player.getLocation().getBlockX() + 1, player.getLocation().getBlockY(), player.getLocation().getBlockZ());
				}
				break;
			case SOUTH:
				if(pet.isGround()){
					location = new Location(player.getWorld(), player.getLocation().getBlockX() -1, player.getLocation().getBlockY() - pet.ground(), player.getLocation().getBlockZ());
				}else{
					location = new Location(player.getWorld(), player.getLocation().getBlockX() -1, player.getLocation().getBlockY(), player.getLocation().getBlockZ());
				}
				break;
			case WEST:
				if(pet.isGround()){
					location = new Location(player.getWorld(), player.getLocation().getBlockX(), player.getLocation().getBlockY() - pet.ground(), player.getLocation().getBlockZ() - 1);
				}else{
					location = new Location(player.getWorld(), player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ() - 1);
				}
				break;
			case EAST:
				if(pet.isGround()){
					location = new Location(player.getWorld(), player.getLocation().getBlockX(), player.getLocation().getBlockY() - pet.ground(), player.getLocation().getBlockZ() + 1);
				}else{
					location = new Location(player.getWorld(), player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ() + 1);
				}
				break;
			default:
				break;
			}
			
			ArmorStand rubicude = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
			rubicude.setVisible(false);
			rubicude.setGravity(false);
			rubicude.setHelmet(CustomSkull.customHead(pet.getHeadURL()));
			rubicude.setCustomName(pet.getPrefixColor() + pet.getName());
			rubicude.setCustomNameVisible(true);
		}, (long) (0.9 * 20));
	}
	
	public void loadCosmetic() {
		Stream.of(Pets.values()).forEach(pets -> this.loadCosmetic(pets));
	}
}
