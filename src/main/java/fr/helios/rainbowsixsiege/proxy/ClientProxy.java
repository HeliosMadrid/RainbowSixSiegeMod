package fr.helios.rainbowsixsiege.proxy;

import fr.helios.rainbowsixsiege.entities.R6Entities;
import fr.helios.rainbowsixsiege.items.R6Items;
import fr.helios.rainbowsixsiege.utils.R6Keys;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    public ClientProxy()
    {
        MinecraftForge.EVENT_BUS.register(R6Items.INSTANCE);
    }

    @Override public void preInit()
    {
        super.preInit();
        R6Entities.registerRenders();
    }

    @Override public void init()
    {
        super.init();
    }

    @Override public void postInit()
    {
        super.postInit();
        R6Keys.INSTANCE.bindingKeys();
    }
}
