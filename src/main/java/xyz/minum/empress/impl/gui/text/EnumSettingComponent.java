package xyz.minum.empress.impl.gui.text;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.apache.commons.lang3.EnumUtils;
import xyz.minum.empress.Empress;
import xyz.minum.empress.api.setting.Setting;
import xyz.minum.empress.api.utils.render.GuiUtils;
import xyz.minum.empress.api.utils.render.TextGuiComponent;
import xyz.minum.empress.api.utils.render.font.FontUtil;

import java.awt.*;
import java.util.Arrays;
import java.util.Locale;

public class EnumSettingComponent extends TextGuiComponent {

    Setting<Enum<?>> setting;
    String head;
    String tail = "§6}";
    String[] modes;


    public EnumSettingComponent(int x, int y, Setting<Enum<?>> setting) {
        super(x, y, 3);
        this.setting = setting;
        modes = getNames((Class<? extends Enum<?>>) setting.getValue().getClass());
    }


    @Override
    public void draw(int mouseX, int mouseY, float partialTicks){

        modes = getNames((Class<? extends Enum<?>>) setting.getValue().getClass());


        head = "§6private " + "§4enum §f" +  setting.getName() + " {";
        FontUtil.drawString(head, x, y, Color.WHITE.getRGB());
        FontUtil.drawString("   " + String.join(", ", modes), x, y+FontUtil.getFontHeight(FontUtil.fonts.JetBrains)+3, Color.WHITE.getRGB());
        FontUtil.drawString(tail, x, y+FontUtil.getFontHeight(FontUtil.fonts.JetBrains)*2+3, Color.WHITE.getRGB());


    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton){
        int startX = x + FontUtil.getStringWidth("  ");
        for(String mode : modes){
            if(inside(mouseX, mouseY, startX, y+FontUtil.getFontHeight(FontUtil.fonts.JetBrains)+3, FontUtil.getStringWidth(mode+", "), FontUtil.getFontHeight(FontUtil.fonts.JetBrains))){
                setting.setValue(Enum.valueOf(setting.getValue().getClass(), mode.toLowerCase()));
            }
            startX += FontUtil.getStringWidth(mode + ", ");
        }

    }

    public String[] getNames(Class<? extends Enum<?>> e) {
        String[] ret = Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);

        for (int i = 0; i < ret.length; i++) {
                if(setting.getValue().toString().equals(ret[i])){
                    ret[i] = ret[i].toUpperCase(Locale.ROOT);
                }
        }
        return ret;
    }



}
