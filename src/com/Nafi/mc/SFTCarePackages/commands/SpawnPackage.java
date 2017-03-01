package com.Nafi.mc.SFTCarePackages.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Nafi.mc.SFTCarePackages.handlers.Locations;
import com.Nafi.mc.SFTCarePackages.handlers.Packages;

public class SpawnPackage implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(!(sender instanceof Player))
		{
			sender.sendMessage(ChatColor.RED + "You must be a player to perform this command");
			return true;
		}
		
		Player player = (Player) sender;
		
		if(!player.hasPermission("sftcarepackage.spawn"))
		{
			sender.sendMessage(ChatColor.RED + "You do not have permission to perform this command");
			return true;
		}
		
		Packages.spawnPackage(Locations.getRandomLocation(player.getWorld()), Packages.getRandomPackageName());
		
		
		return false;
	}

}
