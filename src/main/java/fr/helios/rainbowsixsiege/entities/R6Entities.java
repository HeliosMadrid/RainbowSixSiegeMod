package fr.helios.rainbowsixsiege.entities;

import fr.helios.rainbowsixsiege.RainbowSixSiege;
import fr.helios.rainbowsixsiege.entities.render.RenderBullet;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class R6Entities
{
    public static void registerEntities() {
        int id = 1;
        EntityRegistry.registerModEntity(EntityBullet.bullet, EntityBullet.class, "bullet", id++, RainbowSixSiege.instance, Minecraft.getMinecraft().gameSettings.renderDistanceChunks * 16, 1, false);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders(){
        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, RenderBullet.factory);
    }
}
