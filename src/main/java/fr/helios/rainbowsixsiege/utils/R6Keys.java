package fr.helios.rainbowsixsiege.utils;

import fr.helios.rainbowsixsiege.gui.GlobalUI;
import fr.helios.rainbowsixsiege.network.R6Network;
import fr.helios.rainbowsixsiege.network.packets.ReloadGunPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static org.lwjgl.input.Keyboard.*;

public class R6Keys
{
    public static final R6Keys INSTANCE = new R6Keys();
    private final KeyBinding[] keys = new KeyBinding[] {
            registerKey("globalUI", KEY_U),
            registerKey("reload", KEY_R),
            registerKey("test", KEY_I)
    };

    public void bindingKeys() {
        for(KeyBinding key : keys) {
            ClientRegistry.registerKeyBinding(key);
        }
    }

    private KeyBinding registerKey(String name, int keyId) {
        return new KeyBinding(References.MODID + ".keys." + name, keyId, "keys.categories." + References.MODID);
    }

    public KeyBinding getKeyByIndex(int index) {
        return this.keys[index];
    }

    @SideOnly(Side.CLIENT)
    public void performKeys() {
        if(INSTANCE.getKeyByIndex(0).isPressed())
            GlobalUI.displayGui(Minecraft.getMinecraft().player);
        if(INSTANCE.getKeyByIndex(1).isPressed())
            R6Network.NETWORK.sendToServer(new ReloadGunPacket());
        if(INSTANCE.getKeyByIndex(2).isPressed())
            test();

    }

    private void test() {
    }
}
