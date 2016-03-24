package fewizz.at.world;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class ATTeleporter extends Teleporter {

	WorldServer world;
	
	public ATTeleporter(WorldServer worldIn) {
		super(worldIn);
		world = worldIn;
	}

	@Override
	public boolean placeInExistingPortal(Entity entity, float rotationYaw) {
		entity.posX = 0;
		entity.posZ = 0;
		entity.posY = world.getTopSolidOrLiquidBlock(new BlockPos(entity.serverPosX, 0, entity.serverPosZ)).getY() + 1;
		return true;
	}
}
