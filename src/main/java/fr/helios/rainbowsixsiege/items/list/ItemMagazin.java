package fr.helios.rainbowsixsiege.items.list;

import fr.helios.rainbowsixsiege.entities.EntityBullet;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemMagazin extends ItemBase
{
    private int maxBullets;

    String cb = "currentBullets";

    public ItemMagazin()
    {
        super("magazin");
        setMaxStackSize(16);
        maxBullets = 250;
    }

    public EntityBullet createBullet(World world, EntityLivingBase shooter) {
        return new EntityBullet(world, shooter);
    }

    public int getCurrentBullets(ItemStack stack)
    {
        if(!stack.hasTagCompound()) setup(stack);
        return stack.getTagCompound().getInteger(cb);
    }

    private void setup(ItemStack stack) {
        stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setInteger(cb, maxBullets);
    }

    public void useBullet(ItemStack stack) {
        if(!stack.hasTagCompound()) setup(stack);
        stack.getTagCompound().setInteger(cb, stack.getTagCompound().getInteger(cb) - 1);
    }
}
