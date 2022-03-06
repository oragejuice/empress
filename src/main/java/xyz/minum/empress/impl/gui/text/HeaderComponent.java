package xyz.minum.empress.impl.gui.text;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.lwjgl.input.Keyboard;
import xyz.minum.empress.Empress;
import xyz.minum.empress.api.module.Module;
import xyz.minum.empress.api.utils.render.GuiUtils;
import xyz.minum.empress.api.utils.render.TextGuiComponent;
import xyz.minum.empress.api.utils.render.font.FontUtil;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


public class HeaderComponent extends TextGuiComponent {

    Module module;
    String header;
    String desc;
    String bind;
    String closer;
    private int i;
    private boolean binding;


    public HeaderComponent(int x, int y, Module module) {
        super(x, y,module.getDescription().isEmpty() ? 3 : 4);
        this.module = module;

        desc = ChatFormatting.GRAY + "/* " + module.getDescription() + " */";
        header = "§6public " + ChatFormatting.BLUE + module.getName() + ChatFormatting.WHITE + "§f() §6extends " + " §fModule {";
        bind = ChatFormatting.RED + "    super" + ChatFormatting.WHITE + "(" + ChatFormatting.YELLOW + "bind." + ChatFormatting.GRAY + Keyboard.getKeyName(module.getBind()) + ChatFormatting.WHITE + ")";
        closer = ChatFormatting.WHITE + "}";

    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks){
        if(module == null) return;

        //int sX = x+FontUtil.getStringWidth(bind)-FontUtil.getStringWidth(Keyboard.getKeyName(module.getBind())+")");
        //GuiUtils.drawBox(sX, y+FontUtil.getFontHeight(FontUtil.fonts.JetBrains)*(1+i), FontUtil.getStringWidth(Keyboard.getKeyName(module.getBind())), FontUtil.getFontHeight(FontUtil.fonts.JetBrains), Color.WHITE.getRGB());

        desc = ChatFormatting.DARK_GRAY + "/* " + module.getDescription() + " */";
        header = "§6public " + ChatFormatting.BLUE + module.getName() + "§f()" + " §6extends " + ChatFormatting.WHITE + "Module {";
        bind = ChatFormatting.RED + "    super" + ChatFormatting.WHITE + "(" + "§4bind." + ChatFormatting.GRAY + (binding ? "..." : Keyboard.getKeyName(module.getBind())) + ChatFormatting.WHITE + ")";
        closer = ChatFormatting.WHITE + "}";

        int i = module.getDescription().isEmpty() ? 0 : 1;
        if(!module.getDescription().isEmpty()){
            FontUtil.drawString(desc, x, y, Color.WHITE.getRGB(), FontUtil.fonts.JetBrains );
        }
        FontUtil.drawString(header, x, y+FontUtil.getFontHeight(FontUtil.fonts.JetBrains)*(0+i), 0xCC7832, FontUtil.fonts.JetBrains );
        FontUtil.drawString(bind, x, y+FontUtil.getFontHeight(FontUtil.fonts.JetBrains)*(1+i)+3, Color.WHITE.getRGB(), FontUtil.fonts.JetBrains );
        FontUtil.drawString(closer, x, y+FontUtil.getFontHeight(FontUtil.fonts.JetBrains)*(2+i)+6, Color.WHITE.getRGB(), FontUtil.fonts.JetBrains );
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        if(binding){
            if(keyCode == Keyboard.KEY_ESCAPE || keyCode == Keyboard.KEY_DELETE) {
                module.setBind(0);
                binding = false;
                return;
            }
            module.setBind(keyCode);
            binding = false;
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton){
        i = module.getDescription().isEmpty() ? 0 : 1;
        int sX = x+FontUtil.getStringWidth(bind)-FontUtil.getStringWidth(Keyboard.getKeyName(module.getBind())+")");
        if(inside(mouseX, mouseY, sX, y+FontUtil.getFontHeight(FontUtil.fonts.JetBrains)*(1+i)+3, FontUtil.getStringWidth(Keyboard.getKeyName(module.getBind())), FontUtil.getFontHeight(FontUtil.fonts.JetBrains))){
            Empress.logger.info("Bind clicked!");
            binding = true;
        } else {
            binding = false;
        }
    }




}
