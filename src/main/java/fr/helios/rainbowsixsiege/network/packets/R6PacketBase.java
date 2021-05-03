package fr.helios.rainbowsixsiege.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public abstract class R6PacketBase implements IMessage
{

    @Override public void fromBytes(ByteBuf buf)
    {
        deserialize(new PacketBuffer(buf));
    }

    @Override public void toBytes(ByteBuf buf)
    {
        serialize(new PacketBuffer(buf));
    }

    protected abstract void serialize(PacketBuffer buffer);
    protected abstract void deserialize(PacketBuffer buffer);

    public static abstract class R6PacketHandlerBase<PACKET extends R6PacketBase> implements IMessageHandler<PACKET, IMessage> {

        @Override public IMessage onMessage(PACKET message, MessageContext ctx)
        {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
                handle(message, ctx);
            });
            return null;
        }

        protected abstract void handle(PACKET buffer, MessageContext ctx);
    }
}
