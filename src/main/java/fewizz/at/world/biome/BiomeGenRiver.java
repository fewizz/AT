package fewizz.at.world.biome;

import net.minecraft.init.Blocks;

public class BiomeGenRiver extends ATBiome {

	public BiomeGenRiver() {
		super(36);
		
		this.setBiomeName("ATRiver");
		this.minHeight = -0.6F;
		this.maxHeight = 0.1F;
		this.topBlock = Blocks.sand.getDefaultState();
		this.fillerBlock = Blocks.sand.getDefaultState();
	}

}
