package xyz.minum.empress.impl.modules.player;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import xyz.minum.empress.api.module.Category;
import xyz.minum.empress.api.module.Module;
import xyz.minum.empress.api.setting.Setting;
import xyz.minum.empress.api.utils.TimerUtil;

public class Timer extends Module {

    public static Setting<Double> timer = new Setting<>("timer", 1.3, 0.1, 10.0);

    public Timer() {
        super("timer", Category.player);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        TimerUtil.setTimer(timer.getValue().floatValue());
    }

    @Override
    public void onDisable() {
        TimerUtil.resetTimer();
        super.onDisable();
    }





}
