package fewizz.at.world;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class ATTeleporter extends Teleporter{

	public ATTeleporter(WorldServer worldIn) {
		super(worldIn);
	}
	
	//@Override
	//public void placeInPortal(Entity entityIn, float rotationYaw) {
	//}

	//@Override
	//public boolean makePortal(Entity p_85188_1_) {
	//	return false;
	//}
	
	@Override
	public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
		return true;
	}
}
