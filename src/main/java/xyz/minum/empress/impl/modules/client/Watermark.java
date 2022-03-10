package xyz.minum.empress.impl.modules.client;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.minum.empress.api.module.Category;
import xyz.minum.empress.api.module.Module;
import xyz.minum.empress.api.utils.render.GuiUtils;
import xyz.minum.empress.api.utils.render.font.FontUtil;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Watermark extends Module {

    public Watermark() {
        super("watermark", Category.client);
    }

    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");


    @SubscribeEvent
    public void onRender2D(RenderGameOverlayEvent.Chat event){

        ScaledResolution sr = new ScaledResolution(mc);
        Date date = new Date();
        String s = "empress | " + formatter.format(date) + " | " + (!mc.isSingleplayer() ? Objects.requireNonNull(mc.getConnection()).getPlayerInfo(mc.player.getUniqueID()).getResponseTime() : "localhost");
        GuiUtils.drawHorizontalGradientRect(sr.getScaledWidth() - (FontUtil.getStringWidth(s, FontUtil.fonts.Helvetica)+40), 8, sr.getScaledWidth() - 10, 10+FontUtil.getFontHeight(FontUtil.fonts.Helvetica), new Color(0,0,0,255).getRGB(), new Color(0,0,0,0).getRGB());
        FontUtil.drawString(s, sr.getScaledWidth() - (FontUtil.getStringWidth(s, FontUtil.fonts.Helvetica)+35), 10, Color.WHITE.getRGB());
    }
}
