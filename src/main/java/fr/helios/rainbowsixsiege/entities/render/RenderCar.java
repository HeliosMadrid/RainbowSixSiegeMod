package fr.helios.rainbowsixsiege.entities.render;

import fr.helios.rainbowsixsiege.entities.models.ModelCar;
import fr.helios.rainbowsixsiege.entities.vehicle.EntityCar;
import fr.helios.rainbowsixsiege.utils.References;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import static net.minecraft.client.renderer.GlStateManager.*;

import javax.annotation.Nullable;

public class RenderCar extends Render<EntityCar>
{
    public static Factory factory = new Factory();
    private static final ResourceLocation texture = new ResourceLocation(References.MODID, "textures/entity/car.png");
    private final ModelCar model = new ModelCar();

    public RenderCar(RenderManager renderManager)
    {
        super(renderManager);
    }

    @Nullable @Override protected ResourceLocation getEntityTexture(EntityCar entity)
    {
        return texture;
    }

    @Override public void doRender(EntityCar entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.bindEntityTexture(entity);

        pushMatrix();
        color(1f, 1f, 1f, 1f);
        translate(x, y + 4.5, z);
        scale(5f, 5f, 5f);
        rotate(180, 1, 0, 0);

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

    public static class Factory implements IRenderFactory<EntityCar> {
        @Override public Render<? super EntityCar> createRenderFor(RenderManager manager)
        {
            return new RenderCar(manager);
        }
    }
}
