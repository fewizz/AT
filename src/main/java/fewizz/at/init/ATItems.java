package fewizz.at.init;

import fewizz.at.item.ItemTeleporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ATItems {
	
	public static final Item TELEPORTER = new ItemTeleporter(new Item.Settings().itemGroup(ItemGroup.MISC));
	public static final BlockItem BUBBLE_GRASS = new BlockItem(ATBlocks.BUBBLE_GRASS, new Item.Settings().itemGroup(ItemGroup.BUILDING_BLOCKS));
	public static final BlockItem BUBBLE_DIRT = new BlockItem(ATBlocks.BUBBLE_DIRT, new Item.Settings().itemGroup(ItemGroup.BUILDING_BLOCKS));
	public static final BlockItem BUBBLE_LEAVES = new BlockItem(ATBlocks.BUBBLE_LEAVES, new Item.Settings().itemGroup(ItemGroup.BUILDING_BLOCKS));
	public static final BlockItem SEDGE = new BlockItem(ATBlocks.SEDGE, new Item.Settings().itemGroup(ItemGroup.BUILDING_BLOCKS));;
	
	public static void init() {
		Registry.register(Registry.ITEM, new Identifier("at", "teleporter"), TELEPORTER);
		
		BUBBLE_GRASS.registerBlockItemMap(Item.BLOCK_ITEM_MAP, BUBBLE_GRASS);
		Registry.register(Registry.ITEM, new Identifier("at", "bubble_grass"), BUBBLE_GRASS);
		
		BUBBLE_DIRT.registerBlockItemMap(Item.BLOCK_ITEM_MAP, BUBBLE_DIRT);
		Registry.register(Registry.ITEM, new Identifier("at", "bubble_dirt"), BUBBLE_DIRT);
		
		BUBBLE_LEAVES.registerBlockItemMap(Item.BLOCK_ITEM_MAP, BUBBLE_LEAVES);
		Registry.register(Registry.ITEM, new Identifier("at", "bubble_leaves"), BUBBLE_LEAVES);
		
		SEDGE.registerBlockItemMap(Item.BLOCK_ITEM_MAP, SEDGE);
		Registry.register(Registry.ITEM, new Identifier("at", "sedge"), SEDGE);
	}
	
}
