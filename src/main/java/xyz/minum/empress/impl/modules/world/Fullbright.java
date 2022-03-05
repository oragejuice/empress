package xyz.minum.empress.impl.modules.world;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.minum.empress.api.module.Category;
import xyz.minum.empress.api.module.Module;

import java.util.Arrays;

public class Fullbright extends Module {

    public Fullbright() {
        super("fullbright", Category.world, "makes your world brigh!");
    }

    @SubscribeEvent
    public void onWorldRender(RenderWorldLastEvent event) {
        Arrays.fill(mc.world.provider.getLightBrightnessTable(), 1.0F);
    }

    @Override
    public void onDisable() {
        float[] table = mc.world.provider.getLightBrightnessTable();
        for (int i = 0; i <= 15; ++i) {
            float f1 = 1.0F - (float)i / 15.0F;
            table[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) + 0.0F;
        }
        super.onDisable();
    }


}
