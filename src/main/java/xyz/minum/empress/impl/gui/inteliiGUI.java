package xyz.minum.empress.impl.gui;

import net.minecraft.client.gui.GuiScreen;
import xyz.minum.empress.api.utils.render.GuiUtils;

import java.awt.*;
import java.io.IOException;

public class inteliiGUI extends GuiScreen {

    Sidebar sidebar = new Sidebar(50,30,100,400);

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GuiUtils.drawBox(150,30,540,400, new Color(43,43,43));
        sidebar.draw(mouseX,mouseY,partialTicks);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        sidebar.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {

        super.keyTyped(typedChar, keyCode);
    }

}
