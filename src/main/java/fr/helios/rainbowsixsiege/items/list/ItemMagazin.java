package fr.helios.rainbowsixsiege.items.list;

import fr.helios.rainbowsixsiege.entities.EntityBullet;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.Objects;

public class ItemMagazin extends ItemBase
{
    private final int maxBullets;

    final String cb = "currentBullets";

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
        return Objects.requireNonNull(stack.getTagCompound()).getInteger(cb);
    }

    private void setup(ItemStack stack) {
        stack.setTagCompound(new NBTTagCompound());
        Objects.requireNonNull(stack.getTagCompound()).setInteger(cb, maxBullets);
    }

    public void useBullet(ItemStack stack) {
        if(!stack.hasTagCompound()) setup(stack);
        Objects.requireNonNull(stack.getTagCompound()).setInteger(cb, stack.getTagCompound().getInteger(cb) - 1);
    }
}
