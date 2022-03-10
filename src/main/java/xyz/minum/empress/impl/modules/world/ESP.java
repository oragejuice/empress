package xyz.minum.empress.impl.modules.world;

import xyz.minum.empress.api.module.Category;
import xyz.minum.empress.api.module.Module;
import xyz.minum.empress.api.setting.Setting;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class ESP extends Module {


    public static Setting<Boolean> players = new Setting<>("players", true);
    public static Setting<Boolean> items = new Setting<>("items", true);
    public static Setting<Boolean> crystals = new Setting<>("crystals", true);
    public static Setting<Double> width = new Setting<>("width", 1.0,0.1,10.0);

    Color color = new Color(255, 209, 220);

    public ESP() {
        super("ESP", Category.world);
    }




}
