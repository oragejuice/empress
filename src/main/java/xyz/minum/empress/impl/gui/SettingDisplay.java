package xyz.minum.empress.impl.gui;

import xyz.minum.empress.api.module.Module;
import xyz.minum.empress.api.setting.Setting;
import xyz.minum.empress.api.utils.render.GuiComponent;
import xyz.minum.empress.api.utils.render.font.FontUtil;

public class SettingDisplay extends GuiComponent {

    public Module module;

    public SettingDisplay(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void draw(){
        if(module == null){
            //TODO explain shit
            return;
        }
        int yOffset = 0;
        for(Setting<?> setting : module.getSettings()){
            FontUtil.drawString(setting.getName(),x+3,y+yOffset+1, 0xFFFFFF);
            yOffset += FontUtil.getFontHeight(FontUtil.fonts.Helvetica);
        }
    }

    public void updateScreen(Module module){
        this.module = module;
    }
}
