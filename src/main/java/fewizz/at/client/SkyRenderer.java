package fewizz.at.client;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

import fewizz.at.world.biome.ATBiome;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.IRenderHandler;

import static fewizz.at.client.Rend.*;

public class SkyRenderer extends IRenderHandler {

	ResourceLocation skybox = new ResourceLocation("at:textures/sky/skybox.png");

	float x = 1F / 4F;
	float y = 1F / 3F;
	float distance = 0;

	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		distance = (mc.gameSettings.renderDistanceChunks * 16) + 30;
		Tessellator tes = Tessellator.getInstance();
		GlStateManager.disableFog();
		VertexBuffer wr = tes.getBuffer();
		
		Vec3d color = world.getSkyColor(mc.thePlayer, partialTicks);
		int r = (int) (color.xCoord * 255);
		int g = (int) (color.yCoord * 255);;
		int b = (int) (color.zCoord * 255);;
		
		GlStateManager.color(1, 1, 1);
		GlStateManager.disableCull();
		GlStateManager.disableDepth();
		GlStateManager.enableTexture2D();

		mc.renderEngine.bindTexture(skybox);

		GlStateManager.pushMatrix();
		GlStateManager.translate(0, -40, 0);
		GlStateManager.rotate(mc.theWorld.getCelestialAngle(partialTicks) * 360.0F, 1, 0, 0);
		GlStateManager.rotate(45, 0, 1, 0);
		wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

		wr.pos(-distance, -distance, -distance).tex(0, 1 - y).endVertex();
		wr.pos(distance, -distance, -distance).tex(x, 1 - y).endVertex();
		wr.pos(distance, distance, -distance).tex(x, 1 - y * 2).endVertex();
		wr.pos(-distance, distance, -distance).tex(0, 1 - y * 2).endVertex();

		wr.pos(distance, -distance, -distance).tex(x, 1 - y).endVertex();
		wr.pos(distance, -distance, distance).tex(x * 2, 1 - y).endVertex();
		wr.pos(distance, distance, distance).tex(x * 2, 1 - y * 2).endVertex();
		wr.pos(distance, distance, -distance).tex(x, 1 - y * 2).endVertex();

		wr.pos(distance, -distance, distance).tex(x * 2, 1 - y).endVertex();
		wr.pos(-distance, -distance, distance).tex(x * 3, 1 - y).endVertex();
		wr.pos(-distance, distance, distance).tex(x * 3, 1 - y * 2).endVertex();
		wr.pos(distance, distance, distance).tex(x * 2, 1 - y * 2).endVertex();

		wr.pos(-distance, -distance, distance).tex(x * 3, 1 - y).endVertex();
		wr.pos(-distance, -distance, -distance).tex(x * 4, 1 - y).endVertex();
		wr.pos(-distance, distance, -distance).tex(x * 4, 1 - y * 2).endVertex();
		wr.pos(-distance, distance, distance).tex(x * 3, 1 - y * 2).endVertex();

		wr.pos(distance, distance, -distance).tex(x, 1 - y * 2).endVertex();
		wr.pos(distance, distance, distance).tex(x * 2, 1 - y * 2).endVertex();
		wr.pos(-distance, distance, distance).tex(x * 2, 1 - y * 3).endVertex();
		wr.pos(-distance, distance, -distance).tex(x, 1 - y * 3).endVertex();

		wr.pos(distance, -distance, -distance).tex(x, 1 - y).endVertex();
		wr.pos(distance, -distance, distance).tex(x * 2, 1 - y).endVertex();
		wr.pos(-distance, -distance, distance).tex(x * 2, 1).endVertex();
		wr.pos(-distance, -distance, -distance).tex(x, 1).endVertex();

		tes.draw();

		GlStateManager.popMatrix();

		GlStateManager.disableTexture2D();
		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0);
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		/** "Something like a Fog" *****/
		wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);

		for (float angle = 0; angle < 360; angle += 30) {
			float sin1 = MathHelper.sin((float) Math.toRadians(angle)) * distance;
			float sin2 = MathHelper.sin((float) Math.toRadians(angle + 30)) * distance;
			float cos1 = MathHelper.cos((float) Math.toRadians(angle)) * distance;
			float cos2 = MathHelper.cos((float) Math.toRadians(angle + 30)) * distance;
			
			wr.pos(sin1, -10, cos1).color(r, g, b, 255).endVertex();
			wr.pos(sin2, -10, cos2).color(r, g, b, 255).endVertex();
			wr.pos(sin2, distance * 1.2f, cos2).color(r, g, b, 0).endVertex();
			wr.pos(sin1, distance * 1.2f, cos1).color(r, g, b, 0).endVertex();
			
			wr.pos(sin1, -200, cos1).color(r, g, b, 255).endVertex();
			wr.pos(sin2, -200, cos2).color(r, g, b, 255).endVertex();
			wr.pos(sin2, -10, cos2).color(r, g, b, 255).endVertex();
			wr.pos(sin1, -10, cos1).color(r, g, b, 255).endVertex();
		}
		tes.draw();
		/*****************************/

		GlStateManager.alphaFunc(516, 0.1F);
		GlStateManager.shadeModel(GL11.GL_FLAT);
		GlStateManager.enableTexture2D();
		GlStateManager.enableCull();
		GlStateManager.enableFog();
		GlStateManager.enableDepth();
	}

}
