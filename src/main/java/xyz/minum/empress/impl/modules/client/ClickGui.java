package xyz.minum.empress.impl.modules.client;

import org.lwjgl.input.Keyboard;
import xyz.minum.empress.api.module.Category;
import xyz.minum.empress.api.module.Module;
import xyz.minum.empress.impl.gui.intelligui.IntelliGUI;

public class ClickGui extends Module {

    public IntelliGUI intelliGUI;

    public ClickGui() {
        super("ClickGui", Category.client);
        this.setBind(Keyboard.KEY_RSHIFT);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if(intelliGUI == null) intelliGUI = new IntelliGUI();
        mc.displayGuiScreen(intelliGUI);
    }

    @Override
    public void onDisable(){
        super.onDisable();

    }
}
