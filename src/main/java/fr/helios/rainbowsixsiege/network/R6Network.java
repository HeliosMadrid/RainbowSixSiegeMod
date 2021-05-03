package fr.helios.rainbowsixsiege.network;

import fr.helios.rainbowsixsiege.network.packets.PlayerShootPacket;
import fr.helios.rainbowsixsiege.network.packets.ReloadGunPacket;
import fr.helios.rainbowsixsiege.utils.References;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class R6Network
{
    static int id;

    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(References.MODID);

    public static void initPackets() {
        NETWORK.registerMessage(ReloadGunPacket.Handler.class, ReloadGunPacket.class, id(), Side.SERVER);

        NETWORK.registerMessage(PlayerShootPacket.Handler.class, PlayerShootPacket.class, id(), Side.SERVER);
        NETWORK.registerMessage(PlayerShootPacket.Handler.class, PlayerShootPacket.class, id(), Side.CLIENT);
    }

    private static int id() {
        return id++;
    }
}
