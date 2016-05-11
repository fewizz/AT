package fewizz.at.world.biome;

import java.util.Random;

import fewizz.at.block.BlockBubbleLeaves;
import fewizz.at.init.ATBlocks;
import fewizz.at.world.gen.feature.WorldGenCandyTree;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenCandy extends ATBiome {

	public BiomeGenCandy() {
		super("ATCandy", 0.35F, 0.45F);
		
		this.topBlock = ATBlocks.candyGrass.getDefaultState();
		this.fillerBlock = ATBlocks.bubbleDirt.getDefaultState();
		this.theBiomeDecorator.grassPerChunk = 0;
		this.theBiomeDecorator.treesPerChunk = 1;
	}
	
	@Override
	public int getBiomeColor() {
		return 0xFFFF00;
	}
	
	@Override
	public int getSkyColor() {
		return 0x7F00FF;
	}
	
	@Override
	public int getCloudColor() {
		return 0x7F00FF;
	}
	
	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return 0x079400;
	}
	
	@Override
	public WorldGenAbstractTree genBigTreeChance(Random rand) {
		WorldGenCandyTree wg = new WorldGenCandyTree(ATBlocks.candyLog.getDefaultState(), ATBlocks.bubbleLeaves.getDefaultState().withProperty(BlockBubbleLeaves.DECAYABLE, true).withProperty(BlockBubbleLeaves.CHECK_DECAY,  false));
		wg.TREE_HEIGHT = 12;
		wg.NORMAL_BRANCH_RADIUS = 3;
		wg.BRANCH_RADIUS_VARIATION = 2;
		wg.LEAVES_OFFSET = 6;
		return wg;
	}

}
