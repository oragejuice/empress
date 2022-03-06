package xyz.minum.empress.impl.modules;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import xyz.minum.empress.api.module.Category;
import xyz.minum.empress.api.module.Module;
import xyz.minum.empress.api.setting.Setting;
import xyz.minum.empress.impl.gui.glScissorTest.scissor;

public class TestModule extends Module {


    public static Setting<Boolean> testSetting = new Setting<>("testSetting", true);
    public static Setting<Boolean> testSetting2 = new Setting<>("testSetting2", true);
    public static Setting<Boolean> testSetting3 = new Setting<>("testSetting3", true);
    public static Setting<Double> testDouble4 = new Setting<>("testDouble4", 3.0,0.0,10.0);
    public static Setting<Boolean> testSetting4 = new Setting<>("testSetting4", true);
    public static Setting<Boolean> testSetting5 = new Setting<>("testSetting5", true);
    public static Setting<Double> testDouble3 = new Setting<>("testDouble3", 3.0,0.0,10.0);
    public static Setting<Boolean> testSetting6 = new Setting<>("testSetting6", true);
    public static Setting<Boolean> testSetting7 = new Setting<>("testSetting7", true);
    public static Setting<Double> testDouble5 = new Setting<>("testDouble5", 3.0,0.0,10.0);
    public static Setting<Double> testDouble6 = new Setting<>("testDouble6", 3.0,0.0,10.0);
    public static Setting<Boolean> testSetting8 = new Setting<>("testSetting8", true);
    public static Setting<Boolean> testSetting9 = new Setting<>("testSetting9", true);
    public static Setting<testEnum> testEnumSetting1 = new Setting<>("testEnumSetting", testEnum.poop);
    public static Setting<Double> testDouble = new Setting<>("testDouble", 3.0,0.0,10.0);
    public static Setting<Double> testDouble2 = new Setting<>("testDouble2", 3.0,0.0,10.0);


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
        //mc.displayGuiScreen(new scissor());
    }

    public enum testEnum {
        poop, more, shit, fart
    }
}
