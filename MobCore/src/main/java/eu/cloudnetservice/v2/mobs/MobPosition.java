package eu.cloudnetservice.v2.mobs;

public class MobPosition {

    private String group;

    private String world;

    private double x;

    private double y;

    private double z;

    private float yaw;

    private float pitch;

    public MobPosition(String group, String world, double x, double y, double z, float yaw, float pitch) {
        this.group = group;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public String getGroup() {
        return group;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public String getWorld() {
        return world;
    }

}
