package fr.helios.rainbowsixsiege.items.list;

import fr.helios.rainbowsixsiege.entities.EntityBullet;
import fr.helios.rainbowsixsiege.utils.R6Sounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class ItemGun extends ItemBase
{
    private ItemStack magazin;

    public ItemGun()
    {
        super("gun");
        setMaxStackSize(1);
        setMaxDamage(500);
    }

    public void onPlayerShoot(World world, EntityPlayer shooter)
    {
        ItemStack stack = shooter.getHeldItem(EnumHand.MAIN_HAND);

        if(stack.getItem() instanceof ItemGun){
            if (((ItemGun)stack.getItem()).hasValidMag(stack))
            {
                if(!world.isRemote){
                    ItemMagazin mag = (ItemMagazin)magazin.getItem();

                    EntityBullet entityBullet = mag.createBullet(world, shooter);
                    entityBullet.shoot(shooter);

                    mag.useBullet(magazin);

                    world.spawnEntity(entityBullet);
                }

                world.playSound(null, shooter.posX +  shooter.motionX, shooter.posY + shooter.motionY, shooter.posZ + shooter.motionZ, R6Sounds.BULLET_SHOOT, SoundCategory.PLAYERS, 0.8F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 360 * 0.5F);

                shooter.addStat(Objects.requireNonNull(StatList.getObjectUseStats(this)));
            }
        }
    }

    private ItemStack findMagazin(EntityPlayer player) {
        if (this.isMagazin(player.getHeldItem(EnumHand.OFF_HAND)))
        {
            return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else if (this.isMagazin(player.getHeldItem(EnumHand.MAIN_HAND)))
        {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        else
        {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (this.isMagazin(itemstack))
                {
                    return itemstack;
                }
            }

            return ItemStack.EMPTY;
        }
    }

    public boolean hasValidMag(ItemStack stack) {
        if(!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
            Objects.requireNonNull(stack.getTagCompound()).setBoolean("hasMag", false);
            return false;
        }
        boolean hasMag = Objects.requireNonNull(stack.getTagCompound()).getBoolean("hasMag") && magazin != null && !magazin.isEmpty();

        if(hasMag) {
            ItemMagazin mag = (ItemMagazin)magazin.getItem();
            return mag.getCurrentBullets(magazin) > 0;
        } return false;
    }

    private boolean isMagazin(ItemStack stack) {
        return stack.getItem() instanceof ItemMagazin;
    }

    public void reload(EntityPlayer player) {
        magazin = findMagazin(player);

        if(magazin != null && !magazin.isEmpty() && ((ItemMagazin)magazin.getItem()).getCurrentBullets(magazin) > 0)
        {
            ItemStack stack = player.getHeldItem(EnumHand.MAIN_HAND);
            if(!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
            Objects.requireNonNull(stack.getTagCompound()).setBoolean("hasMag", true);
        }
    }

    @Override public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        return true;
    }

    @Override public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player)
    {
        return true;
    }

    @Override public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }
}
