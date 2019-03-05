package fewizz.at.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class BubbleBiome extends Biome {

	public BubbleBiome() {
		super(new Biome.Settings()
			.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
			.precipitation(Biome.Precipitation.RAIN)
			.category(Biome.Category.PLAINS)
			.depth(0.2F)
			.scale(0.05F)
			.temperature(0.8F)
			.downfall(0.4F)
			.waterColor(0x0000FF)
			.waterFogColor(0x0000FF)
			.parent((String)null)
		);
	}

}
