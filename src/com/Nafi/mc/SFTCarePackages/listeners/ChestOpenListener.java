package com.Nafi.mc.SFTCarePackages.listeners;

import org.bukkit.Bukkit;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class ChestOpenListener implements Listener{
	
	@EventHandler
	public void onCarePackageOpen(InventoryOpenEvent e)
	{
		if(!(e.getInventory().getHolder() instanceof Chest) && !(e.getInventory().getHolder() instanceof DoubleChest))
		{
			return;
		}
		
		if(!e.getInventory().getTitle().equals("🞧 Care Package 🞧"))
		{
			return;
		}
		
		Bukkit.broadcastMessage("CarePackage found");
		
	}

} 
