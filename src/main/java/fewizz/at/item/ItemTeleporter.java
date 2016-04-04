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
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
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
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (!worldIn.isRemote) {
			//((EntityPlayerMP) playerIn).mcServer.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) playerIn, ATConfiguration.dimID, new ATTeleporter(((EntityPlayerMP) playerIn).mcServer.worldServerForDimension(ATConfiguration.dimID)));
			//playerIn.changeDimension(ATConfiguration.dimID);
			((EntityPlayerMP) playerIn).mcServer.getPlayerList().transferPlayerToDimension((EntityPlayerMP) playerIn, ATConfiguration.dimID, new ATTeleporter(((EntityPlayerMP) playerIn).mcServer.worldServerForDimension(ATConfiguration.dimID)));
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}
}
