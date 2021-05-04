package fr.helios.rainbowsixsiege.events;

import fr.helios.rainbowsixsiege.items.R6Items;
import fr.helios.rainbowsixsiege.items.list.ItemBase;
import fr.helios.rainbowsixsiege.items.list.ItemGun;
import fr.helios.rainbowsixsiege.network.R6Network;
import fr.helios.rainbowsixsiege.network.packets.PlayerShootPacket;
import fr.helios.rainbowsixsiege.utils.R6Keys;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber()
public class R6Events
{
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(R6Items.INSTANCE.getItems().toArray(new ItemBase[0]));
    }

    @SubscribeEvent
    public void playerShoot(PlayerInteractEvent.LeftClickBlock event) {
        if(event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemGun){
            event.setCanceled(true);
            R6Network.NETWORK.sendToServer(new PlayerShootPacket());
        }
    }

    @SubscribeEvent
    public void leftClickEmpty(PlayerInteractEvent.LeftClickEmpty event) {
        if(event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemGun){
            R6Network.NETWORK.sendToServer(new PlayerShootPacket());
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onMouseDown(InputEvent.MouseInputEvent event) {
            if(Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemGun){
                if(Minecraft.getMinecraft().gameSettings.keyBindAttack.isPressed()) {
                    R6Network.NETWORK.sendToServer(new PlayerShootPacket());
                }
            }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onKeyDown(InputEvent.KeyInputEvent event)
    {
        R6Keys.INSTANCE.performKeys();
    }
}
