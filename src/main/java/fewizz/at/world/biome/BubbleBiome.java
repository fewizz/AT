package fewizz.at.world.biome;

import fewizz.at.init.ATBlocks;
import fewizz.at.init.ATFetures;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.NoiseHeightmapDecoratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class BubbleBiome extends Biome {
	static final TernarySurfaceConfig BUBBLE_CONFIG = new TernarySurfaceConfig(
		ATBlocks.BUBBLE_GRASS_BLOCK.getDefaultState(),
		ATBlocks.BUBBLE_DIRT.getDefaultState(),
		Blocks.GRAVEL.getDefaultState()
	);

	public BubbleBiome() {
		super(new Biome.Settings()
			.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, BUBBLE_CONFIG)
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
		addFeature(
			GenerationStep.Feature.VEGETAL_DECORATION,
			Biome.configureFeature(
				ATFetures.BUBBLE_GRASS,
				new DefaultFeatureConfig(),
				Decorator.NOISE_HEIGHTMAP_DOUBLE, new NoiseHeightmapDecoratorConfig(-0.8D, 5, 10)
			)
		);
	}

}
