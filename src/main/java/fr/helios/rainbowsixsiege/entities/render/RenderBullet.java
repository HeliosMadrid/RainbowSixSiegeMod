package fr.helios.rainbowsixsiege.entities.render;

import fr.helios.rainbowsixsiege.entities.EntityBullet;
import fr.helios.rainbowsixsiege.entities.models.BulletModel;
import fr.helios.rainbowsixsiege.utils.References;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static net.minecraft.client.renderer.GlStateManager.*;

public class RenderBullet extends Render<EntityBullet>
{
    private static final ResourceLocation texture = new ResourceLocation(References.MODID, "textures/entity/bullets/bullet.png");
    public static final Factory factory = new Factory();

    private final BulletModel model;

    protected RenderBullet(RenderManager renderManager, BulletModel model)
    {
        super(renderManager);
        this.model = model;
    }

    @Nullable @Override protected ResourceLocation getEntityTexture(@Nonnull EntityBullet entity)
    {
        return texture;
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

        model.render();

        if(renderOutlines)
        {
            disableColorMaterial();
            disableOutlineMode();
        }

        disableRescaleNormal();
        popMatrix();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public static class Factory implements IRenderFactory<EntityBullet>
    {
        @Override public Render<? super EntityBullet> createRenderFor(RenderManager manager)
        {
            return new RenderBullet(manager, new BulletModel());
        }
    }
}
