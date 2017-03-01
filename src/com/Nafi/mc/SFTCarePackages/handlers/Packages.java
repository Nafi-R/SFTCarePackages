package com.Nafi.mc.SFTCarePackages.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.Nafi.mc.SFTCarePackages.events.PackageSpawnEvent;

public class Packages {

	private static HashMap<String, Set<ItemStack>> packages = new HashMap<String, Set<ItemStack>>();
	private static HashMap<Player, Location> prevCompass = new HashMap<Player, Location>();
	private static Location previousLocation;
	
	
	public static Location getPreviousPackageLocation()
	{
		return previousLocation;
	}
	
	public static void setPreviousPackageLocation(Location loc)
	{
		previousLocation = loc;
	}
	
	
	public static void setPackage(String name, Set<ItemStack> items)
	{
		packages.put(name, items);
	}
	
	public static HashMap<String, Set<ItemStack>> getPackages()
	{
		return packages;
	}
	
	
	public static boolean spawnPackage(Location location, String PackageName)
	{
		PackageSpawnEvent spawn = new PackageSpawnEvent(location, getPackages().get(PackageName));
		
		Bukkit.getPluginManager().callEvent(spawn);
		
		if(spawn.isCancelled())
		{
			return false;
		}
		
		setPreviousPackageLocation(location);
		
		Announcer.announceSpawn(location, PackageName);
		
		location.getWorld().strikeLightning(location);
		
		for(Player p: Bukkit.getServer().getOnlinePlayers())
		{
			p.playSound(location, Sound.ENTITY_LIGHTNING_THUNDER, 0, 0);
			p.playSound(location, Sound.ENTITY_LIGHTNING_IMPACT, 0, 0);
		}
		
		Block chest = location.getBlock();
		chest.setType(Material.CHEST);
		Chest c = (Chest) chest.getState();
		c.setCustomName("🞧 Care Package 🞧");
		placeItems(c.getBlockInventory(), getPackages().get(PackageName));
		for(Player p : Bukkit.getOnlinePlayers())
		{
			prevCompass.put(p,p.getCompassTarget());
			p.setCompassTarget(location);
		}
		return true;
	}

	
	
	private static void placeItems(Inventory i, Set<ItemStack> s)
	{
		Random slotRand = new Random();
		List<Integer> takenSlots = new ArrayList<Integer>();
		
		for(ItemStack item : s)
		{
			int slot = slotRand.nextInt(26);
			while(takenSlots.contains(slot))
			{
				slot = slotRand.nextInt(26);
			}
			i.setItem(slot, item);
		}	
	}
	
	public static String getRandomPackageName()
	{
		Random rand = new Random();
		
		int size = getPackages().keySet().size();
		
		return getPackages().keySet().toArray()[rand.nextInt(size)].toString();
	
	}
	
	
	
	
	
}
