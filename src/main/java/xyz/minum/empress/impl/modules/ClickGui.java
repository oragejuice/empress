package xyz.minum.empress.impl.modules;

import org.lwjgl.input.Keyboard;
import xyz.minum.empress.api.module.Category;
import xyz.minum.empress.api.module.Module;
import xyz.minum.empress.impl.gui.inteliiGUI;

public class ClickGui extends Module {

    public ClickGui() {
        super("ClickGui", Category.client);
        this.setBind(Keyboard.KEY_SEMICOLON);
    }

    @Override
    public void onEnable(){
        super.onEnable();
        mc.displayGuiScreen(new inteliiGUI());
    }

    @Override
    public void onDisable(){
        super.onDisable();

    }
}
