package fr.helios.rainbowsixsiege.utils;

public class Vec2
{
    public double x, z;

    public Vec2(double x, double z)
    {
        this.x = x;
        this.z = z;
    }

    public Vec2(Vec2 vec)
    {
        this.x = vec.x;
        this.z = vec.z;
    }
}
