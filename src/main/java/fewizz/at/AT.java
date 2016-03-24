package fewizz.at;

import java.io.File;

import fewizz.at.client.CloudRenderer;
import fewizz.at.init.ATBiomes;
import fewizz.at.init.ATBlocks;
import fewizz.at.init.ATItems;
import fewizz.at.item.ItemTeleporter;
import fewizz.at.proxy.Proxy;
import fewizz.at.util.ATConfiguration;
import fewizz.at.util.ATCreativeTab;
import fewizz.at.util.ATEventHandler;
import fewizz.at.world.ATWorldProvider;
import fewizz.at.world.biome.BiomeGenBubble;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = AT.MODID, version = AT.VERSION)
public class AT {
	public static final String MODID = "at";
	public static final String VERSION = "0.0.8";
	
	@SidedProxy(
			clientSide = "fewizz.at.proxy.ClientProxy",
			serverSide = "fewizz.at.proxy.Proxy")
	public static Proxy proxy;
	
	public static CreativeTabs tab = new ATCreativeTab();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ATConfiguration.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/AT.cfg"));
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ATEventHandler());
		DimensionManager.registerProviderType(ATConfiguration.dimID, ATWorldProvider.class, true);
		DimensionManager.registerDimension(ATConfiguration.dimID, ATConfiguration.dimID);
		ATBlocks.init();
		ATItems.init();
		ATBiomes.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.registerItemVariants();
		proxy.registerRenders();
	}
}
