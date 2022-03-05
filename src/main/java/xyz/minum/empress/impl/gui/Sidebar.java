package xyz.minum.empress.impl.gui;

import xyz.minum.empress.Empress;
import xyz.minum.empress.api.module.Category;
import xyz.minum.empress.api.utils.render.GuiComponent;
import xyz.minum.empress.api.utils.render.GuiUtils;
import xyz.minum.empress.api.utils.render.font.FontUtil;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Sidebar extends GuiComponent {

    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    Date date = new Date();

    ArrayList<CategoryComponent> categoryComponents = new ArrayList<>();

    int yOffset = 5;

    public Sidebar(int x, int y, int width, int height) {
        super(x, y, width, height);

        int yOffset = 5;
        for(Category c : Category.values()){
            CategoryComponent component = new CategoryComponent(x+15, y+yOffset + 30, FontUtil.getStringWidth(c.toString()),FontUtil.getFontHeight(FontUtil.fonts.Helvetica), c);
            yOffset += FontUtil.getFontHeight(FontUtil.fonts.Helvetica) + component.subModulesHeight + 3;
            categoryComponents.add(component);
        }
    }

    public void draw(int mouseX, int mouseY, float partialTicks){
        GuiUtils.drawBox(x, y, width, height, new Color(60,63,65));

        GuiUtils.drawBox(x,y,width,20, new Color(49, 51, 53));

        FontUtil.drawString(Empress.MOD_ID + " - " + formatter.format(date),x+5,y+9, Color.WHITE.getRGB(), FontUtil.fonts.Helvetica);

        FontUtil.drawString(Empress.MOD_ID+"."+Empress.VERSION, x+7,y+24, Color.WHITE.getRGB(), FontUtil.fonts.Helvetica);
        GuiUtils.drawDownwardsArrow(x+3,y+25);


        int yOffset = 5;
        for(CategoryComponent component : categoryComponents){
            component.updatePosition(x+15, y+yOffset + 30);
            component.draw(mouseX, mouseY, partialTicks);
            yOffset += FontUtil.getFontHeight(FontUtil.fonts.Helvetica) + component.subModulesHeight + 3;
        }

    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for(CategoryComponent component : categoryComponents){
            component.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

}
