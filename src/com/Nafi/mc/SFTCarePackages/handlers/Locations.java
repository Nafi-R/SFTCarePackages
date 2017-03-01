package com.Nafi.mc.SFTCarePackages.handlers;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.massivecraft.factions.listeners.FactionsBlockListener;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.wimbli.WorldBorder.BorderData;
import com.wimbli.WorldBorder.Config;

public class Locations implements Listener{

	
	private static World world;
	
	private static Location location;
	
	private static BorderData getBorder()
	{
	   return (BorderData)Config.getBorders().get(world.getName());
	}
	
	private static BorderData getBorder(World w)
	{
	   return (BorderData)Config.getBorders().get(w.getName());
	}
	
	
	public Locations(Location l)
	{
		location = l;
	}
	
	public Locations(){}
	

	public boolean isFactionClaim()
	{
		return isFactionClaim(location);
	}
	
	public static boolean isFactionClaim(Location paramLoc)
	{
		for(Player p : Bukkit.getServer().getOnlinePlayers())
		{
			if(!FactionsBlockListener.playerCanBuildDestroyBlock(p, paramLoc, "build", true) && !FactionsBlockListener.playerCanBuildDestroyBlock(p, paramLoc, "destroy", true))
			{
				return true;
			}
		}

		return false;
	}
	
	
	
	public boolean isWithinBorder()
	{
		return getBorder().insideBorder(location);
	}
	
	public static boolean isWithinBorder(Location paramLoc)
	{
		return getBorder().insideBorder(paramLoc);
	}
	
	public boolean isUnprotected()
	{
		return isUnprotected(location);
	}
	
	public static boolean isUnprotected(Location paramLoc)
	{
		WorldGuardPlugin w = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
		
		for(Player p : Bukkit.getServer().getOnlinePlayers())
		{
			if(!w.canBuild(p, paramLoc))
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	public boolean isSuitable()
	{	
		return isSuitable(location);
	}
	
	
	public static boolean isSuitable(Location paramLoc)
	{
		if(paramLoc == null)
		{
			return false;
		}
		
		if(!isFactionClaim(paramLoc) && isWithinBorder(paramLoc) && isUnprotected(paramLoc))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public static Location getRandomLocation(World w)
	{
			
		Random coRand = new Random();
				
		double zLim = getBorder(w).getRadiusX();
		double xLim = getBorder(w).getRadiusZ();
		
		Location loc = null;
				
		
		while(!isSuitable(loc))
		{
			 int x =(int) getBorder().getX();// + coRand.nextInt((int)xLim);
			 
			 if(coRand.nextBoolean())
			 {
				 x += coRand.nextInt((int)xLim);
			 }
			 else
			 {
				 x -= coRand.nextInt((int)xLim);
			 }
			 
			 
			 int z =(int) getBorder().getZ();
			 
			 if(coRand.nextBoolean())
			 {
				 z += coRand.nextInt((int)zLim);
			 }
			 else
			 {
				 z -= coRand.nextInt((int)zLim);
			 }
			 
			loc = new Location(w,x, getHighestY(w,x,z), z);
		}
		
		return loc;
		
	}
	
	
	public Location getRandomLocation()
	{
		return getRandomLocation(location.getWorld());
	}
	
	public static void setWorld(World w)
	{
		world = w;
	}
	
	public static World getWorld()
	{
		return world;
	}
	
	public static int getHighestY(World w, int x, int z)
	{
		int start = 200;
		
		int count = 0;
		
		Material type = w.getBlockAt(x, start ,z).getType();
		while((type == Material.AIR|| type == Material.LONG_GRASS || type == Material.LEAVES || type == Material.LEAVES_2) && count < 200)
		{
			start-=1;
			count++;
			type = w.getBlockAt(x, start ,z).getType();
		}

		return start+1;
		
		
	}
		
}
