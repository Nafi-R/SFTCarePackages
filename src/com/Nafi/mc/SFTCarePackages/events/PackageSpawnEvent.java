package com.Nafi.mc.SFTCarePackages.events;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PackageSpawnEvent extends Event implements Cancellable{
	
	private boolean isCancelled;
	private Set<ItemStack> getItems;
	private Location getLocation;
	private static HandlerList handlers = new HandlerList();

	
	public PackageSpawnEvent(Location location, Set<ItemStack> items)
	{
		this.getLocation = location;
		this.getItems = items;
	}
	
	
	
	public Set<ItemStack> getItems()
	{
		return getItems;
	}
	
	public Location getLocation()
	{
		return getLocation;
	}
	
	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean arg0) {
		isCancelled = arg0;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	
	
	
	
}
