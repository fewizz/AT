package fewizz.at.world.biome;

import java.util.Random;

import fewizz.at.init.ATBlocks;
import fewizz.at.world.gen.feature.WorldGenBigCandyTree;
import fewizz.at.world.gen.feature.WorldGenBubbleTallGrass;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenBubble extends ATBiome {

	public BiomeGenBubble() {
		super(34);
		this.maxHeight = 0.3F;
		this.minHeight = 0.3f;
		this.biomeName = "Bubble";
		this.fillerBlock = ATBlocks.bubbleDirt.getDefaultState();
		this.topBlock = ATBlocks.bubbleGrass.getDefaultState();
		this.theBiomeDecorator.grassPerChunk = 256;
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		super.decorate(worldIn, rand, pos);
		int newX = pos.getX() + rand.nextInt(16);
		int newZ = pos.getZ() + rand.nextInt(16);
		new WorldGenBigCandyTree(rand.nextInt(4)).generate(worldIn, rand, worldIn.getTopSolidOrLiquidBlock(new BlockPos(newX, 0, newZ)));
	}

	@Override
	public WorldGenAbstractTree genBigTreeChance(Random rand) {
		return new WorldGenBigCandyTree(rand.nextInt(4));
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return new WorldGenBubbleTallGrass();
	}

	@Override
	public float getFrequency() {
		return 0.01F;
	}

	@Override
	public boolean hasMountains() {
		return true;
	}

	@Override
	public float getMountainAmplitude() {
		return 25.0F;
	}

	@Override
	public float getMountainFrequency() {
		return 0.007F;
	}

	@Override
	public float getMountainOffset() {
		return -0.4F;
	}

}
