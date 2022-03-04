package xyz.minum.empress.impl.gui;

import xyz.minum.empress.Empress;
import xyz.minum.empress.api.module.Category;
import xyz.minum.empress.api.utils.render.GuiComponent;
import xyz.minum.empress.api.utils.render.GuiUtils;
import xyz.minum.empress.api.utils.render.font.FontUtil;

import java.awt.*;
import java.util.ArrayList;

public class Sidebar extends GuiComponent {

    ArrayList<CategoryComponent> categoryComponents = new ArrayList<>();

    int yOffset = 5;

    public Sidebar(int x, int y, int width, int height) {
        super(x, y, width, height);


        int yOffset = 5;
        for(Category c : Category.values()){
            //Empress.logger.info(c.toString());
            CategoryComponent component = new CategoryComponent(x+7, y+yOffset, FontUtil.getStringWidth(c.toString()),FontUtil.getFontHeight(), c);
            yOffset += FontUtil.getFontHeight() + component.subModulesHeight + 3;
            categoryComponents.add(component);
        }
        Empress.logger.info(categoryComponents.isEmpty());
    }

    public void draw(int mouseX, int mouseY, float partialTicks){
        GuiUtils.drawBox(x, y, width, height, new Color(60,63,65));

        int yOffset = 5;
        for(CategoryComponent component : categoryComponents){
            component.updatePosition(x+7, y+yOffset);
            component.draw(mouseX, mouseY, partialTicks);
            yOffset += FontUtil.getFontHeight() + component.subModulesHeight + 3;
        }

    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for(CategoryComponent component : categoryComponents){
            component.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

}
