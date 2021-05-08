package fr.helios.rainbowsixsiege.items.list;

import fr.helios.rainbowsixsiege.utils.interfaces.IEnumVariant;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public abstract class ItemVariant<TYPE extends IEnumVariant> extends ItemBase
{
    private final TYPE[] META_LOOKUP;

    public ItemVariant(TYPE[] META_LOOKUP, String name)
    {
        super(name);
        this.META_LOOKUP = META_LOOKUP;
    }

    @Override public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if(isInCreativeTab(tab)) {
            for(TYPE value : META_LOOKUP)
                items.add(value.toItemStack());
        }
    }

    @Override public String getTranslationKey(ItemStack stack)
    {
        return super.getTranslationKey(stack) + "." + META_LOOKUP[stack.getMetadata()].getName();
    }

    public TYPE[] getMetalookup()
    {
        return this.META_LOOKUP;
    }
}
