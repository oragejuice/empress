package xyz.minum.empress.impl.gui.text;

import com.mojang.realmsclient.gui.ChatFormatting;
import xyz.minum.empress.Empress;
import xyz.minum.empress.api.setting.Setting;
import xyz.minum.empress.api.utils.render.TextGuiComponent;
import xyz.minum.empress.api.utils.render.font.FontUtil;

import java.awt.*;

public class BooleanSettingComponent extends TextGuiComponent {

    private Setting<Boolean> setting;
    String line;

    public BooleanSettingComponent(int x, int y, Setting<Boolean> setting) {
        super(x, y, 1);
        this.setting = setting;
    }


    @Override
    public void draw(int mouseX, int mouseY, float partialTicks){
        line = "§6private " +  "§3boolean " + ChatFormatting.WHITE + setting.getName() + " = " + ChatFormatting.GRAY + setting.getValue();
        FontUtil.drawString(line, x,y, new Color(204, 120, 50).getRGB(), FontUtil.fonts.JetBrains);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton){
        String start =  "§6private " +  "§3boolean " + ChatFormatting.WHITE + setting.getName() + " = ";
        if(inside(mouseX, mouseY, x+FontUtil.getStringWidth(start),y,FontUtil.getStringWidth(" "+setting.getValue()), FontUtil.getFontHeight(FontUtil.fonts.JetBrains) )){
            setting.setValue(!setting.getValue());
        }
    }
}
