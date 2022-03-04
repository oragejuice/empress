package xyz.minum.empress.api.utils.render;

public class GuiComponent {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public GuiComponent(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean inside(int posX, int posY) {
        return posX > x && posY > y && posX < x + width && posY < y + height;
    }
}
