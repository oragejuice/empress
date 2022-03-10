package xyz.minum.empress.api.utils.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;
import xyz.minum.empress.Empress;
import xyz.minum.empress.api.Globals;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class GuiUtils implements Globals {

    protected static float zLevel;

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

    public static void drawVerticalGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {

        float f = (float)(startColor >> 24 & 255) / 255.0F;
        float f1 = (float)(startColor >> 16 & 255) / 255.0F;
        float f2 = (float)(startColor >> 8 & 255) / 255.0F;
        float f3 = (float)(startColor & 255) / 255.0F;
        float f4 = (float)(endColor >> 24 & 255) / 255.0F;
        float f5 = (float)(endColor >> 16 & 255) / 255.0F;
        float f6 = (float)(endColor >> 8 & 255) / 255.0F;
        float f7 = (float)(endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right, (double)top, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)top, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)bottom, (double)zLevel).color(f5, f6, f7, f4).endVertex();
        bufferbuilder.pos((double)right, (double)bottom, (double)zLevel).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void drawHorizontalGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {

        float f = (float)(startColor >> 24 & 255) / 255.0F;
        float f1 = (float)(startColor >> 16 & 255) / 255.0F;
        float f2 = (float)(startColor >> 8 & 255) / 255.0F;
        float f3 = (float)(startColor & 255) / 255.0F;
        float f4 = (float)(endColor >> 24 & 255) / 255.0F;
        float f5 = (float)(endColor >> 16 & 255) / 255.0F;
        float f6 = (float)(endColor >> 8 & 255) / 255.0F;
        float f7 = (float)(endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right, (double)top, (double)zLevel).color(f5, f6, f7, f4).endVertex();
        bufferbuilder.pos((double)left, (double)top, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)bottom, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)right, (double)bottom, (double)zLevel).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

}
