package fr.helios.rainbowsixsiege.entities.render;

import fr.helios.rainbowsixsiege.entities.EntityBullet;
import fr.helios.rainbowsixsiege.entities.models.R6ModelBase;
import fr.helios.rainbowsixsiege.entities.models.RenderBulletModel;
import fr.helios.rainbowsixsiege.utils.References;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static net.minecraft.client.renderer.GlStateManager.*;

public abstract class RenderBullet extends Render<EntityBullet>
{
    private final ResourceLocation bulletTex;

    private final R6ModelBase model;

    protected RenderBullet(RenderManager renderManager, R6ModelBase model, ResourceLocation bulletTex)
    {
        super(renderManager);
        this.model = model;
        this.bulletTex = bulletTex;
    }

    public static class RenderRocket extends RenderBullet {
        public static Factory factory = new Factory();

        public RenderRocket(RenderManager renderManager)
        {
            super(renderManager, new RenderBulletModel.RocketModel(), new ResourceLocation(References.MODID, "textures/entity/bullets/rocket.png"));
        }

        public static class Factory implements IRenderFactory<EntityBullet.EntityRocket> {
            @Override public Render<? super EntityBullet.EntityRocket> createRenderFor(RenderManager manager)
            {
                return new RenderBullet.RenderRocket(manager);
            }
        }
    }

    public static class RenderBulletBase extends RenderBullet {
        public static Factory factory = new Factory();

        public RenderBulletBase(RenderManager renderManager)
        {
            super(renderManager, new RenderBulletModel.BulletModel(),  new ResourceLocation(References.MODID, "textures/entity/bullets/bullet.png"));
        }

        public static class Factory implements IRenderFactory<EntityBullet.EntityBulletBase> {
            @Override public Render<? super EntityBullet.EntityBulletBase> createRenderFor(RenderManager manager)
            {
                return new RenderBullet.RenderRocket(manager);
            }
        }
    }

    @Nullable @Override protected ResourceLocation getEntityTexture(@Nonnull EntityBullet entity)
    {
        return bulletTex;
    }

    @Override public void doRender(@Nonnull EntityBullet entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.bindEntityTexture(entity);

        pushMatrix();
        color(1.0f, 1.0f, 1.0f, 1.0f);
        translate(x, y, z);
        enableRescaleNormal();

        if(renderOutlines)
        {
            enableColorMaterial();
            enableOutlineMode(getTeamColor(entity));
        }

        model.doRender();

        if(renderOutlines)
        {
            disableColorMaterial();
            disableOutlineMode();
        }

        disableRescaleNormal();
        popMatrix();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
