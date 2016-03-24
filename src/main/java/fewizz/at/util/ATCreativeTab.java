package fewizz.at.util;

import fewizz.at.init.ATBlocks;
import fewizz.at.init.ATItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ATCreativeTab extends CreativeTabs {

	public ATCreativeTab() {
		super("AT");
	}

	@Override
	public Item getTabIconItem() {
		return ATItems.teleporter;
	}

}
