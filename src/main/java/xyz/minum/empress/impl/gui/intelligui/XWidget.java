package xyz.minum.empress.impl.gui.intelligui;

import xyz.minum.empress.api.utils.render.GuiComponent;
import xyz.minum.empress.api.utils.render.GuiUtils;

public class XWidget extends GuiComponent {

    public XWidget(int x, int y) {
        super(x, y, 6, 6);
    }

    public void draw(){
        GuiUtils.draw2DCross(this.x, this.y);
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (inside(mouseX, mouseY)){
            return true;
        }
        return  false;
    }

    public void updatePosition(int x, int y){
        this.x = x;
        this.y = y;
    }
}
