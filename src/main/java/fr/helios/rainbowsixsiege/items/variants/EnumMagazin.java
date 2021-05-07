package fr.helios.rainbowsixsiege.items.variants;

import com.google.common.collect.Maps;
import fr.helios.rainbowsixsiege.items.R6Items;
import fr.helios.rainbowsixsiege.items.list.ItemVariant;
import fr.helios.rainbowsixsiege.utils.interfaces.IEnumVariant;
import net.minecraft.item.ItemStack;

import java.util.Map;

public enum EnumMagazin implements IEnumVariant
{
    ROCKET(0, "rocket", 1);

    private final int metadata;
    private final String name;

    private final int maxBullets;

    private static final Map<Integer, EnumMagazin> META_LOOKUP = Maps.newHashMap();

    static {
        for(EnumMagazin value : values())
            META_LOOKUP.put(value.metadata, value);
    }

    EnumMagazin(int metadata, String name, int maxBullets)
    {
        this.metadata = metadata;
        this.name = name;
        this.maxBullets = maxBullets;
    }

    @Override public int getMetadata()
    {
        return this.metadata;
    }

    public int getMaxBullets() {
        return this.maxBullets;
    }



    public static EnumMagazin byMetadata(int metadata)
    {
        EnumMagazin value = META_LOOKUP.get(metadata);
        return value == null ? ROCKET : value;
    }

    @Override public ItemStack toItemStack()
    {
        return new ItemStack(R6Items.INSTANCE.magazin, 1, this.getMetadata());
    }

    @Override public String getVar(ItemVariant item)
    {
        return item.getRegistryName().toString() + "=" + this.getName();
    }

    @Override public String getName()
    {
        return this.name;
    }
}
