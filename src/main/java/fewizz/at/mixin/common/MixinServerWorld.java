package fewizz.at.mixin.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import fewizz.at.world.ATPortalForcer;
import fewizz.at.world.dimension.ATDimensionType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PortalForcer;

@Mixin(ServerWorld.class)
public class MixinServerWorld {
	@Shadow
	PortalForcer portalForcer;
	
	@Redirect(method="<init>", at=@At(value="FIELD", target="net/minecraft/server/world/ServerWorld.portalForcer:Lnet/minecraft/world/PortalForcer;"))
	void onSetPortalForcer(ServerWorld world, PortalForcer pf) {
		if(world.dimension.getType().equals(ATDimensionType.INSTANCE))
			portalForcer = new ATPortalForcer(world);
		else portalForcer = pf;
	}
}
