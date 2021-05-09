package fr.helios.rainbowsixsiege.entities.vehicle;

import fr.helios.rainbowsixsiege.utils.Vec2;
import fr.helios.rainbowsixsiege.utils.Vec3;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityCar extends Entity
{
    public EntityCar(World world)
    {
        super(world);
    }

    public EntityCar(World world, Vec3 pos, Vec2 rotation)
    {
        this(world);
        this.posX = pos.x;
        this.posY = pos.y;
        this.posZ = pos.z;
        this.rotationYaw = (float)rotation.x;
        this.rotationPitch = (float)rotation.z;
        System.out.println("POS :" + posX + " ," + posY + " ," + posZ);
    }

    @Override protected void entityInit()
    {

    }

    @Override protected void readEntityFromNBT(NBTTagCompound compound)
    {

    }

    @Override protected void writeEntityToNBT(NBTTagCompound compound)
    {

    }
}
