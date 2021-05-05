package fr.helios.rainbowsixsiege.items;

import com.google.common.collect.Sets;
import fr.helios.rainbowsixsiege.RainbowSixSiege;
import fr.helios.rainbowsixsiege.items.list.ItemBase;
import fr.helios.rainbowsixsiege.items.list.ItemGun;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Objects;
import java.util.Set;

@Mod.EventBusSubscriber(Side.CLIENT)
public class R6Items
{
    public static final R6Items INSTANCE = new R6Items();

    /**Déclarations des items**/
        public ItemGun gun;
    /**fin**/

    private final Set<ItemBase> items = Sets.newHashSet();

    public void initItems() {
        gun = new ItemGun();
    }

    @SubscribeEvent
    public void registerItemsModels(ModelRegistryEvent event) {
        for(ItemBase item : items) {
            registerItemModel(item);
        }
    }

    private void registerItemModel(final ItemBase item) {
        ModelBakery.registerItemVariants(item, item.getRegistryName());
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"));
    }

    public void registerItem(ItemBase item, String name) {
        item.setTranslationKey(name).setRegistryName(name).setCreativeTab(RainbowSixSiege.tab);
        items.add(item);
    }

    public Set<ItemBase> getItems()
    {
        return items;
    }
}
