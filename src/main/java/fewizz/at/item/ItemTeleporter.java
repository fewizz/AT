package fewizz.at.item;

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

public class ItemTeleporter extends Item {
	public ItemTeleporter() {
		setCreativeTab(CreativeTabs.tabFood);
		GameRegistry.registerItem(this, "teleporter");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		if (!worldIn.isRemote) {
			//if (playerIn.dimension == -1)
				((EntityPlayerMP) playerIn).mcServer.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) playerIn, 3, new ATTeleporter(((EntityPlayerMP) playerIn).mcServer.worldServerForDimension(3)));
			//else
				//playerIn.travelToDimension(-1);
		}
		return itemStackIn;
	}
}
