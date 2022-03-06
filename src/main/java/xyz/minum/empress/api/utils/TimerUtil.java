package xyz.minum.empress.api.utils;

import net.minecraft.client.Minecraft;
import xyz.minum.empress.api.Globals;

public class TimerUtil implements Globals {

    //private static final Minecraft mc = Minecraft.getMinecraft();

    public static void setTimer(float speed) {
        mc.timer.tickLength = 50.0f / speed;
    }

    public static void resetTimer() {
        mc.timer.tickLength = 50;
    }
}
