package fewizz.at.world;

import java.lang.reflect.Field;
import java.nio.FloatBuffer;

import fewizz.at.client.CloudRenderer;
import fewizz.at.client.Rend;
import fewizz.at.client.SkyRenderer;
import fewizz.at.client.WeatherRenderer;
import fewizz.at.util.ATConfiguration;
import fewizz.at.world.biome.ATBiome;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

public class ATWorldProvider extends WorldProvider {
	SkyRenderer sr;
	CloudRenderer cr;
	WeatherRenderer wr;

	@Override
	protected void createBiomeProvider() {
		this.biomeProvider = new ATBiomeProvider(worldObj);
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
			this.lightBrightnessTable[i] = (float) i / 16f;
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

	@Override
	public boolean isSurfaceWorld() {
		return true;
	}

	@Override
	public Vec3d getSkyColor(Entity cameraEntity, float partialTicks) {
		BlockPos playerPos = new BlockPos(cameraEntity);
		int len = 6;
		int count = 0;
		float r = 0;
		float g = 0;
		float b = 0;

		for (int x = -len; x <= len; x++) {
			for (int z = -len; z <= len; z++) {

				BiomeGenBase biome = Minecraft.getMinecraft().theWorld.getBiomeGenForCoords(new BlockPos(playerPos.getX() + x, 0, playerPos.getZ() + z));

				if (biome instanceof ATBiome) {
					int color = ((ATBiome) biome).getSkyColor();
					r += (float) ((color & 0xFF0000) >> 16) / 255f;
					g += (float) ((color & 0x00FF00) >> 8) / 255f;
					b += (float) ((color & 0x0000FF) >> 0) / 255f;
				}
				else {
					Vec3d color = super.getCloudColor(partialTicks);
					r += color.xCoord;
					g += color.yCoord;
					b += color.zCoord;
				}

				count++;
			}
		}

		r /= count;
		g /= count;
		b /= count;

		return new Vec3d(r, g, b);

	}

	@Override
	public Vec3d getCloudColor(float partialTicks) {
		BlockPos playerPos = new BlockPos(Minecraft.getMinecraft().thePlayer);
		int len = 6;
		int count = 0;
		float r = 0;
		float g = 0;
		float b = 0;

		for (int x = -len; x <= len; x++) {
			for (int z = -len; z <= len; z++) {

				BiomeGenBase biome = Minecraft.getMinecraft().theWorld.getBiomeGenForCoords(new BlockPos(playerPos.getX() + x, 0, playerPos.getZ() + z));

				if (biome instanceof ATBiome) {
					int color = ((ATBiome) biome).getCloudColor();
					r += (float) ((color & 0xFF0000) >> 16) / 255F;
					g += (float) ((color & 0x00FF00) >> 8) / 255F;
					b += (float) ((color & 0x0000FF) >> 0) / 255F;
				}
				else {
					r += 0.5F;
					g += 1F;
					b += 1F;
				}

				count++;
			}
		}

		r /= count;
		g /= count;
		b /= count;

		return new Vec3d(r, g, b);
	}

	@Override
	public DimensionType getDimensionType() {
		return DimensionType.getById(ATConfiguration.dimID);
	}

}
