package xyz.minum.empress.impl.gui.glScissorTest;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.GL11;
import xyz.minum.empress.api.utils.render.ScissorStack;

import java.awt.*;

public class scissor extends GuiScreen {


    ScissorStack scissorStack = new ScissorStack();

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){

        Gui.drawRect(50,50,150,150,Color.BLUE.getRGB());

        GL11.glPushMatrix();
        scissorStack.pushScissor(100,100,100,100);

        Gui.drawRect(150,150,250,250, Color.WHITE.getRGB());

        scissorStack.popScissor();
        GL11.glPopMatrix();
    }
}
