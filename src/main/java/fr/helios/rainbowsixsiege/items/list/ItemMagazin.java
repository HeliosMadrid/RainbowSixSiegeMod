package fr.helios.rainbowsixsiege.items.list;

import fr.helios.rainbowsixsiege.items.variants.EnumMagazin;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemMagazin extends ItemVariant<EnumMagazin>
{
    public ItemMagazin()
    {
        super(EnumMagazin.values(), "magazin");
    }

    public static int getCurrentBullets(ItemStack stack) {
        tag(stack);
        if(!stack.getTagCompound().hasKey("bullets"))
            stack.getTagCompound().setInteger("bullets", EnumMagazin.byMetadata(stack.getMetadata()).getMaxBullets());
        return stack.getTagCompound().getInteger("bullets");
    }

    public static void useBullet(ItemStack stack) {
        tag(stack);
        if(stack.getTagCompound().hasKey("bullets")) {
            stack.getTagCompound().setInteger("bullets", stack.getTagCompound().getInteger("bullets") - 1);
        }
    }

    public static void setBullets(ItemStack stack, int amount) {
        tag(stack);
        stack.getTagCompound().setInteger("bullets", amount);
    }

    private static void tag(ItemStack stack) {
        if(!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
    }
}
