package fr.helios.rainbowsixsiege.entities;

import fr.helios.rainbowsixsiege.entities.render.RenderBullet;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class R6Entities
{
    public static void registerEntities() {
        int id = EntityBullet.registerSubsEntities(1);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders(){
        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.EntityRocket.class, RenderBullet.RenderRocket.factory);
        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.EntityBulletBase.class, RenderBullet.RenderBulletBase.factory);
    }
}
