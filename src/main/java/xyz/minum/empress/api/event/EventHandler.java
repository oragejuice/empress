package xyz.minum.empress.api.event;

import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import xyz.minum.empress.Empress;


public class EventHandler {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event){
        if (Keyboard.getEventKeyState() && Keyboard.getEventKey() != Keyboard.KEY_NONE) {
            Empress.INSTANCE.moduleManager.getModules().forEach(module -> {
                        if (Keyboard.getEventKey() == module.getBind()) {
                            module.toggle();
                        }
                    }
            );
        }
    }
}
