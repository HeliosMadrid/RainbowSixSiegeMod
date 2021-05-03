package fr.helios.rainbowsixsiege.network.packets;

import fr.helios.rainbowsixsiege.items.list.ItemGun;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ReloadGunPacket extends R6PacketBase
{
    @Override protected void serialize(PacketBuffer buffer)
    {

    }

    @Override protected void deserialize(PacketBuffer buffer)
    {

    }

    public static class Handler extends R6PacketHandlerBase<ReloadGunPacket> {

        @Override protected void handle(ReloadGunPacket buffer, MessageContext ctx)
        {
            EntityPlayer player = ctx.getServerHandler().player;
            ItemGun gun = (ItemGun)player.getHeldItem(EnumHand.MAIN_HAND).getItem();
            gun.reload(player);
        }
    }
}
