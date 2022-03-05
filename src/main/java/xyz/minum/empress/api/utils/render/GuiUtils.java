package xyz.minum.empress.api.utils.render;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.GL_NICEST;
import static org.lwjgl.opengl.GL11.GL_POLYGON_SMOOTH_HINT;

public class GuiUtils {

    public static void drawBox(int x, int y, int width, int height, Color color){
        GuiScreen.drawRect(x,y,x+width, y+height, color.getRGB());
    }

    public static void drawBox(int x, int y, int width, int height, int color){
        GuiScreen.drawRect(x,y,x+width, y+height, color);
    }


    public static void draw2DCross(int x, int y){
        drawLine(x, y, x+3, y+3, Color.WHITE);
        drawLine(x, y+3, x+3, y, Color.WHITE);
    }

    public static void drawDownwardsArrow(int x, int y){
        drawLine(x-2, y-1, x, y+1, Color.WHITE);
        drawLine(x+2, y-1, x, y+1, Color.WHITE);
    }

    public static void drawSideArrow(int x, int y){
        drawLine(x-1, y-2, x+1, y, Color.WHITE);
        drawLine(x-1, y+2, x+1, y, Color.WHITE);
    }


    private static void drawLine(float x1, float y1, float x2, float y2) {
        enabledGL2D();
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2d(x1, y1);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        disabledGL2D();
    }

    public static void drawLine(float x1, float y1, float x2, float y2, Color color) {
        applyColor(color);
        drawLine(x1, y1, x2, y2);
    }

    private static void applyColor(Color color) {
        GL11.glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
    }

    public static void enabledGL2D() {
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glHint( GL_POLYGON_SMOOTH_HINT, GL_NICEST );
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableCull();
    }

    public static void disabledGL2D() {
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableCull();
        GlStateManager.color(1f, 1f, 1f,1f);
    }
}
