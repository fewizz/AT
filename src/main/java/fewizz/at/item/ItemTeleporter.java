package fewizz.at.item;

import fewizz.at.AT;
import fewizz.at.util.ATConfiguration;
import fewizz.at.util.IHasName;
import fewizz.at.world.ATTeleporter;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemTeleporter extends Item  implements IHasName {
	
	@Override
	public String getName() {
		return "teleporter";
	}
	
	public ItemTeleporter() {
		setCreativeTab(AT.tab);
		GameRegistry.registerItem(this, getName());
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		if (!worldIn.isRemote) {
			((EntityPlayerMP) playerIn).mcServer.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) playerIn, ATConfiguration.dimID, new ATTeleporter(((EntityPlayerMP) playerIn).mcServer.worldServerForDimension(ATConfiguration.dimID)));
		}
		return itemStackIn;
	}
}
