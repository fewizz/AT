package fewizz.at.world.biome;

import java.util.Random;

import fewizz.at.init.ATBlocks;
import fewizz.at.util.ATConfiguration;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeGenRiver extends ATBiome {

	public BiomeGenRiver() {
		super("ATRiver", -0.5F, 0.2F);

		//this.biomeName = getName();
		//this.minHeight = -0.5F;
		//this.maxHeight = 0.2F;
		//this.topBlock = Blocks.sand.getDefaultState();
		this.fillerBlock = Blocks.sand.getDefaultState();
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int zGlobal, int xGlobal, double noiseVal) {
		super.genTerrainBlocks(worldIn, rand, chunkPrimerIn, zGlobal, xGlobal, noiseVal);
		int y = -1;
		int x = xGlobal & 15;
		int z = zGlobal & 15;

		//if (chunkPrimerIn.getBlockState(x, worldIn.getSeaLevel() + 1, z).getBlock().getMaterial() == Material.air && chunkPrimerIn.getBlockState(x, worldIn.getSeaLevel(), z).getBlock().getMaterial() != Material.water) {
		//	y = worldIn.getSeaLevel();
		//}
		//else if (chunkPrimerIn.getBlockState(x, worldIn.getSeaLevel() + 2, z).getBlock().getMaterial() == Material.air && chunkPrimerIn.getBlockState(x, worldIn.getSeaLevel(), z).getBlock().getMaterial() != Material.water) {
		//	y = worldIn.getSeaLevel() + 1;
		//}

		//if (y != -1) {
		//	chunkPrimerIn.setBlockState(x, y + 1, z, ATBlocks.sedge.getStateFromMeta(0));
		//	chunkPrimerIn.setBlockState(x, y + 2, z, ATBlocks.sedge.getStateFromMeta(1));
		//	chunkPrimerIn.setBlockState(x, y + 3, z, ATBlocks.sedge.getStateFromMeta(2));
		//}
		
	}

}
