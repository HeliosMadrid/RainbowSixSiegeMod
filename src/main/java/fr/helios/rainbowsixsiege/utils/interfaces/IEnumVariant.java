package fr.helios.rainbowsixsiege.utils.interfaces;

import fr.helios.rainbowsixsiege.items.list.ItemVariant;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public interface IEnumVariant extends IStringSerializable
{
    int getMetadata();
    ItemStack toItemStack();
    String getVar(ItemVariant item);
}
