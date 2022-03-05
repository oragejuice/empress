package xyz.minum.empress.api.utils.render;

import xyz.minum.empress.api.utils.render.font.FontUtil;

import java.io.IOException;

public abstract class TextGuiComponent {


    protected int x;
    protected int y;
    protected final int lines;

    public TextGuiComponent(int x, int y, int lines){
        this.x = x;
        this.y = y;
        this.lines = lines;
    }

    public void draw(int mouseX, int mouseY, float partialTicks){}

    public void mouseClicked(int mouseX, int mouseY, int mouseButton){}

    public void mouseReleased(int mouseX, int mouseY, int state){}

    public void updatePosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void keyTyped(char typedChar, int keyCode) throws IOException{}

        public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLines() {
        return lines;
    }

    public int getHeight(){
        return lines * (FontUtil.getFontHeight(FontUtil.fonts.JetBrains)+1);
    }

    protected boolean inside(int posX, int posY, int x, int y, int width, int height) {
        return posX > x && posY > y && posX < x + width && posY < y + height;
    }




}
