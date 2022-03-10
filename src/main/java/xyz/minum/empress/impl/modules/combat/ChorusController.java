package xyz.minum.empress.impl.modules.combat;


import org.lwjgl.input.Keyboard;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import xyz.minum.empress.api.event.events.PacketEvent;
import xyz.minum.empress.api.module.Category;
import xyz.minum.empress.api.module.Module;

import java.util.LinkedList;
import java.util.Queue;


public class ChorusController extends Module {

    Queue<SPacketPlayerPosLook> tpPackets = new LinkedList<>();

    public ChorusController() {
        super("chorus_controller", Category.combat);
    }
    SPacketPlayerPosLook pak;
    boolean cancelling = true;

    @SubscribeEvent
    public void onPacket(PacketEvent event){

        if (event.getPacket() instanceof CPacketConfirmTeleport){
            //if (cancelling) confirmPackets.add((CPacketConfirmTeleport) event.getPacket());
            event.setCanceled(cancelling);
        }

        if (event.getPacket() instanceof SPacketPlayerPosLook){
            cancelling = true;
            tpPackets.add((SPacketPlayerPosLook) event.getPacket());
            pak = (SPacketPlayerPosLook) event.getPacket();
            event.setCanceled(cancelling);
        }

    }

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if (isMovementKey(Keyboard.getEventKey()) && pak != null && cancelling == true) {

            while(!tpPackets.isEmpty()){
                cancelling = false;
                mc.player.connection.handlePlayerPosLook(tpPackets.poll());
            }

            pak = null;
        }
    }


    private boolean isMovementKey(int key){
        return mc.gameSettings.keyBindForward.getKeyCode() == key ||
                mc.gameSettings.keyBindLeft.getKeyCode() == key ||
                mc.gameSettings.keyBindRight.getKeyCode() == key ||
                mc.gameSettings.keyBindBack.getKeyCode() == key ||
                mc.gameSettings.keyBindJump.getKeyCode() == key;
    }
}
