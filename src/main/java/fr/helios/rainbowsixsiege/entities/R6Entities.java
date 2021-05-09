package fr.helios.rainbowsixsiege.entities;

import fr.helios.rainbowsixsiege.RainbowSixSiege;
import fr.helios.rainbowsixsiege.entities.render.RenderBullet;
import fr.helios.rainbowsixsiege.entities.render.RenderCar;
import fr.helios.rainbowsixsiege.entities.vehicle.EntityCar;
import fr.helios.rainbowsixsiege.utils.References;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class R6Entities
{
    public static void registerEntities() {
        int id = EntityBullet.registerSubsEntities(1);
        EntityRegistry.registerModEntity(new ResourceLocation(References.MODID, "car"), EntityCar.class, "car", id++, RainbowSixSiege.instance, Minecraft.getMinecraft().gameSettings.renderDistanceChunks * 16 * 2, 1, true);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders(){
        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.EntityRocket.class, RenderBullet.RenderRocket.factory);
        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.EntityBulletBase.class, RenderBullet.RenderBulletBase.factory);

        RenderingRegistry.registerEntityRenderingHandler(EntityCar.class, RenderCar.factory);
    }
}
