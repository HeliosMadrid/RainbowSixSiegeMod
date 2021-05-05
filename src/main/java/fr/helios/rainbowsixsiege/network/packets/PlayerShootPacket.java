package fr.helios.rainbowsixsiege.network.packets;

import fr.helios.rainbowsixsiege.items.list.ItemWeapon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PlayerShootPacket extends R6PacketBase
{
    @Override protected void serialize(PacketBuffer buffer)
    {

    }

    @Override protected void deserialize(PacketBuffer buffer)
    {

    }

    public static class Handler extends R6PacketHandlerBase<PlayerShootPacket> {

        @Override protected void handle(PlayerShootPacket buffer, MessageContext ctx)
        {
            EntityPlayer player = ctx.getServerHandler().player;
            ItemStack stack = player.getHeldItem(EnumHand.MAIN_HAND);
            if(stack.getItem() instanceof ItemWeapon)
            {
                ItemWeapon gun = (ItemWeapon)stack.getItem();
                gun.onPlayerShoot(player.world, player);
            }
        }
    }
}
