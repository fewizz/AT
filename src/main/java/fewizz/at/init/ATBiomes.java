package fewizz.at.init;

import fewizz.at.world.biome.BubbleBiome;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ATBiomes {
	public static final BubbleBiome BUBBLE = new BubbleBiome();
	
	public static void init() {
		Registry.register(Registry.BIOME, new Identifier("at:bubble"), BUBBLE);
	}
}
