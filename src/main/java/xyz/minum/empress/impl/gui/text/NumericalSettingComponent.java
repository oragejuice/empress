package xyz.minum.empress.impl.gui.text;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.lwjgl.input.Keyboard;
import xyz.minum.empress.Empress;
import xyz.minum.empress.api.setting.Setting;
import xyz.minum.empress.api.utils.render.TextGuiComponent;
import xyz.minum.empress.api.utils.render.font.FontUtil;

import java.awt.*;
import java.io.IOException;

public class NumericalSettingComponent extends TextGuiComponent {

    private Setting<Double> setting;
    String line;
    boolean capturing;
    String stringValue;

    public NumericalSettingComponent(int x, int y, Setting<Double> setting) {
        super(x, y, 1);
        this.setting = setting;
        this.stringValue = String.valueOf(setting.getValue());
    }


    @Override
    public void draw(int mouseX, int mouseY, float partialTicks){

        if(!capturing) stringValue = String.valueOf(setting.getValue());

        //String start =  "§6private " +  "§3double " + ChatFormatting.WHITE + setting.getName() + " = ";
        //GuiUtils.drawBox(x+FontUtil.getStringWidth(start),y,FontUtil.getStringWidth(" "+setting.getValue()), FontUtil.getFontHeight(FontUtil.fonts.JetBrains), Color.BLUE.getRGB());

        line = "§6private " +  "§3double " + ChatFormatting.WHITE + setting.getName() + " = " + ChatFormatting.GRAY + stringValue;
        FontUtil.drawString(line, x,y, new Color(204, 120, 50).getRGB(), FontUtil.fonts.JetBrains);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        if(capturing){
            if(keyCode == Keyboard.KEY_RETURN || keyCode == Keyboard.KEY_ESCAPE){
                if (!isNumeric(stringValue)) {
                    stringValue = setting.getValue().toString();
                    return;
                }
                Double s = Double.valueOf(stringValue);
                setting.setValue(s);
            } else if(keyCode == Keyboard.KEY_BACK){
                if(stringValue.isEmpty()) return;
                stringValue = stringValue.substring(0,stringValue.length()-1);
            }
            else if(Character.isDigit(typedChar) || ((Character) typedChar).equals('.')) {
                Empress.logger.info("key pressed" + typedChar);
                stringValue += typedChar;
            }
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton){


        String start =  "§6private " +  "§double " + ChatFormatting.WHITE + setting.getName() + " = ";
        if(inside(mouseX, mouseY, x+FontUtil.getStringWidth(start),y,FontUtil.getStringWidth(" "+setting.getValue()), FontUtil.getFontHeight(FontUtil.fonts.JetBrains) )){
            capturing = true;
        } else {
            capturing = false;
        }

        Empress.logger.info("capturing" + capturing);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
