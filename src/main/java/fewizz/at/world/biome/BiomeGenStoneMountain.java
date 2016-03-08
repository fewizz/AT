package fewizz.at.world.biome;

import net.minecraft.init.Blocks;

public class BiomeGenStoneMountain extends ATBiome {

	public BiomeGenStoneMountain() {
		super(35);
		
		this.topBlock = Blocks.sand.getDefaultState();
		this.fillerBlock = Blocks.sandstone.getDefaultState();
	}

}
