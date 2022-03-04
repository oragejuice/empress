package xyz.minum.empress.api.utils.render.font;

import java.io.InputStream;

public class FontUtil {

    private static FontRenderer globalFont = new FontRenderer(getFont("JetBrainsMono-Regular", 35));

    public static void drawString(String text, float x, float y, int color){
        if(globalFont == null){
            globalFont = new FontRenderer(getFont("JetBrainsMono-Regular", 35));
        }
        globalFont.drawString(text, x, y, color, false);
    }

    public static int getFontHeight(){
        return  globalFont.FONT_HEIGHT;
    }

    public static int getStringWidth(String text) {
            return globalFont.getStringWidth(text);
    }


    public static java.awt.Font getFont(String fontName, float size) {
        try {
            InputStream inputStream = FontUtil.class.getResourceAsStream("/assets/" + fontName + ".ttf");
            assert inputStream != null;
            java.awt.Font awtClientFont = java.awt.Font.createFont(0, inputStream);
            awtClientFont = awtClientFont.deriveFont(java.awt.Font.PLAIN, size);
            inputStream.close();

            return awtClientFont;
        } catch (Exception exception) {
            exception.printStackTrace();
            return new java.awt.Font("default", java.awt.Font.PLAIN, (int) size);
        }
    }
}
