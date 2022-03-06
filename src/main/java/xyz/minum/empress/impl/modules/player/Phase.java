package xyz.minum.empress.impl.modules.player;

import net.minecraft.block.BlockAir;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.minum.empress.api.event.events.PacketEvent;
import xyz.minum.empress.api.module.Category;
import xyz.minum.empress.api.module.Module;
import xyz.minum.empress.api.setting.Setting;
import xyz.minum.empress.api.utils.MovementUtil;

public class Phase extends Module {

    public static Setting<mode> modeSetting = new Setting<>("mode", mode.zero);
    public static Setting<Double> maxY = new Setting<>("maxy", 3.0,-10.0,10.0);
    public static Setting<Double> hSpeed = new Setting<>("hspeed", 22.0,0.0,100.0);
    public static Setting<Double> vSpeed = new Setting<>("vspeed", 52.0,0.0,100.0);
    public static Setting<Double> pSpeed = new Setting<>("pspeed", 6.5,0.0,100.0);
    public static Setting<Boolean> fakeFly = new Setting<>("fakefly", true);


    public Phase() {
        super("phase", Category.player);
    }

    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent event) {
        if (!(event.getEntity() instanceof EntityPlayerSP) || mc.player == null) return;

        if (modeSetting.getValue() == mode.zero) {
            if (mc.player.posY <= maxY.getValue()) {
                if (mc.player.posY >= 1)
                    mc.player.setPosition(mc.player.posX, mc.player.posY + (0 - mc.player.posY), mc.player.posZ);
                else mc.player.motionY = 0;
            }
        }

        //Credits to Noil, oHare, 0x22, LinusTouchTips
        if (modeSetting.getValue() == mode.packet) {
            final double[] dirSpeed;
            if (mc.world.getBlockState(mc.player.getPosition()).getBlock() instanceof BlockAir && mc.world.getBlockState(mc.player.getPosition().add(0, 1, 0)).getBlock() instanceof BlockAir) dirSpeed = MovementUtil.directionSpeed(hSpeed.getValue()/100f);
            else dirSpeed = MovementUtil.directionSpeed(pSpeed.getValue()/100f);
            mc.player.connection.sendPacket(new CPacketPlayer.PositionRotation(mc.player.posX + dirSpeed[0], mc.player.posY + (mc.gameSettings.keyBindJump.isKeyDown() ? vSpeed.getValue()/1000f : 0.00000001) - (mc.gameSettings.keyBindSneak.isKeyDown() ? vSpeed.getValue()/1000f : 0.00000002), mc.player.posZ + dirSpeed[1], mc.player.rotationYaw, mc.player.rotationPitch, false));
            mc.player.connection.sendPacket(new CPacketPlayer.PositionRotation(mc.player.posX, -1337, mc.player.posZ, mc.player.rotationYaw, mc.player.rotationPitch, mc.player.onGround));
            if (fakeFly.getValue()) mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
            mc.player.setVelocity(0, 0,0);
            mc.player.noClip = true;

        }
    }

    @SubscribeEvent
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof SPacketPlayerPosLook) {
            SPacketPlayerPosLook packet = (SPacketPlayerPosLook) event.getPacket();
            packet.yaw = mc.player.rotationYaw;
            packet.pitch = mc.player.rotationPitch;
        }
    }




    public enum mode {
        zero, packet
    }
}
