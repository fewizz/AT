package fewizz.at.init;

import fewizz.at.block.BlockSedge;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ATBlocks {

	public static final Block BUBBLE_GRASS = new Block(FabricBlockSettings.copy(Blocks.DIRT).build());
	public static final Block BUBBLE_DIRT = new Block(FabricBlockSettings.copy(Blocks.DIRT).build());
	public static final Block BUBBLE_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.BIRCH_LEAVES).build());
	public static Block candyGrass;
	public static Block candyLeaves;
	public static Block candyLog;
	public static final Block SEDGE = new BlockSedge(FabricBlockSettings.of(Material.PLANT).build());

	public static void init() {
		Registry.register(
			Registry.BLOCK,
			new Identifier("at", "bubble_grass"),
			BUBBLE_GRASS
		);
		Registry.register(
			Registry.BLOCK,
			new Identifier("at", "bubble_dirt"),
			BUBBLE_DIRT
		);
		Registry.register(
			Registry.BLOCK,
			new Identifier("at", "bubble_leaves"),
			BUBBLE_LEAVES
		);
		Registry.register(
			Registry.BLOCK,
			new Identifier("at", "sedge"),
			SEDGE
		);
	}

}
