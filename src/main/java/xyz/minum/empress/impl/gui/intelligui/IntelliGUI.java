package xyz.minum.empress.impl.gui.intelligui;

import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.GL11;
import xyz.minum.empress.Empress;
import xyz.minum.empress.api.module.Module;
import xyz.minum.empress.api.utils.MathUtils;
import xyz.minum.empress.api.utils.render.GuiUtils;
import xyz.minum.empress.api.utils.render.ScissorStack;
import xyz.minum.empress.impl.modules.client.ClickGui;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;


public class IntelliGUI extends GuiScreen {

    Sidebar sidebar = new Sidebar(50,30,100,400);

    private CopyOnWriteArrayList<TabButton> tabs = new CopyOnWriteArrayList<>();
    private TabButton focusedTab;
    private SettingDisplay settingDisplay = new SettingDisplay(155,60,540,500);
    private ScissorStack scissorStack = new ScissorStack();


    @Override
    public void handleMouseInput() throws IOException {
        //settingDisplay.setScroll(Mouse.getEventDWheel());
        settingDisplay.handleMouseInput();
        super.handleMouseInput();
    }


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


        GL11.glPushMatrix();
        scissorStack.pushScissor(150,55,540,375);
        settingDisplay.draw(mouseX, mouseY, partialTicks);
        scissorStack.popScissor();
        GL11.glPopMatrix();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        sidebar.mouseClicked(mouseX, mouseY, mouseButton);

        for(TabButton tabButton : tabs){
            tabButton.mouseClicked(mouseX, mouseY, mouseButton);
        }

        settingDisplay.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        settingDisplay.keyTyped(typedChar, keyCode);
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
                if(tabs.isEmpty()){
                    setFocusedTab(null);
                }
                return;
            }
        }
    }

    public TabButton getTab(Module module){
        for(TabButton tabButton : tabs){
            if(tabButton.module == module){
                return tabButton;
            }
        }
        return null;
    }

    public boolean tabExist(Module module){
        for(TabButton tabButton : tabs){
            if(tabButton.module == module){
                return true;
            }
        }
        return false;
    }

    public void removeTab(TabButton tabButton){
        int i = tabs.indexOf(tabButton);
        tabs.remove(tabButton);
        if(tabs.isEmpty()){
            setFocusedTab(null);
        } else {
            setFocusedTab(tabs.get(MathUtils.true_mod(i-1, tabs.size())));
            Empress.logger.info("removing tab: " +String.valueOf(MathUtils.true_mod(i-1, tabs.size())) + " : " + String.valueOf(tabs.size()));
        }

    }

    public void addTab(Module module){
        if(tabExist(module)) return;
        TabButton tabButton = new TabButton(150+(tabs.size()*50), 30,70,20, module);
        tabs.add(tabButton);
        setFocusedTab(tabButton);

    }



    public void setFocusedTab(TabButton tabButton){
        focusedTab = tabButton;
        for(TabButton button : tabs){
            if(button != tabButton){
                button.focused = false;
            }
        }
        settingDisplay.updateScreen(tabButton);
        if(tabButton != null) {
            tabButton.focused = true;
        }

    }



}
