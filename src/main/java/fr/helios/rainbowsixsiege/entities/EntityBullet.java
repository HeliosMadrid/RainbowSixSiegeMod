package fr.helios.rainbowsixsiege.entities;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import fr.helios.rainbowsixsiege.RainbowSixSiege;
import fr.helios.rainbowsixsiege.entities.render.RenderBullet;
import fr.helios.rainbowsixsiege.utils.References;
import fr.helios.rainbowsixsiege.utils.Vec3;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class EntityBullet extends Entity implements IProjectile
{
    private static final Predicate<Entity> BULLET_TARGETS = Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, Entity::canBeCollidedWith);
    private static final DataParameter<Byte> CRITICAL = EntityDataManager.createKey(EntityBullet.class, DataSerializers.BYTE);

    private Entity shooter;
    private float damage;
    private final float knockbackStrength;
    private double speed;
    private boolean inGround;

    public EntityBullet(World world)
    {
        super(world);
        this.knockbackStrength = 1f;
        this.setSize(0.5F, 0.5F);
    }

    public EntityBullet(World world, double x, double y, double z)
    {
        this(world);
        this.setPosition(x, y, z);
    }

    protected EntityBullet(World world, EntityLivingBase shooter, int damage, int speed)
    {
        this(world, shooter.posX, shooter.posY + (double)shooter.getEyeHeight(), shooter.posZ);
        this.shooter = shooter;
        this.damage = damage;
        this.speed = speed;
    }

    public abstract Render<? extends EntityBullet> createRender(RenderManager manager);

    public void hitEffect(RayTraceResult result)
    {

    }

    public static class EntityRocket extends EntityBullet {
        public static final ResourceLocation bullet = new ResourceLocation(References.MODID, "entityrocket");
        public EntityRocket(World world)
        {
            super(world);
        }

        public EntityRocket(World world, EntityLivingBase shooter)
        {
            super(world, shooter, 5, 5);
        }

        @Override public Render<? extends EntityBullet> createRender(RenderManager manager)
        {
            return new RenderBullet.RenderRocket(manager);
        }

        @Override public void hitEffect(RayTraceResult result)
        {
            this.world.createExplosion(this, result.hitVec.x, result.hitVec.y, result.hitVec.z, 10f, true);
        }
    }

    public static class EntityBulletBase extends EntityBullet {
        public static final ResourceLocation bullet = new ResourceLocation(References.MODID, "entitybullet");
        public EntityBulletBase(World world)
        {
            super(world);
        }

        public EntityBulletBase(World world, EntityLivingBase shooter)
        {
            super(world, shooter, 30, 100);
        }

        @Override public Render<? extends EntityBullet> createRender(RenderManager manager)
        {
            return new RenderBullet.RenderBulletBase(manager);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
        double dist = this.getEntityBoundingBox().getAverageEdgeLength() * 10.0D;

        if (Double.isNaN(dist))
        {
            dist = 1.0D;
        }

        dist = dist * 64.0D * getRenderDistanceWeight();
        return distance < dist * dist;
    }

    @Override protected void entityInit()
    {
        this.dataManager.register(CRITICAL, (byte)0);
    }

    public void shoot(EntityPlayer player) {
        Vec3 forward = Vec3.getForward(player);

        this.shoot(forward.x, forward.y, forward.z, 0.0F, 1.0F);

        this.motionX += shooter.motionX;
        this.motionZ += shooter.motionZ;

        if (!shooter.onGround)
        {
            this.motionY += shooter.motionY;
        }

    }

    @Override public void shoot(double x, double y, double z, float speed, float inaccuracy)
    {
        Vec3 forward = new Vec3(x, y, z);
        forward.normalize();
        forward.multiply(this.speed);
        setMotion(forward);
        setRot(forward);
    }

    private void setRot(Vec3 forward) {
        this.rotationYaw = (float)forward.getYaw();
        this.rotationPitch = (float)forward.getPitch();
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }

    private boolean hasRot() {
        return prevRotationPitch != 0f && prevRotationYaw != 0;
    }

    @SideOnly(Side.CLIENT)
    @Override public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport)
    {
        this.setPosition(x, y, z);
        this.setRotation(yaw, pitch);
    }

    private void setMotion(Vec3 velocity) {
        this.motionX = velocity.x;
        this.motionY = velocity.y;
        this.motionZ = velocity.z;
    }

    @SideOnly(Side.CLIENT)
    @Override public void setVelocity(double x, double y, double z)
    {
        setMotion(new Vec3(x, y, z));
        if(!hasRot()) setRot(new Vec3(x, y, z));
    }

    @Override public void onUpdate()
    {
        super.onUpdate();

        if(inGround) setDead();
        if(!hasRot()) setRot(new Vec3(posX, posY, posZ));

        Vec3 pos = new Vec3(this.posX, this.posY, this.posZ);
        Vec3 velocity = pos.add(this.motionX, this.motionY, this.motionZ);

        RayTraceResult result = this.world.rayTraceBlocks(pos.toVec3d(), velocity.toVec3d(), false, true, false);

        if(result != null) {
            velocity = new Vec3(result.hitVec);
        }

        Entity entity = findEntityOnPath(pos.toVec3d(), velocity.toVec3d());

        if(entity != null) {
            result = new RayTraceResult(entity);
        }

        if(result != null && !ForgeEventFactory.onProjectileImpact(this, result)) {
            this.hitEffect(result);
            if(result.entityHit != null) {
                onEntityHit(result.entityHit);
            } else {
                this.inGround = true;
            }
        }

        if (this.isInWater())
        {
            for (int i = 0; i < 4; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ);
            }
        }

        this.setPosition(velocity.x, velocity.y, velocity.z);
        this.doBlockCollisions();
    }

    protected void onEntityHit(@Nonnull Entity victim) {

        int amountDamage = (int)this.damage;
        DamageSource damagesource;

        if (this.shooter == null)
            damagesource = causeBulletDamage(this, this);
        else
            damagesource = causeBulletDamage(this, this.shooter);

        if (victim.attackEntityFrom(damagesource, (float)amountDamage))
        {
            if (victim instanceof EntityLivingBase)
            {
                EntityLivingBase livingVictim = (EntityLivingBase)victim;

                if (this.knockbackStrength > 0)
                {
                    float norme2d = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

                    if (norme2d > 0.0F)
                    {
                        livingVictim.addVelocity(this.motionX * (double)this.knockbackStrength * 0.5 / (double)norme2d, 0.1D, this.motionZ * (double)this.knockbackStrength * 0.5D / (double)norme2d);
                    }
                }

                if (this.shooter instanceof EntityLivingBase)
                {
                    EnchantmentHelper.applyThornEnchantments(livingVictim, this.shooter);
                }

                if (this.shooter != null && livingVictim != this.shooter && livingVictim instanceof EntityPlayer && this.shooter instanceof EntityPlayerMP)
                {
                    ((EntityPlayerMP)this.shooter).connection.sendPacket(new SPacketChangeGameState(6, 0.0F));
                }
            }

            if(!(victim instanceof EntityEnderman))
                setDead();
        }
    }

    private DamageSource causeBulletDamage(EntityBullet bullet, @Nullable Entity indirectEntity) {
        return (new EntityDamageSourceIndirect("bullet", bullet, indirectEntity)).setProjectile();
    }

    public boolean getIsCritical()
    {
        byte b0 = this.dataManager.get(CRITICAL);
        return (b0 & 1) != 0;
    }

    protected Entity findEntityOnPath(Vec3d start, Vec3d end)
    {
        Entity entity = null;
        List<Entity> entities = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D), BULLET_TARGETS::test);
        double prevDist = 0.0D;

        for (Entity target : entities)
        {
            if (!world.isRemote && target != this.shooter)
            {
                AxisAlignedBB aabb = target.getEntityBoundingBox();
                RayTraceResult raytraceresult = aabb.calculateIntercept(start, end);

                if (raytraceresult != null)
                {
                    double dist = start.squareDistanceTo(raytraceresult.hitVec);

                    if (dist < prevDist || prevDist == 0.0D)
                    {
                        entity = target;
                        prevDist = dist;
                    }
                }
            }
        }

        return entity;
    }

    @Override protected void readEntityFromNBT(NBTTagCompound compound)
    {
        this.speed = compound.getDouble("speed");

        if (compound.hasKey("damage", 99))
        {
            this.damage = (float)compound.getDouble("damage");
        }
    }

    @Override protected void writeEntityToNBT(NBTTagCompound compound)
    {
        compound.setDouble("damage", this.damage);
        compound.setBoolean("crit", this.getIsCritical());
        compound.setDouble("speed", this.speed);
        this.setIsCritical(compound.getBoolean("crit"));
    }

    @Override protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override public float getEyeHeight()
    {
        return 0.0F;
    }

    @Override public boolean canBeAttackedWithItem()
    {
        return false;
    }

    public void setIsCritical(boolean critical)
    {
        byte b0 = this.dataManager.get(CRITICAL);

        if (critical)
        {
            this.dataManager.set(CRITICAL, (byte)(b0 | 1));
        }
        else
        {
            this.dataManager.set(CRITICAL, (byte)(b0 & -2));
        }
    }

    public static int registerSubsEntities(int id) {
        EntityRegistry.registerModEntity(EntityRocket.bullet, EntityRocket.class, "rocket", id++, RainbowSixSiege.instance, Minecraft.getMinecraft().gameSettings.renderDistanceChunks * 16, 1, false);
        EntityRegistry.registerModEntity(EntityBulletBase.bullet, EntityBulletBase.class, "bullet", id++, RainbowSixSiege.instance, Minecraft.getMinecraft().gameSettings.renderDistanceChunks * 16, 1, false);
        return id;
    }
}
