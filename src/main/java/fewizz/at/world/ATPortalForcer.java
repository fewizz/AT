package fewizz.at.world;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PortalForcer;

public class ATPortalForcer extends PortalForcer {

	public ATPortalForcer(ServerWorld serverWorld_1) {
		super(serverWorld_1);
	}
	
	@Override
	public boolean method_8653(Entity entity_1, float float_1) {
		entity_1.setPosition(0, 256, 0);
		return true;
	}

}
