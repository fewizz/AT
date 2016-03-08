package fewizz.at;

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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = AT.MODID, version = AT.VERSION)
public class AT
{
    public static final String MODID = "at";
    public static final String VERSION = "0.0.1";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	DimensionManager.registerProviderType(3, ATWorldProvider.class, true);
    	DimensionManager.registerDimension(3, 3);
    	ATBlocks.init();
    	ATItems.init();
    	ATBiomes.init();
    }
}
