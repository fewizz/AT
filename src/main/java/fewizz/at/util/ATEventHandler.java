package fewizz.at.util;

import fewizz.at.client.CloudRenderer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ATEventHandler {
	
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		CloudRenderer.cloudTickCounter += 2;
	}
}
