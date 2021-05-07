package fr.helios.rainbowsixsiege.items.list;

import fr.helios.rainbowsixsiege.entities.EntityBullet;
import fr.helios.rainbowsixsiege.items.R6Items;
import fr.helios.rainbowsixsiege.items.variants.EnumWeapon;
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

public class ItemWeapon extends ItemVariant<EnumWeapon>
{
    public ItemWeapon() {
        super(EnumWeapon.values(), "weapon");
        setMaxStackSize(1);
    }

    public void onPlayerShoot(World world, EntityPlayer shooter)
    {
        ItemStack stack = shooter.getHeldItem(EnumHand.MAIN_HAND);

        if(stack.getItem() instanceof ItemWeapon && hasMag(stack))
        {
            if(!world.isRemote)
            {
                EntityBullet entityBullet = EnumWeapon.byMetadata(stack.getMetadata()).createBullet(world, shooter, stack);
                entityBullet.shoot(shooter);
                ItemMagazin.useBullet(stack);

                world.spawnEntity(entityBullet);
            }

            world.playSound(null, shooter.posX +  shooter.motionX, shooter.posY + shooter.motionY, shooter.posZ + shooter.motionZ, EnumWeapon.byMetadata(stack.getMetadata()).getShootSound(), SoundCategory.PLAYERS, 0.8F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 360 * 0.5F);

            shooter.addStat(Objects.requireNonNull(StatList.getObjectUseStats(this)));
        }
    }

    private boolean hasMag(ItemStack stack) {
        tag(stack);
        if(stack.getTagCompound().hasKey("hasMag") && stack.getTagCompound().getBoolean("hasMag")) {
            if(stack.getTagCompound().hasKey("bullets") && stack.getTagCompound().getInteger("bullets") > 0)
                return true;
        }
        return false;
    }

    private ItemStack findMag(EntityPlayer player) {
        for(int i = 0; i < player.inventory.getSizeInventory(); i++) {
            if(player.inventory.getStackInSlot(i).getItem() instanceof ItemMagazin && ItemMagazin.getCurrentBullets(player.inventory.getStackInSlot(i)) > 0){
                ItemStack mag = player.inventory.getStackInSlot(i);
                player.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
                return mag;
            }
        } return ItemStack.EMPTY;
    }

    private void decharge(EntityPlayer player, ItemStack gun) {
        ItemStack mag = new ItemStack(R6Items.INSTANCE.magazin);
        ItemMagazin.setBullets(mag, gun.getTagCompound().getInteger("bullets"));
        gun.getTagCompound().setBoolean("hasMag", false);
        gun.getTagCompound().setInteger("bullets", 0);
        for(int i = 0; i < player.inventory.getSizeInventory() - 5; i++) {
            if(player.inventory.getStackInSlot(i).isEmpty()){
                System.out.println(player.inventory.getSizeInventory());
                player.inventory.setInventorySlotContents(i, mag);
                return;
            }
        }
        player.dropItem(mag, true);
    }

    public void reload(EntityPlayer player) {
        ItemStack gun = player.getHeldItem(EnumHand.MAIN_HAND);
        tag(gun);
        if(gun.getTagCompound().hasKey("hasMag") && gun.getTagCompound().getBoolean("hasMag")) {
            decharge(player, gun);
        } else {
            ItemStack mag = findMag(player);
            if(mag != null && !mag.isEmpty() && ItemMagazin.getCurrentBullets(mag) > 0){
                System.out.println("RELOAD");
                gun.getTagCompound().setBoolean("hasMag", true);
                gun.getTagCompound().setInteger("bullets", ItemMagazin.getCurrentBullets(mag));
            }
        }
    }

    private void tag(ItemStack stack) {
        if(!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
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
        return 0;
    }
}