package fewizz.at.client;

import org.lwjgl.opengl.GL11;

import fewizz.at.world.biome.ATBiome;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.IRenderHandler;

public class CloudRenderer extends IRenderHandler {
	public static int cloudTickCounter = 0;
	public static Vec3d color = new Vec3d(0, 0, 0);
	public static Vec3d prevColor = new Vec3d(0, 0, 0);
	public static final ResourceLocation locationCloudsPng = new ResourceLocation("textures/environment/clouds.png");

	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		GlStateManager.disableCull();
		GlStateManager.setFogEnd(((mc.gameSettings.renderDistanceChunks * 16) - 70) * 3);
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer worldrenderer = tessellator.getBuffer();
		final double tick = this.cloudTickCounter + (partialTicks * 2);
		final double posXor = (mc.getRenderViewEntity().prevPosX + (mc.getRenderViewEntity().posX - mc.getRenderViewEntity().prevPosX) * (double) partialTicks + (tick * 0.029999999329447746D)) / 12.0D;
		final double posZor = (mc.getRenderViewEntity().prevPosZ + (mc.getRenderViewEntity().posZ - mc.getRenderViewEntity().prevPosZ) * (double) partialTicks) / 12.0D;
		float height = (float) (mc.theWorld.provider.getCloudHeight() - (mc.getRenderViewEntity().prevPosY + (mc.getRenderViewEntity().posY - mc.getRenderViewEntity().prevPosY) * partialTicks));
		mc.renderEngine.bindTexture(locationCloudsPng);
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

		//Vec3d cloudColor = world.getCloudColour(partialTicks);

		float r1 = (float) (prevColor.xCoord + ((color.xCoord - prevColor.xCoord) * partialTicks));//(float) cloudColor.xCoord;
		float g1 = (float) (prevColor.yCoord + ((color.yCoord - prevColor.yCoord) * partialTicks));//(float) cloudColor.yCoord;
		float b1 = (float) (prevColor.zCoord + ((color.zCoord - prevColor.zCoord) * partialTicks));//(float) cloudColor.zCoord;

		float r2 = r1 * 0.9F;
		float g2 = g1 * 0.9F;
		float b2 = b1 * 0.9F;

		float r3 = r1 * 0.7F;
		float g3 = g1 * 0.7F;
		float b3 = b1 * 0.7F;

		float r4 = r1 * 0.87F;
		float g4 = g1 * 0.87F;
		float b4 = b1 * 0.87F;

		GlStateManager.scale(12.0F, 1.0F, 12.0F);
		GlStateManager.colorMask(true, true, true, true);

		float end = GL11.glGetFloat(GL11.GL_FOG_END);
		GlStateManager.setFogEnd(end * 3F);

		worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);

		for (int level = 1; level < 4; level++) {
			double posX = posXor + level * 5;
			double posZ = posZor + level * 5;

			posX = posX - (double) (MathHelper.floor_double(posX / 2048.0D) * 2048);
			posZ = posZ - (double) (MathHelper.floor_double(posZ / 2048.0D) * 2048);

			float texU = (float) MathHelper.floor_double(posX) * 0.00390625F;
			float texV = (float) MathHelper.floor_double(posZ) * 0.00390625F;

			float f19 = (float) (posX - (double) MathHelper.floor_double(posX));
			float f20 = (float) (posZ - (double) MathHelper.floor_double(posZ));

			for (int clX = -3; clX <= 4; ++clX) {
				for (int clZ = -3; clZ <= 4; ++clZ) {
					float x1 = (float) (clX * 8);
					float z1 = (float) (clZ * 8);
					float x = x1 - f19;
					float z = z1 - f20;

					if (height > -5.0F) {
						worldrenderer.pos((double) (x + 0.0F), (double) (height + 0.0F), (double) (z + 8.0F)).tex((double) ((x1 + 0.0F) * 0.00390625F + texU), (double) ((z1 + 8.0F) * 0.00390625F + texV)).color(r3, g3, b3, 1F).normal(0.0F, -1.0F, 0.0F).endVertex();
						worldrenderer.pos((double) (x + 8.0F), (double) (height + 0.0F), (double) (z + 8.0F)).tex((double) ((x1 + 8.0F) * 0.00390625F + texU), (double) ((z1 + 8.0F) * 0.00390625F + texV)).color(r3, g3, b3, 1F).normal(0.0F, -1.0F, 0.0F).endVertex();
						worldrenderer.pos((double) (x + 8.0F), (double) (height + 0.0F), (double) (z + 0.0F)).tex((double) ((x1 + 8.0F) * 0.00390625F + texU), (double) ((z1 + 0.0F) * 0.00390625F + texV)).color(r3, g3, b3, 1F).normal(0.0F, -1.0F, 0.0F).endVertex();
						worldrenderer.pos((double) (x + 0.0F), (double) (height + 0.0F), (double) (z + 0.0F)).tex((double) ((x1 + 0.0F) * 0.00390625F + texU), (double) ((z1 + 0.0F) * 0.00390625F + texV)).color(r3, g3, b3, 1F).normal(0.0F, -1.0F, 0.0F).endVertex();
					}

					if (height <= 5.0F) {
						worldrenderer.pos((double) (x + 0.0F), (double) (height + 6.0F - 9.765625E-4F), (double) (z + 8.0F)).tex((double) ((x1 + 0.0F) * 0.00390625F + texU), (double) ((z1 + 8.0F) * 0.00390625F + texV)).color(r1, g1, b1, 1F).normal(0.0F, 1.0F, 0.0F).endVertex();
						worldrenderer.pos((double) (x + 8.0F), (double) (height + 6.0F - 9.765625E-4F), (double) (z + 8.0F)).tex((double) ((x1 + 8.0F) * 0.00390625F + texU), (double) ((z1 + 8.0F) * 0.00390625F + texV)).color(r1, g1, b1, 1F).normal(0.0F, 1.0F, 0.0F).endVertex();
						worldrenderer.pos((double) (x + 8.0F), (double) (height + 6.0F - 9.765625E-4F), (double) (z + 0.0F)).tex((double) ((x1 + 8.0F) * 0.00390625F + texU), (double) ((z1 + 0.0F) * 0.00390625F + texV)).color(r1, g1, b1, 1F).normal(0.0F, 1.0F, 0.0F).endVertex();
						worldrenderer.pos((double) (x + 0.0F), (double) (height + 6.0F - 9.765625E-4F), (double) (z + 0.0F)).tex((double) ((x1 + 0.0F) * 0.00390625F + texU), (double) ((z1 + 0.0F) * 0.00390625F + texV)).color(r1, g1, b1, 1F).normal(0.0F, 1.0F, 0.0F).endVertex();
					}

					if (clX > -1) {
						for (int l1 = 0; l1 < 8; ++l1) {
							worldrenderer.pos((double) (x + (float) l1 + 0.0F), (double) (height + 0.0F), (double) (z + 8.0F)).tex((double) ((x1 + (float) l1 + 0.5F) * 0.00390625F + texU), (double) ((z1 + 8.0F) * 0.00390625F + texV)).color(r2, g2, b2, 1F).normal(-1.0F, 0.0F, 0.0F).endVertex();
							worldrenderer.pos((double) (x + (float) l1 + 0.0F), (double) (height + 6.0F), (double) (z + 8.0F)).tex((double) ((x1 + (float) l1 + 0.5F) * 0.00390625F + texU), (double) ((z1 + 8.0F) * 0.00390625F + texV)).color(r2, g2, b2, 1F).normal(-1.0F, 0.0F, 0.0F).endVertex();
							worldrenderer.pos((double) (x + (float) l1 + 0.0F), (double) (height + 6.0F), (double) (z + 0.0F)).tex((double) ((x1 + (float) l1 + 0.5F) * 0.00390625F + texU), (double) ((z1 + 0.0F) * 0.00390625F + texV)).color(r2, g2, b2, 1F).normal(-1.0F, 0.0F, 0.0F).endVertex();
							worldrenderer.pos((double) (x + (float) l1 + 0.0F), (double) (height + 0.0F), (double) (z + 0.0F)).tex((double) ((x1 + (float) l1 + 0.5F) * 0.00390625F + texU), (double) ((z1 + 0.0F) * 0.00390625F + texV)).color(r2, g2, b2, 1F).normal(-1.0F, 0.0F, 0.0F).endVertex();
						}
					}

					if (clX <= 1) {
						for (int i2 = 0; i2 < 8; ++i2) {
							worldrenderer.pos((double) (x + (float) i2 + 1.0F - 9.765625E-4F), (double) (height + 0.0F), (double) (z + 8.0F)).tex((double) ((x1 + (float) i2 + 0.5F) * 0.00390625F + texU), (double) ((z1 + 8.0F) * 0.00390625F + texV)).color(r2, g2, b2, 1F).normal(1.0F, 0.0F, 0.0F).endVertex();
							worldrenderer.pos((double) (x + (float) i2 + 1.0F - 9.765625E-4F), (double) (height + 6.0F), (double) (z + 8.0F)).tex((double) ((x1 + (float) i2 + 0.5F) * 0.00390625F + texU), (double) ((z1 + 8.0F) * 0.00390625F + texV)).color(r2, g2, b2, 1F).normal(1.0F, 0.0F, 0.0F).endVertex();
							worldrenderer.pos((double) (x + (float) i2 + 1.0F - 9.765625E-4F), (double) (height + 6.0F), (double) (z + 0.0F)).tex((double) ((x1 + (float) i2 + 0.5F) * 0.00390625F + texU), (double) ((z1 + 0.0F) * 0.00390625F + texV)).color(r2, g2, b2, 1F).normal(1.0F, 0.0F, 0.0F).endVertex();
							worldrenderer.pos((double) (x + (float) i2 + 1.0F - 9.765625E-4F), (double) (height + 0.0F), (double) (z + 0.0F)).tex((double) ((x1 + (float) i2 + 0.5F) * 0.00390625F + texU), (double) ((z1 + 0.0F) * 0.00390625F + texV)).color(r2, g2, b2, 1F).normal(1.0F, 0.0F, 0.0F).endVertex();
						}
					}

					if (clZ > -1) {
						for (int j2 = 0; j2 < 8; ++j2) {
							worldrenderer.pos((double) (x + 0.0F), (double) (height + 6.0F), (double) (z + (float) j2 + 0.0F)).tex((double) ((x1 + 0.0F) * 0.00390625F + texU), (double) ((z1 + (float) j2 + 0.5F) * 0.00390625F + texV)).color(r4, g4, b4, 1F).normal(0.0F, 0.0F, -1.0F).endVertex();
							worldrenderer.pos((double) (x + 8.0F), (double) (height + 6.0F), (double) (z + (float) j2 + 0.0F)).tex((double) ((x1 + 8.0F) * 0.00390625F + texU), (double) ((z1 + (float) j2 + 0.5F) * 0.00390625F + texV)).color(r4, g4, b4, 1F).normal(0.0F, 0.0F, -1.0F).endVertex();
							worldrenderer.pos((double) (x + 8.0F), (double) (height + 0.0F), (double) (z + (float) j2 + 0.0F)).tex((double) ((x1 + 8.0F) * 0.00390625F + texU), (double) ((z1 + (float) j2 + 0.5F) * 0.00390625F + texV)).color(r4, g4, b4, 1F).normal(0.0F, 0.0F, -1.0F).endVertex();
							worldrenderer.pos((double) (x + 0.0F), (double) (height + 0.0F), (double) (z + (float) j2 + 0.0F)).tex((double) ((x1 + 0.0F) * 0.00390625F + texU), (double) ((z1 + (float) j2 + 0.5F) * 0.00390625F + texV)).color(r4, g4, b4, 1F).normal(0.0F, 0.0F, -1.0F).endVertex();
						}
					}

					if (clZ <= 1) {
						for (int k2 = 0; k2 < 8; ++k2) {
							worldrenderer.pos((double) (x + 0.0F), (double) (height + 6.0F), (double) (z + (float) k2 + 1.0F - 9.765625E-4F)).tex((double) ((x1 + 0.0F) * 0.00390625F + texU), (double) ((z1 + (float) k2 + 0.5F) * 0.00390625F + texV)).color(r4, g4, b4, 1F).normal(0.0F, 0.0F, 1.0F).endVertex();
							worldrenderer.pos((double) (x + 8.0F), (double) (height + 6.0F), (double) (z + (float) k2 + 1.0F - 9.765625E-4F)).tex((double) ((x1 + 8.0F) * 0.00390625F + texU), (double) ((z1 + (float) k2 + 0.5F) * 0.00390625F + texV)).color(r4, g4, b4, 1F).normal(0.0F, 0.0F, 1.0F).endVertex();
							worldrenderer.pos((double) (x + 8.0F), (double) (height + 0.0F), (double) (z + (float) k2 + 1.0F - 9.765625E-4F)).tex((double) ((x1 + 8.0F) * 0.00390625F + texU), (double) ((z1 + (float) k2 + 0.5F) * 0.00390625F + texV)).color(r4, g4, b4, 1F).normal(0.0F, 0.0F, 1.0F).endVertex();
							worldrenderer.pos((double) (x + 0.0F), (double) (height + 0.0F), (double) (z + (float) k2 + 1.0F - 9.765625E-4F)).tex((double) ((x1 + 0.0F) * 0.00390625F + texU), (double) ((z1 + (float) k2 + 0.5F) * 0.00390625F + texV)).color(r4, g4, b4, 1F).normal(0.0F, 0.0F, 1.0F).endVertex();
						}
					}

				}
			}

			height += 30;
		}
		tessellator.draw();

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disableBlend();
		GlStateManager.enableCull();
	}

}
