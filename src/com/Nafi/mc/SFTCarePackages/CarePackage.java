package com.Nafi.mc.SFTCarePackages;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.Nafi.mc.SFTCarePackages.commands.SpawnPackage;
import com.Nafi.mc.SFTCarePackages.config.ConfigManager;
import com.Nafi.mc.SFTCarePackages.listeners.ChestBreakListener;
import com.Nafi.mc.SFTCarePackages.listeners.ChestOpenListener;

public class CarePackage extends JavaPlugin{
	
	@Override
	public void onEnable()
	{
		(new ConfigManager(this)).loadConfig();
		this.getCommand("spawnpackage").setExecutor(new SpawnPackage());
		Bukkit.getPluginManager().registerEvents(new ChestOpenListener(), this);
		Bukkit.getPluginManager().registerEvents(new ChestBreakListener(), this);
	}
	
	@Override
	public void onDisable()
	{}

}
