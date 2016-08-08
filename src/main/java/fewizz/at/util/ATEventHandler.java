package fewizz.at.util;

import fewizz.at.client.CloudRenderer;
import fewizz.at.client.SkyRenderer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class ATEventHandler {
	
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if(event.phase == Phase.END || Minecraft.getMinecraft().theWorld == null || Minecraft.getMinecraft().thePlayer == null || Minecraft.getMinecraft().theWorld.provider.getDimension() != ATConfiguration.dimID) {
			return;
		}
		
		CloudRenderer.cloudTickCounter += 2;
		CloudRenderer.prevColor = CloudRenderer.color;
		CloudRenderer.color = Minecraft.getMinecraft().theWorld.getCloudColour(0);
		SkyRenderer.prevColor = SkyRenderer.color;
		SkyRenderer.color = Minecraft.getMinecraft().theWorld.getSkyColor(Minecraft.getMinecraft().thePlayer, 0);
	}
}
