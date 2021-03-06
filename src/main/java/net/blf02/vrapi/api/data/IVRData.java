package net.blf02.vrapi.api.data;

import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * IVRData Interface.
 *
 * This interface contains information about a given object tracked in VR (the HMD or a controller).
 */
public interface IVRData {

    /**
     * Get the direction of the object
     * @return A vector representing where the object is pointing on the x, y, and z axis.
     */
    public Vector3d getLookAngle();

    /**
     * Get the position of the object in Minecraft
     * @return A Vector3d representing the object's location in Minecraft
     */
    public Vector3d position();

    /**
     * Returns the roll of the object in degrees.
     * @return Object roll in degrees.
     */
    public float getRoll();

    /**
     * Returns the pitch of the object in degrees.
     * @return Object pitch in degrees.
     */
    public float getPitch();

    /**
     * Returns the yaw of the object in degrees.
     * @return Object yaw in degrees.
     */
    public float getYaw();

    /**
     * Gets the rotation matrix of the object.
     * Only on the client!
     * @return The Matrix4f representing the rotation of the object
     */
    @OnlyIn(Dist.CLIENT)
    public Matrix4f getRotationMatrix();
}
