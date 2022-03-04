package xyz.minum.empress.core.mixins;


import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.minum.empress.api.event.events.PacketEvent;

@Mixin(value = NetworkManager.class)
public class MixinNetworkManager {

    private final Minecraft mc = Minecraft.getMinecraft();

    @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
    private void sendPacket(Packet<?> packet, CallbackInfo callbackInfo){
        if (mc.player == null) return;
        PacketEvent event = new PacketEvent(packet);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) callbackInfo.cancel();
    }

    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    private void onChannelRead(ChannelHandlerContext context, Packet<?> packet, CallbackInfo callbackInfo) {
        if (mc.player == null) return;
        PacketEvent event = new PacketEvent(packet);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            callbackInfo.cancel();
        }
    }
}
