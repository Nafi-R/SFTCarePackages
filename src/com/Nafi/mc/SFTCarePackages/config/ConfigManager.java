package com.Nafi.mc.SFTCarePackages.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import com.Nafi.mc.SFTCarePackages.CarePackage;
import com.Nafi.mc.SFTCarePackages.Utils.Utils;
import com.Nafi.mc.SFTCarePackages.handlers.Announcer;
import com.Nafi.mc.SFTCarePackages.handlers.Locations;
import com.Nafi.mc.SFTCarePackages.handlers.Packages;

public class ConfigManager extends Utils {
	
	CarePackage pl;
	
	public ConfigManager(CarePackage paramPlugin)
	{
		this.pl = paramPlugin;
	}

	public void loadConfig()
	{
		FileConfiguration config = pl.getConfig();
		pl.saveDefaultConfig();

		for(String k : config.getConfigurationSection("CarePackages").getKeys(false))
		{
			Set<ItemStack> items = new HashSet<ItemStack>();
			for(String  i: config.getStringList("CarePackages." + k))
			{
				items.add(StringToItemStack(i));
			}
			Packages.setPackage(k, items);
		}
		
		List<String> Announcements = new ArrayList<String>();
		for(String a: config.getStringList("SpawnAnnouncementMessages"))
		{
			Announcements.add(a);
		}
		Announcer.setAnnounceMessages(Announcements);
		
		Locations.setWorld(Bukkit.getWorld(config.getString("World")));
		
		
		
	}
	
	
	
	
	
	
}
