package fr.helios.rainbowsixsiege;

import fr.helios.rainbowsixsiege.events.R6Events;
import fr.helios.rainbowsixsiege.proxy.CommonProxy;
import fr.helios.rainbowsixsiege.utils.R6Tab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static fr.helios.rainbowsixsiege.utils.References.*;

@Mod(
        modid = MODID,
        name = NAME,
        version = VERSION,
        acceptedMinecraftVersions = MC_VERSION,
        guiFactory = GUI_FACTORY,
        canBeDeactivated = true
)
public class RainbowSixSiege
{
    @Mod.Instance
    public static RainbowSixSiege instance;

    @SidedProxy(modId = MODID, clientSide = CLIENT_PROXY, serverSide = SERVER_PROXY)
    public static CommonProxy proxy;

    public static final R6Tab tab = new R6Tab("tab.rainbowsixsiege");

    public RainbowSixSiege()
    {
        MinecraftForge.EVENT_BUS.register(new R6Events());
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }
}
