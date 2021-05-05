package fr.helios.rainbowsixsiege.items.list;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemMagazin extends ItemBase
{
    private static final int maxBullets = 30;

    public ItemMagazin()
    {
        super("magazin");
    }

    public static int getCurrentBullets(ItemStack stack) {
        tag(stack);
        if(!stack.getTagCompound().hasKey("bullets"))
            stack.getTagCompound().setInteger("bullets", maxBullets);
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
