package fr.helios.rainbowsixsiege.items.list;

import fr.helios.rainbowsixsiege.entities.vehicle.EntityCar;
import fr.helios.rainbowsixsiege.utils.Vec2;
import fr.helios.rainbowsixsiege.utils.Vec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCar extends ItemBase
{
    public ItemCar()
    {
        super("car");
        setMaxStackSize(1);
    }

    @Override public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand)
    {
        if(!world.isRemote)
        {
            world.spawnEntity(new EntityCar(world, new Vec3(player.posX, player.posY, player.posZ), new Vec2(player.rotationYaw, player.rotationPitch)));
        }
        return EnumActionResult.SUCCESS;
    }
}
