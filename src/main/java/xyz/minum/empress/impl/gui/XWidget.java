package xyz.minum.empress.impl.gui;

import xyz.minum.empress.Empress;
import xyz.minum.empress.api.utils.render.GuiComponent;
import xyz.minum.empress.api.utils.render.GuiUtils;
import xyz.minum.empress.impl.modules.ClickGui;

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
