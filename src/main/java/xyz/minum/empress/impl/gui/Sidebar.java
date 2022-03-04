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

    ArrayList<CategoryComponent> categoryComponents = new ArrayList<>();

    int yOffset = 5;

    public Sidebar(int x, int y, int width, int height) {
        super(x, y, width, height);

        int yOffset = 5;
        for(Category c : Category.values()){
            //Empress.logger.info(c.toString());
            CategoryComponent component = new CategoryComponent(x+15, y+yOffset + 30, FontUtil.getStringWidth(c.toString()),FontUtil.getFontHeight(), c);
            yOffset += FontUtil.getFontHeight() + component.subModulesHeight + 3;
            categoryComponents.add(component);
        }
        Empress.logger.info(categoryComponents.isEmpty());
    }

    public void draw(int mouseX, int mouseY, float partialTicks){
        GuiUtils.drawBox(x, y, width, height, new Color(60,63,65));

        GuiUtils.drawBox(x,y,width,20, new Color(49, 51, 53));
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        FontUtil.drawString(Empress.MOD_ID + " - " + formatter.format(date),x+4,y+9, Color.WHITE.getRGB(), FontUtil.fonts.Helvetica);

        FontUtil.drawString(Empress.MOD_ID+"."+Empress.VERSION, x+7,y+24, Color.WHITE.getRGB(), FontUtil.fonts.Helvetica);
        GuiUtils.drawDownwardsArrow(x+3,y+27);


        int yOffset = 5;
        for(CategoryComponent component : categoryComponents){
            component.updatePosition(x+15, y+yOffset + 30);
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
