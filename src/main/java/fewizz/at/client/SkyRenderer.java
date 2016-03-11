package fewizz.at.client;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IRenderHandler;

import static fewizz.at.client.Rend.*;

public class SkyRenderer extends IRenderHandler {

	ResourceLocation skybox = new ResourceLocation("at:textures/sky/skybox2.png");
	
	float x = 1F / 4F;
	float y = 1F / 3F;
	float distance = 0;
	FloatBuffer fb = GLAllocation.createDirectFloatBuffer(16);
	
	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		distance = (mc.gameSettings.renderDistanceChunks * 16) + 20;
		Tessellator tes = Tessellator.getInstance();
		WorldRenderer wr = tes.getWorldRenderer();
		GlStateManager.color(1, 1, 1);
		GlStateManager.disableCull();
		
		GL11.glGetFloat(GL11.GL_FOG_COLOR, fb);
		GL11.glFog(GL11.GL_FOG_COLOR, fb.put(RED, fb.get(RED) / 2F).put(GREEN, fb.get(GREEN) * 2F).put(Rend.BLUE, fb.get(BLUE) * 3F));
		GlStateManager.setFogEnd(distance * 3);
		GlStateManager.enableTexture2D();
		
		mc.renderEngine.bindTexture(skybox);
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, -40, 0);
		GlStateManager.rotate(mc.theWorld.getCelestialAngle(partialTicks) * 360.0F, 1, 0, 0);
		wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		
		wr.pos(-distance, -distance, -distance).tex(0, 1 - y).endVertex();
		wr.pos(distance, -distance, -distance) .tex(x, 1 - y).endVertex();
		wr.pos(distance, distance, -distance)  .tex(x, 1 -y * 2).endVertex();
		wr.pos(-distance, distance, -distance) .tex(0, 1 - y * 2).endVertex();
		
		wr.pos(distance, -distance, -distance) .tex(x, 1 - y).endVertex();
		wr.pos(distance, -distance, distance)  .tex(x * 2, 1 - y).endVertex();
		wr.pos(distance, distance, distance)   .tex(x * 2, 1 - y * 2).endVertex();
		wr.pos(distance, distance, -distance)  .tex(x, 1 - y * 2).endVertex();
		
		wr.pos(distance, -distance, distance)  .tex(x * 2, 1 - y).endVertex();
		wr.pos(-distance, -distance, distance) .tex(x * 3, 1 - y).endVertex();
		wr.pos(-distance, distance, distance)  .tex(x * 3, 1 - y * 2).endVertex();
		wr.pos(distance, distance, distance)   .tex(x * 2, 1 - y * 2).endVertex();
		
		wr.pos(-distance, -distance, distance) .tex(x * 3, 1 - y).endVertex();
		wr.pos(-distance, -distance, -distance).tex(x * 4, 1 - y).endVertex();
		wr.pos(-distance, distance, -distance) .tex(x * 4, 1 - y * 2).endVertex();
		wr.pos(-distance, distance, distance)  .tex(x * 3, 1 - y * 2).endVertex();
		
		wr.pos(distance, distance, -distance)  .tex(x, 1 - y * 2).endVertex();
		wr.pos(distance, distance, distance)   .tex(x * 2, 1 - y * 2).endVertex();
		wr.pos(-distance, distance, distance)  .tex(x * 2, 1 - y * 3).endVertex();
		wr.pos(-distance, distance, -distance) .tex(x, 1 - y * 3).endVertex();
		
		wr.pos(distance, -distance, -distance) .tex(x, 1 - y).endVertex();
		wr.pos(distance, -distance, distance)  .tex(x * 2, 1 - y).endVertex();
		wr.pos(-distance, -distance, distance) .tex(x * 2, 1).endVertex();
		wr.pos(-distance, -distance, -distance).tex(x, 1).endVertex();
		
		tes.draw();
		
		GlStateManager.popMatrix();
		GlStateManager.enableCull();
	}

}
