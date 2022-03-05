package xyz.minum.empress.impl.gui;

import net.minecraft.client.gui.GuiScreen;
import xyz.minum.empress.Empress;
import xyz.minum.empress.api.module.Module;
import xyz.minum.empress.api.utils.render.GuiUtils;
import xyz.minum.empress.impl.modules.client.ClickGui;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


public class inteliiGUI extends GuiScreen {

    Sidebar sidebar = new Sidebar(50,30,100,400);

    private CopyOnWriteArrayList<TabButton> tabs = new CopyOnWriteArrayList<>();


    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GuiUtils.drawBox(150,30,540,400, new Color(43,43,43));
        GuiUtils.drawBox(150,30,540,20, new Color(60,63,65));
        sidebar.draw(mouseX,mouseY,partialTicks);

        int xOffset = 0;
        for(TabButton tabButton : tabs){
            tabButton.update(150+xOffset,30);
            tabButton.draw(mouseX, mouseY, partialTicks);
            xOffset += 70;
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        sidebar.mouseClicked(mouseX, mouseY, mouseButton);

        for(TabButton tabButton : tabs){
            tabButton.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        Empress.INSTANCE.moduleManager.getModule(ClickGui.class).toggle();
    }

    public void removeTab(Module module){
        for(TabButton tabButton : tabs){
            if(tabButton.module == module){
                tabs.remove(tabButton);
                return;
            }
        }
    }

    public void removeTab(TabButton tabButton){
        tabs.remove(tabButton);
    }

    public void addTab(Module module){
        tabs.add(new TabButton(150+(tabs.size()*50), 30,70,20, module));
    }



}
