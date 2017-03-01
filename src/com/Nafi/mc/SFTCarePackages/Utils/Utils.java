package com.Nafi.mc.SFTCarePackages.Utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class Utils {

	protected static ItemStack StringToItemStack(String s)
	{
		//id;damage;amount;enchant1,level;enchant2,level;enchant3,level...etc
		String[] itemComp = s.split(";");
		
		short dur = 0;
		int amount = 1;
		Map<Enchantment, Integer> e = new HashMap<Enchantment,Integer>();
		
		int id = Integer.parseInt(itemComp[0]);
		
		if(itemComp.length >= 1)
		{
			 dur = Short.parseShort(itemComp[1]);
		}
		
		if(itemComp.length >= 2)
		{
			 amount = Short.parseShort(itemComp[2]);
		}
		
		for(int i = 3; i < itemComp.length; i++)
		{
			e.put(Enchantment.getByName(itemComp[i].split(",")[0].toUpperCase()), Integer.parseInt(itemComp[i].split(",")[1]));
		}
		
		
		@SuppressWarnings("deprecation")
		ItemStack i = new ItemStack(id, dur);
		i.setAmount(amount);
		i.addUnsafeEnchantments(e);
		
		return i;
	}
	
	
	
}
