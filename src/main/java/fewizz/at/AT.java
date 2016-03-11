package fewizz.at;

import fewizz.at.client.CloudRenderer;
import fewizz.at.init.ATBiomes;
import fewizz.at.init.ATBlocks;
import fewizz.at.init.ATItems;
import fewizz.at.item.ItemTeleporter;
import fewizz.at.world.ATWorldProvider;
import fewizz.at.world.biome.BiomeGenBubble;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid = AT.MODID, version = AT.VERSION)
public class AT {
	public static final String MODID = "at";
	public static final String VERSION = "0.0.4";

	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		DimensionManager.registerProviderType(3, ATWorldProvider.class, true);
		DimensionManager.registerDimension(3, 3);
		ATBlocks.init();
		ATItems.init();
		ATBiomes.init();
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		CloudRenderer.cloudTickCounter++;
	}
}
