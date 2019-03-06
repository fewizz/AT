package fewizz.at;

import fewizz.at.init.ATBiomes;
import fewizz.at.init.ATBlocks;
import fewizz.at.init.ATFetures;
import fewizz.at.init.ATItems;
import fewizz.at.world.dimension.ATDimensionType;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AT implements ModInitializer {
	
	@Override
	public void onInitialize() {
		ATBlocks.init();
		ATItems.init();
		ATFetures.init();
		ATBiomes.init();
		Registry.register(Registry.DIMENSION, new Identifier("at:at"), ATDimensionType.INSTANCE);
	}
}
