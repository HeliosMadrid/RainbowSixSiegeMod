package fr.helios.rainbowsixsiege.items.list;

import fr.helios.rainbowsixsiege.entities.EntityBullet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemExplosiveThrower extends ItemWeapon
{
    public ItemExplosiveThrower()
    {
        super("rpg");
    }

    @Override protected EntityBullet createEntity(World world, EntityPlayer shooter)
    {
        return new EntityBullet(world, shooter);
    }
}
