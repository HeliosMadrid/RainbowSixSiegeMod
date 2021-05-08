package fr.helios.rainbowsixsiege.items.variants;

import com.google.common.collect.Maps;
import fr.helios.rainbowsixsiege.entities.EntityBullet;
import fr.helios.rainbowsixsiege.items.R6Items;
import fr.helios.rainbowsixsiege.items.list.ItemVariant;
import fr.helios.rainbowsixsiege.utils.R6Sounds;
import fr.helios.rainbowsixsiege.utils.interfaces.IEnumVariant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.Map;

public enum EnumWeapon implements IEnumVariant
{
    RPG(0, "rpg", R6Sounds.BULLET_SHOOT, 5, 5, (World world, EntityPlayer player) -> {
        return new EntityBullet.EntityRocket(world, player);
    }), DESERT_EAGLE(1, "desert_eagle", R6Sounds.BULLET_SHOOT, 30, 100, (World world, EntityPlayer player) -> {
        return new EntityBullet.EntityBulletBase(world, player);
});

    private final int metadata, damage, speed;
    private final String name;
    private final SoundEvent shootSound;

    private final bulletBuilder bulletBuilder;

    private static final Map<Integer, EnumWeapon> META_LOOKUP = Maps.newHashMap();

    static {
        for(EnumWeapon value : values())
            META_LOOKUP.put(value.metadata, value);
    }

    EnumWeapon(int metadata, String name, SoundEvent shootSound, int damage, int speed, bulletBuilder bulletBuilder)
    {
        this.metadata = metadata;
        this.name = name;
        this.shootSound = shootSound;
        this.damage = damage;
        this.speed = speed;
        this.bulletBuilder = bulletBuilder;
    }

    public EnumWeapon.bulletBuilder getBulletBuilder()
    {
        return bulletBuilder;
    }

    public SoundEvent getShootSound()
    {
        return shootSound;
    }

    public static EnumWeapon byMetadata(int metadata) {
        EnumWeapon value = META_LOOKUP.get(metadata);
        return value == null ? RPG : value;
    }

    @Override public int getMetadata()
    {
        return this.metadata;
    }

    @Override public ItemStack toItemStack()
    {
        return new ItemStack(R6Items.INSTANCE.weapon, 1, this.metadata);
    }

    @Override public String getVar(ItemVariant item)
    {
        return item.getRegistryName().toString() + "=" + this.getName();
    }

    @Override public String getName()
    {
        return this.name;
    }

    @FunctionalInterface
    public interface bulletBuilder {
        EntityBullet build(World world, EntityPlayer shooter);
    }
}
