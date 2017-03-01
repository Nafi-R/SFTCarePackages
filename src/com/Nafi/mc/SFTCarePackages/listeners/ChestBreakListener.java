package com.Nafi.mc.SFTCarePackages.listeners;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.Nafi.mc.SFTCarePackages.handlers.Locations;

public class ChestBreakListener implements Listener{
	
	@EventHandler
	public void onPackageBreak(BlockBreakEvent e)
	{
		if(e.getBlock().getType() != Material.CHEST)
		{
			return;
		}
		
		Chest c = (Chest) e.getBlock().getState();
		
		if(!c.getCustomName().equals("🞧 Care Package 🞧"))
		{
			return;
		}
		e.setCancelled(true);
		Locations.getWorld().getBlockAt(e.getBlock().getLocation()).setType(Material.AIR);
	}

}
