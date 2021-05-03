package fr.helios.rainbowsixsiege.proxy;

import fr.helios.rainbowsixsiege.entities.R6Entities;
import fr.helios.rainbowsixsiege.items.R6Items;
import fr.helios.rainbowsixsiege.network.R6Network;

public class CommonProxy
{
    public void preInit() {
        R6Items.INSTANCE.initItems();
        R6Network.initPackets();
    }

    public void init() {
        R6Entities.registerEntities();
    }

    public void postInit() {

    }
}
