package fr.helios.rainbowsixsiege.items.list;

import fr.helios.rainbowsixsiege.items.R6Items;
import net.minecraft.item.Item;

public class ItemBase extends Item
{
    public ItemBase(String name)
    {
        R6Items.INSTANCE.registerItem(this, name);
    }
}
