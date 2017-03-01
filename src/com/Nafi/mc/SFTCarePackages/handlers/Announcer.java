package com.Nafi.mc.SFTCarePackages.handlers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Announcer {

	private static List<String> AnnounceSpawnMessages = new ArrayList<String>();
	
	public static void setAnnounceMessages(List<String> messages)
	{
		for(String msg : messages)
		{
			AnnounceSpawnMessages.add(ChatColor.translateAlternateColorCodes('&', msg));
		}
	}
	
	

	
	
	
	public static void announceSpawn(Location packageLocation, String packageName)
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			for(String msg : AnnounceSpawnMessages)
			{
				p.sendMessage(runAnnounceReplace(p, msg, packageLocation, packageName));
			}
		}		
	}
	
	private static String runAnnounceReplace(Player p, String msg, Location packageLocation, String packageName)
	{
		String relation = "";
		
		int diffX = p.getLocation().getBlockX() - packageLocation.getBlockX();
		int diffZ = p.getLocation().getBlockZ() - packageLocation.getBlockZ();
		
		if(diffX  > 0 && diffZ > 0 && diffX > 100){relation = "North-West";}
		else 
		if(diffX  < 0 && diffZ > 0 && diffX < -100){relation = "North-East";}
		else 
		if(diffX  > 0 && diffZ < 0 && diffX > 100){relation = "South-West";}
		else 
		if(diffX  < 0 && diffZ < 0 && diffX < -100 ){relation = "South-East";}
		else
		if(diffZ > 0 && diffX < 100 && diffX > -100){relation = "North";}
		else
		if(diffZ < 0 && diffX < 100 && diffX > -100){relation = "South";}
		else
		if(diffX > 0 && diffZ < 100 && diffZ > -100){relation = "West";}
		else
		if(diffX < 0 && diffZ < 100 && diffZ > -100){relation = "East";}
		
		msg = msg.replace("{distance}", ((int)p.getLocation().distance(packageLocation)) + "");
		msg = msg.replace("{relation}", relation);
		msg = msg.replace("{x}", packageLocation.getBlockX() + "");
		msg = msg.replace("{y}", packageLocation.getBlockY() + "");
		msg = msg.replace("{z}", packageLocation.getBlockZ() + "");
		msg = msg.replace("{packagename}", "packageName");
		msg = msg.replace("{world}", packageLocation.getWorld().getName());
		
		return msg;
	}
	
	
	
}
