package com.Nafi.mc.SFTCarePackages.schedulers;

import com.Nafi.mc.SFTCarePackages.handlers.Locations;
import com.Nafi.mc.SFTCarePackages.handlers.Packages;

public class PackageSpawnScheduler implements Runnable{

	@Override
	public void run() 
	{
		Packages.spawnPackage(Locations.getRandomLocation(Locations.getWorld()), Packages.getRandomPackageName());
	}

}
