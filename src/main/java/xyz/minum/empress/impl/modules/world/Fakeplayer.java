package xyz.minum.empress.impl.modules.world;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import xyz.minum.empress.Empress;
import xyz.minum.empress.api.module.Category;
import xyz.minum.empress.api.module.Module;

import java.util.UUID;

public class Fakeplayer extends Module {


    public Fakeplayer() {
        super("fakeplayer", Category.world);
    }

    private EntityOtherPlayerMP fakePlayer;

    @Override
    public void onEnable() {
        String s = "Empress" + Minecraft.getSystemTime() % 1000L;
        fakePlayer = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.randomUUID(), s));
        fakePlayer.copyLocationAndAnglesFrom(mc.player);
        fakePlayer.rotationYawHead = mc.player.rotationYawHead;
        fakePlayer.inventory = mc.player.inventory;
        mc.world.addEntityToWorld(-1, fakePlayer);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        try {
            mc.world.removeEntity(fakePlayer);
        } catch (NullPointerException e) {
            Empress.logger.info("Failed to remove FakePlayer" + fakePlayer.getName());
        }
        super.onDisable();
    }
}
