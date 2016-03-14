package fewizz.at.world;

import java.lang.reflect.Field;

import fewizz.at.client.CloudRenderer;
import fewizz.at.client.SkyRenderer;
import fewizz.at.client.WeatherRenderer;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

public class ATWorldProvider extends WorldProvider {
	SkyRenderer sr;
	CloudRenderer cr;
	WeatherRenderer wr;

	@Override
	protected void registerWorldChunkManager() {
		this.worldChunkMgr = new ATWorldChunkManager(this.worldObj);
	}

	@Override
	public IChunkProvider createChunkGenerator() {
		return new ATChunkProvider(worldObj, worldObj.getSeed(), true);
	}

	@Override
	public String getDimensionName() {
		return "AT";
	}

	@Override
	public String getInternalNameSuffix() {
		return "1";
	}

	@Override
	protected void generateLightBrightnessTable() {
		for (int i = 0; i < 16; i++) {
			this.lightBrightnessTable[i] = ((float)i / 16f) + 0.2f;
		}
	}

	@Override
	public IRenderHandler getSkyRenderer() {
		if (sr == null) {
			sr = new SkyRenderer();
		}

		return sr;

	}

	@Override
	public IRenderHandler getCloudRenderer() {
		if (cr == null) {
			cr = new CloudRenderer();
		}

		return cr;
	}

	@Override
	public float getCloudHeight() {
		return 100;
	}

	// @Override
	// public IRenderHandler getWeatherRenderer() {
	// if(wr == null){
	// wr = new WeatherRenderer();
	// }
	//
	// return wr;
	// }

	@Override
	public boolean isSurfaceWorld() {
		return true;
	}

}
