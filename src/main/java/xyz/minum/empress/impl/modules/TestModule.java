package xyz.minum.empress.impl.modules;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import xyz.minum.empress.api.module.Category;
import xyz.minum.empress.api.module.Module;
import xyz.minum.empress.api.setting.Setting;

public class TestModule extends Module {


    static Setting<Boolean> testSetting = new Setting<>("testSetting", true);

    public TestModule() {
        super("TestModule", Category.client);
        this.setBind(Keyboard.KEY_P);
    }

    @SubscribeEvent
    public void onTick(TickEvent event){
        if(mc.player == null || mc.world == null) return;
    }

    @Override
    public void onEnable(){
        super.onEnable();
        mc.player.sendChatMessage("TestModule enabled!!!!");
    }
}
