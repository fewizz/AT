package fewizz.at.init;

import fewizz.at.world.gen.feature.BubbleGrassFeature;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ATFetures {
	public static final BubbleGrassFeature BUBBLE_GRASS = new BubbleGrassFeature();
	
	public static void init() {
		Registry.register(Registry.FEATURE, new Identifier("at", "buuble_grass"), BUBBLE_GRASS);
	}
}
