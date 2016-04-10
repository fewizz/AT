package fewizz.at.world.biome;

import java.util.Random;

import fewizz.at.init.ATBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeGenGreenHillRiver extends ATBiome {

	public BiomeGenGreenHillRiver() {
		super("ATGreenHillRiver", -0.45F, 0.2F);
		
		this.topBlock = Blocks.SAND.getDefaultState();
		this.fillerBlock = Blocks.SAND.getDefaultState();
	}
	
	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int zCoord, int xCoord, double noiseVal) {
		ATBiomes.greenHill.genTerrainBlocks(worldIn, rand, chunkPrimerIn, zCoord, xCoord, noiseVal);
	}

}
