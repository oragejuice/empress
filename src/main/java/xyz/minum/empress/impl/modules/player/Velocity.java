package xyz.minum.empress.impl.modules.player;

import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.minum.empress.api.event.events.PacketEvent;
import xyz.minum.empress.api.module.Category;
import xyz.minum.empress.api.module.Module;
import xyz.minum.empress.api.setting.Setting;

public class Velocity extends Module {

    public static Setting<Double> multplier = new Setting<>("Multplier", 0D,0D,100D);

    public Velocity() {
        super("Velocity", Category.player);
    }

    @SubscribeEvent
    public void onPacket(PacketEvent event) {
        if (mc.player == null) {
            return;
        }


        if (event.getPacket() instanceof SPacketExplosion) {
            SPacketExplosion packet = (SPacketExplosion) event.getPacket();
            packet.motionX *= multplier.getValue();
            packet.motionY *= multplier.getValue();
            packet.motionZ *= multplier.getValue();
        }

        if (event.getPacket() instanceof SPacketEntityVelocity) {
            SPacketEntityVelocity packet = (SPacketEntityVelocity) event.getPacket();
            if (packet.getEntityID() == mc.player.getEntityId()) {
                packet.motionX *= multplier.getValue();
                packet.motionY *= multplier.getValue();
                packet.motionZ *= multplier.getValue();
            }
        }
    }
}
