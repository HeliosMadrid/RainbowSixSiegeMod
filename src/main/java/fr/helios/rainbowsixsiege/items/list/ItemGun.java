package fr.helios.rainbowsixsiege.items.list;

import fr.helios.rainbowsixsiege.entities.EntityBullet;
import fr.helios.rainbowsixsiege.items.R6Items;
import fr.helios.rainbowsixsiege.utils.R6Sounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
    public ItemGun()
    {
        super("gun");
        setMaxStackSize(1);
        setMaxDamage(500);
    }

    public void onPlayerShoot(World world, EntityPlayer shooter)
    {
        ItemStack stack = shooter.getHeldItem(EnumHand.MAIN_HAND);

        if(stack.getItem() instanceof ItemGun)
        {
            if(!world.isRemote)
            {
                EntityBullet entityBullet = new EntityBullet(world, shooter);
                entityBullet.shoot(shooter);

                world.spawnEntity(entityBullet);
            }

            world.playSound(null, shooter.posX +  shooter.motionX, shooter.posY + shooter.motionY, shooter.posZ + shooter.motionZ, R6Sounds.BULLET_SHOOT, SoundCategory.PLAYERS, 0.8F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 360 * 0.5F);

            shooter.addStat(Objects.requireNonNull(StatList.getObjectUseStats(this)));
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
        return 0;
    }
}
