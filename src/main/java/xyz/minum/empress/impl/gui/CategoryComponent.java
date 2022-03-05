package xyz.minum.empress.impl.gui;

import xyz.minum.empress.Empress;
import xyz.minum.empress.api.module.Category;
import xyz.minum.empress.api.module.Module;
import xyz.minum.empress.api.utils.render.GuiComponent;
import xyz.minum.empress.api.utils.render.GuiUtils;
import xyz.minum.empress.api.utils.render.font.FontUtil;

import java.awt.*;
import java.util.ArrayList;

public class CategoryComponent extends GuiComponent {

    Category category;
    boolean visible;
    public int subModulesHeight = 0;

    ArrayList<ModuleButton> moduleButtons = new ArrayList<>();

    public CategoryComponent(int x, int y, int width, int height, Category category) {
        super(x, y, width, height);
        this.category = category;

        for(Module module : Empress.INSTANCE.moduleManager.getModules(category)){
            moduleButtons.add(new ModuleButton(x+10,y+FontUtil.getFontHeight(FontUtil.fonts.Helvetica) + subModulesHeight, FontUtil.getStringWidth(module.getName()), FontUtil.getFontHeight(FontUtil.fonts.Helvetica), module ));
            subModulesHeight += FontUtil.getFontHeight(FontUtil.fonts.Helvetica);
        }

    }


    public void draw(int mouseX, int mouseY, float partialTicks){
        FontUtil.drawString(category.name(), x, y, visible ? Color.WHITE.getRGB() : Color.LIGHT_GRAY.getRGB(), FontUtil.fonts.Helvetica);
        if (visible) {
            GuiUtils.drawDownwardsArrow(x - 4, y + 1);
        } else {
            GuiUtils.drawSideArrow(x - 4, y + 1);

        }

        subModulesHeight = 0;
        if(visible) {
            for (ModuleButton moduleButton : moduleButtons) {
                moduleButton.updatePosition(x+10,y+FontUtil.getFontHeight(FontUtil.fonts.Helvetica) + subModulesHeight + 2);
                moduleButton.draw(mouseX, mouseY, partialTicks);
                subModulesHeight += FontUtil.getFontHeight(FontUtil.fonts.Helvetica)+3;
            }
            /*
            for(Module module : Empress.INSTANCE.moduleManager.getModules(category)){
                moduleButtons.add(new ModuleButton(x+10,y+FontUtil.getFontHeight() + subModulesHeight, FontUtil.getStringWidth(module.getName()), FontUtil.getFontHeight(), module ));

                subModulesHeight += FontUtil.getFontHeight();
            }
             */
        }

    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton){

        if(inside(mouseX, mouseY)) {
            this.visible = !visible;
        }

        if(visible){
            for(ModuleButton moduleButton : moduleButtons){
                moduleButton.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
    }

    public void updatePosition(int x, int y){
        this.x = x;
        this.y = y;
    }
}
