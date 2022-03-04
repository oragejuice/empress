package xyz.minum.empress.api.utils.render;

import net.minecraft.client.gui.GuiScreen;

import java.awt.*;

public class GuiUtils {

    public static void drawBox(int x, int y, int width, int height, Color color){
        GuiScreen.drawRect(x,y,x+width, y+height, color.getRGB());
    }

    public static void drawBox(int x, int y, int width, int height, int color){
        GuiScreen.drawRect(x,y,x+width, y+height, color);
    }
}
