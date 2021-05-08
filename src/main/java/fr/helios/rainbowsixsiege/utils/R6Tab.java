package fr.helios.rainbowsixsiege.utils;

import fr.helios.rainbowsixsiege.items.R6Items;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class R6Tab extends CreativeTabs
{
    public R6Tab(String label)
    {
        super(label);
    }

    @Override public ItemStack createIcon()
    {
        return new ItemStack( R6Items.INSTANCE.magazin);
    }   
}
