package xyz.minum.empress.impl.modules;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import xyz.minum.empress.api.module.Category;
import xyz.minum.empress.api.module.Module;
import xyz.minum.empress.api.setting.Setting;

public class TestModule extends Module {


    public static Setting<Boolean> testSetting = new Setting<>("testSetting", true);
    public static Setting<Boolean> testSetting2 = new Setting<>("testSetting2", true);
    public static Setting<Boolean> testSetting3 = new Setting<>("testSetting3", true);
    public static Setting<Boolean> testSetting4 = new Setting<>("testSetting4", true);
    public static Setting<testEnum> testEnumSetting1 = new Setting<>("testEnumSetting", testEnum.poop);
    public static Setting<Double> testDouble = new Setting<>("testDouble", 3.0,0.0,10.0);

    int i = 0;



    public TestModule() {
        super("TestModule", Category.client);
        this.setBind(Keyboard.KEY_P);
    }

    @SubscribeEvent
    public void onTick(TickEvent event){
        if(mc.player == null || mc.world == null) return;

        if(i >= 20){
            mc.player.sendChatMessage(testSetting.getValue().toString() + " - " + testDouble.getValue().toString() + " - " + testEnumSetting1.getValue().toString());
            i = 0;
        }
        i++;
    }

    @Override
    public void onEnable(){
        super.onEnable();
        mc.player.sendChatMessage("TestModule enabled!!!!");
    }

    public enum testEnum {
        poop, more, shit, fart
    }
}
