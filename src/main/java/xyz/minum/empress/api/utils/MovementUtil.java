package xyz.minum.empress.api.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.potion.Potion;

public class MovementUtil {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static boolean isMoving() {
        return (mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown()) && !(mc.player.isSneaking());
    }

    public static double[] directionSpeed(double speed) {
        final Minecraft mc = Minecraft.getMinecraft();
        float forward = mc.player.movementInput.moveForward;
        float side = mc.player.movementInput.moveStrafe;
        float yaw = mc.player.prevRotationYaw + (mc.player.rotationYaw - mc.player.prevRotationYaw) * mc.getRenderPartialTicks();

        if (forward != 0)
        {
            if (side > 0)
            {
                yaw += (forward > 0 ? -45 : 45);
            }
            else if (side < 0)
            {
                yaw += (forward > 0 ? 45 : -45);
            }
            side = 0;

            // forward = clamp(forward, 0, 1);
            if (forward > 0)
            {
                forward = 1;
            }
            else if (forward < 0)
            {
                forward = -1;
            }
        }

        final double sin = Math.sin(Math.toRadians(yaw + 90));
        final double cos = Math.cos(Math.toRadians(yaw + 90));
        final double posX = (forward * speed * cos + side * speed * sin);
        final double posZ = (forward * speed * sin - side * speed * cos);
        return new double[] {posX, posZ};
    }

    public static void setSpeed(final double speed) {
        double[] dir = directionSpeed(speed);
        mc.player.motionX = dir[0];
        mc.player.motionZ = dir[1];
    }

    public static void setSpeed(Entity entity, final double speed) {
        double[] dir = directionSpeed(speed);
        entity.motionX = dir[0];
        entity.motionZ = dir[1];
    }

    @SuppressWarnings("ConstantConditions")
    public static double getBaseMoveSpeed() {
        double baseSpeed = 0.2873f;
        if (mc.player != null && mc.player.isPotionActive(Potion.getPotionById(1))) {
            final int amplifier = mc.player.getActivePotionEffect(Potion.getPotionById(1)).getAmplifier();
            baseSpeed *= 1.0 + 0.2 * (amplifier + 1);
        }
        return baseSpeed;
    }

}
