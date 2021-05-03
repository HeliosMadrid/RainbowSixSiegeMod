package fr.helios.rainbowsixsiege.utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class Vec3
{
    public double x;
    public double y;
    public double z;

    public Vec3(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3(Vec3d vec3d)
    {
        this(vec3d.x, vec3d.y, vec3d.z);
    }

    public static Vec3 getForward(Entity entity) {
        double cosY = Math.cos(Math.toRadians(entity.rotationYaw));
        double sinY = Math.sin(Math.toRadians(entity.rotationYaw));
        double cosP = Math.cos(Math.toRadians(entity.rotationPitch));
        double sinP = Math.sin(Math.toRadians(entity.rotationPitch));

        double x = -sinY * cosP;
        double y = -sinP;
        double z = cosP * cosY;

        return new Vec3(x, y, z);
    }

    public double getYaw() {
        return Math.toDegrees(Math.atan2(this.x, this.z));
    }

    public double getPitch() {
        return Math.toDegrees(Math.atan2(this.y, Math.sqrt(x * x + z * z)));
    }

    public void normalize() {
        double lenght = getLenght();
        this.x /= lenght;
        this.y /= lenght;
        this.z /= lenght;
    }

    public void multiply(double factor) {
        this.x *= factor;
        this.y *= factor;
        this.z *= factor;
    }

    public Vec3d toVec3d() {
        return new Vec3d(this.x, this.y, this.z);
    }

    public Vec3 add(double x, double y, double z) {
        return new Vec3(this.x + x, this.y + y, this.z + z);
    }

    public double getLenght() {
        return MathHelper.sqrt(x * x + y * y + z * z);
    }
}
