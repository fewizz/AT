package fewizz.at.world;

import java.lang.reflect.Field;

import fewizz.at.client.CloudRenderer;
import fewizz.at.client.SkyRenderer;
import fewizz.at.client.WeatherRenderer;
import fewizz.at.util.ATConfiguration;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

public class ATWorldProvider extends WorldProvider {
	SkyRenderer sr;
	CloudRenderer cr;
	WeatherRenderer wr;

	@Override
	protected void registerWorldChunkManager() {
		this.worldChunkMgr = new ATWorldChunkManager(worldObj);
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ATChunkProvider(worldObj, worldObj.getSeed(), true);
	}

	@Override
	public boolean canCoordinateBeSpawn(int x, int z) {
		return true;
	}
	@Override
	protected void generateLightBrightnessTable() {
		for (int i = 0; i < 16; i++) {
			this.lightBrightnessTable[i] = (float)i / 16f;
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

	@Override
	public DimensionType getDimensionType() {
		return DimensionType.getById(ATConfiguration.dimID);
	}

}
