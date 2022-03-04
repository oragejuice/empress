package xyz.minum.empress.impl.gui;

import xyz.minum.empress.Empress;
import xyz.minum.empress.api.module.Module;
import xyz.minum.empress.api.utils.render.GuiComponent;
import xyz.minum.empress.api.utils.render.font.FontUtil;

import java.awt.*;

public class ModuleButton extends GuiComponent {

    Module module;

    public ModuleButton(int x, int y, int width, int height, Module module) {
        super(x, y, width, height);
        this.module = module;
    }

    public void draw(int mouseX, int mouseY, float partialTicks){
        FontUtil.drawString(module.getName(), x, y, module.isEnabled() ? Color.WHITE.getRGB() : Color.LIGHT_GRAY.getRGB(), FontUtil.fonts.Helvetica);
    }


    public void mouseClicked(int mouseX, int mouseY, int mouseButton){
        if(inside(mouseX, mouseY)) module.toggle();
    }

    public void updatePosition(int x, int y){
        this.x = x;
        this.y = y;
    }
}
