package fewizz.at.world.biome;

import java.util.Random;

import fewizz.at.world.SimplexNoise;
import fewizz.at.world.gen.feature.WorldGenCandyTree;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;

public class BiomeGenGreenHill extends ATBiome {
	public static SimplexNoise noise = new SimplexNoise();

	public BiomeGenGreenHill() {
		super("ATGreenHill", 0.25f, 0.6f);
		
		this.fillerBlock = Blocks.DIRT.getDefaultState();
		this.topBlock = Blocks.GRASS.getDefaultState();
		this.theBiomeDecorator.grassPerChunk = 32;
		this.theBiomeDecorator.treesPerChunk = 2;
	}
	
	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return 0x079400;
	}
	
	@Override
	public int getBiomeColor() {
		return 0x00FF00;
	}
	
	@Override
	public float getFrequency() {
		return 0.06F;
	}
	
	@Override
	public int getSkyColor() {
		return 0x008227;
	}
	
	@Override
	public WorldGenAbstractTree genBigTreeChance(Random rand) {
		return new WorldGenCandyTree(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState().withProperty(Blocks.LEAVES.CHECK_DECAY, false));
	}

	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int zCoord, int xCoord, double noiseVal) {
		int z = zCoord & 15;
		int x = xCoord & 15;

		/** Hilling **/
		IBlockState[] states = new IBlockState[256];

		int offset = ((int) ((((float) noise.noise((float)xCoord / 64f, (float)zCoord / 64f) + 1) / 2f) * 6)) * 3;

		for (int y = 0; y < 256; y++) {
			states[y] = chunkPrimerIn.getBlockState(x, y, z);
		}

		for (int y = 0; y < 256; y++) {
			if (y + offset < 256) {
				IBlockState state = states[y];
				if(state.getBlock() == Blocks.WATER) {
					state = Blocks.FLOWING_WATER.getDefaultState();
				}
				chunkPrimerIn.setBlockState(x, y + offset, z, state);
			}
		}

		for (int y = 0; y < offset; y++) {
			chunkPrimerIn.setBlockState(x, y, z, Blocks.STONE.getDefaultState());
		}
		/*************/

		super.genTerrainBlocks(worldIn, rand, chunkPrimerIn, zCoord, xCoord, noiseVal);
	}

}
