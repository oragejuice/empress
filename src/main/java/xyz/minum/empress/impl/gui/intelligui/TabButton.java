package xyz.minum.empress.impl.gui.intelligui;

import xyz.minum.empress.Empress;
import xyz.minum.empress.api.module.Module;
import xyz.minum.empress.api.utils.render.GuiComponent;
import xyz.minum.empress.api.utils.render.GuiUtils;
import xyz.minum.empress.api.utils.render.font.FontUtil;
import xyz.minum.empress.impl.modules.client.ClickGui;

import java.awt.*;

public class TabButton extends GuiComponent {

    Module module;
    boolean focused;
    XWidget xWidget;

    public TabButton(int x, int y, int width, int height, Module module) {
        super(x, y, width, height);
        this.module = module;
        xWidget = new XWidget(x+width-10,y+7);
        focused = true;
    }

    public void draw(int mouseX, int mouseY, float partialTicks) {
        int centerStringX = (width/2) - (FontUtil.getStringWidth(module.getName()) / 2);
        GuiUtils.drawBox(x,y,width,height, focused ? new Color(131, 128, 128) : new Color(43,43,43));
        FontUtil.drawString(module.getName(), x+centerStringX, y+6, Color.WHITE.getRGB(), FontUtil.fonts.Helvetica);
        xWidget.updatePosition(x+width-10, y+7);
        xWidget.draw();
        GuiUtils.drawBox(x,y+17,width,3, new Color(213, 213, 213));
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (inside(mouseX, mouseY)){
            if(mouseButton == 0 && xWidget.mouseClicked(mouseX, mouseY, mouseButton)) {
                Empress.INSTANCE.moduleManager.getModule(ClickGui.class).intelliGUI.removeTab(this);
            } else {
                focused = true;
                Empress.INSTANCE.moduleManager.getModule(ClickGui.class).intelliGUI.setFocusedTab(this);
            }
        }
    }

    public void update(int x, int y){
        this.x = x;
        this.y = y;

    }
}
