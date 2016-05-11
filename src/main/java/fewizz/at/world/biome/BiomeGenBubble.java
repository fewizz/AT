package fewizz.at.world.biome;

import java.util.Random;

import fewizz.at.init.ATBlocks;
import fewizz.at.util.ATConfiguration;
import fewizz.at.util.IHasName;
import fewizz.at.world.gen.feature.WorldGenBubbleTallGrass;
import fewizz.at.world.gen.feature.WorldGenCandyTree;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.config.Configuration;

public class BiomeGenBubble extends ATBiome {

	public BiomeGenBubble() {
		super("ATBubble", 0.35F, 0.45F);

		this.fillerBlock = ATBlocks.bubbleDirt.getDefaultState();
		this.topBlock = ATBlocks.bubbleGrass.getDefaultState();
		this.theBiomeDecorator.grassPerChunk = 32;
		this.theBiomeDecorator.treesPerChunk = 2;
	}
	
	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return 0x079400;
	}
	
	@Override
	public int getBiomeColor() {
		return 0x5A009D;
	}

	@Override
	public WorldGenAbstractTree genBigTreeChance(Random rand) {
		return new WorldGenCandyTree();
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return new WorldGenBubbleTallGrass();
	}

	@Override
	public float getFrequency() {
		return 0.015F;
	}

	@Override
	public boolean hasMountains() {
		return true;
	}

	@Override
	public float getMountainAmplitude() {
		return 20.0F;
	}

	@Override
	public float getMountainFrequency() {
		return 0.001F;
	}

	@Override
	public float getMountainOffset() {
		return -0.2F;
	}

}
