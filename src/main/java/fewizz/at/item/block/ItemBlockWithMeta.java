package fewizz.at.item.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class ItemBlockWithMeta extends ItemBlock {

	public ItemBlockWithMeta(Block block) {
		super(block);
	}
	
	@Override
	public int getMetadata(int damage) {
		return damage;
	}

}
