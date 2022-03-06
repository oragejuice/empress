package xyz.minum.empress.impl.gui;

import xyz.minum.empress.api.module.Module;
import xyz.minum.empress.api.setting.Setting;
import xyz.minum.empress.api.utils.render.GuiComponent;
import xyz.minum.empress.api.utils.render.TextGuiComponent;
import xyz.minum.empress.api.utils.render.font.FontUtil;
import xyz.minum.empress.impl.gui.text.BooleanSettingComponent;
import xyz.minum.empress.impl.gui.text.EnumSettingComponent;
import xyz.minum.empress.impl.gui.text.HeaderComponent;
import xyz.minum.empress.impl.gui.text.NumericalSettingComponent;

import java.io.IOException;
import java.util.ArrayList;

public class SettingDisplay extends GuiComponent {

    public Module module;
    ArrayList<TextGuiComponent> settingComponents = new ArrayList<>();

    public SettingDisplay(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void draw(int mouseX, int mouseY, float partialTicks){
        if(module == null || settingComponents.isEmpty()) return;
        for(TextGuiComponent component : settingComponents){
            component.draw(mouseX, mouseY, partialTicks);
        }

    }

    public void keyTyped(char typedChar, int keyCode) throws IOException {
        for(TextGuiComponent component : settingComponents){
            component.keyTyped(typedChar, keyCode);
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if(module == null || settingComponents.isEmpty()) return;
        for(TextGuiComponent component : settingComponents){
            component.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }



    public void updateScreen(TabButton tabButton){
        if(tabButton == null){
            this.module = null;
        } else{
            this.module = tabButton.module;
            settingComponents.clear();
            settingComponents.add( new HeaderComponent(this.x, this.y, module));
            int yOffset = settingComponents.get(0).getLines()* FontUtil.getFontHeight(FontUtil.fonts.JetBrains) + 9;
            for(Setting<?> setting : module.getSettings()){

                if(setting.getValue() instanceof  Boolean){
                    BooleanSettingComponent b = new BooleanSettingComponent(this.x, this.y+yOffset, (Setting<Boolean>) setting);
                    settingComponents.add(b);
                    yOffset += b.getLines()*FontUtil.getFontHeight(FontUtil.fonts.JetBrains) + FontUtil.getFontHeight(FontUtil.fonts.JetBrains);
                }

                else if(setting.getValue() instanceof Enum<?>){
                    EnumSettingComponent b = new EnumSettingComponent(this.x, this.y+yOffset, (Setting<Enum<?>>) setting);
                    settingComponents.add(b);
                    yOffset += b.getLines()*FontUtil.getFontHeight(FontUtil.fonts.JetBrains) + FontUtil.getFontHeight(FontUtil.fonts.JetBrains);
                }

                else if(setting.getValue() instanceof Double){
                    NumericalSettingComponent b = new NumericalSettingComponent(this.x, this.y+yOffset, (Setting<Double>) setting);
                    settingComponents.add(b);
                    yOffset += b.getLines()*FontUtil.getFontHeight(FontUtil.fonts.JetBrains) + FontUtil.getFontHeight(FontUtil.fonts.JetBrains);
                }

            }
        }
    }
}
