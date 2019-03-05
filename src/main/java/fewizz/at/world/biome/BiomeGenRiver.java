package fewizz.at.world.biome;

import java.util.Random;

import fewizz.at.init.ATBlocks;
import fewizz.at.util.ATConfiguration;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeGenRiver extends ATBiome {

	public BiomeGenRiver() {
		super("ATRiver", -0.45F, 0.2F);

		this.topBlock = Blocks.SAND.getDefaultState();
		this.fillerBlock = Blocks.SAND.getDefaultState();
	}
	
	@Override
	public int getBiomeColor() {
		return 0x0000FF;
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int zGlobal, int xGlobal, double noiseVal) {
		super.genTerrainBlocks(worldIn, rand, chunkPrimerIn, zGlobal, xGlobal, noiseVal);
		int y = -1;
		int x = xGlobal & 15;
		int z = zGlobal & 15;

		IBlockState statePlusOne = chunkPrimerIn.getBlockState(x, worldIn.getSeaLevel() + 1, z);
		IBlockState statePlusTwo = chunkPrimerIn.getBlockState(x, worldIn.getSeaLevel() + 2, z);
		IBlockState state = chunkPrimerIn.getBlockState(x, worldIn.getSeaLevel(), z);

		if (statePlusOne.getBlock().getMaterial(statePlusOne) == Material.AIR && state.getBlock().getMaterial(state) != Material.WATER) {
			y = worldIn.getSeaLevel();
		}
		else if (statePlusTwo.getBlock().getMaterial(statePlusTwo) == Material.AIR && state.getBlock().getMaterial(state) != Material.WATER) {
			y = worldIn.getSeaLevel() + 1;
		}

		if (y != -1) {
			chunkPrimerIn.setBlockState(x, y + 1, z, ATBlocks.SEDGE.getStateFromMeta(0));
			chunkPrimerIn.setBlockState(x, y + 2, z, ATBlocks.SEDGE.getStateFromMeta(1));
			chunkPrimerIn.setBlockState(x, y + 3, z, ATBlocks.SEDGE.getStateFromMeta(2));
		}

	}

}
