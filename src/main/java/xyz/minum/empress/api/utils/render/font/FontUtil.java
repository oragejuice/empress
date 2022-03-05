package xyz.minum.empress.api.utils.render.font;

import java.io.InputStream;

public class FontUtil {

    private static FontRenderer globalFont = new FontRenderer(getFont("JetBrainsMono-Regular", 30));
    private static FontRenderer fontRenderer = new FontRenderer(getFont("Helvetica", 35));


    public static void drawString(String text, float x, float y, int color){
        if(globalFont == null){
            globalFont = new FontRenderer(getFont("JetBrainsMono-Regular", 30));
        }
        globalFont.drawString(text, x, y, color, false);
    }

    public static void drawString(String text, float x, float y, int color, fonts font){
        if(font.equals(fonts.JetBrains)){
            globalFont.drawString(text, x, y, color, false);
        } else if (font.equals(fonts.Helvetica)){
            fontRenderer.drawString(text, x, y, color, false);
        }
    }



    public static int getFontHeight(fonts font){
        if(font == fonts.JetBrains){
            return  globalFont.FONT_HEIGHT;
        } else if (font == fonts.Helvetica){
            return fontRenderer.FONT_HEIGHT;
        }
        else {
            return  globalFont.FONT_HEIGHT;
        }
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

    public enum fonts{
        JetBrains,
        Helvetica
    }
}
