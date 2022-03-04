package xyz.minum.empress.api.module;

import net.minecraftforge.common.MinecraftForge;
import xyz.minum.empress.api.Globals;
import xyz.minum.empress.api.setting.Setting;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Module implements Globals {

    private String name;
    private boolean enabled =  false;
    private int bind;
    private final Category category;

    private final ArrayList<Setting<?>> settings = new ArrayList<>();

    public Module(String name, Category category){
        this.name = name;
        this.category = category;


        Arrays.stream(this.getClass().getFields())
                .filter(field -> Setting.class.isAssignableFrom(field.getType()))
                .forEach(field -> {
                    field.setAccessible(true);
                    try {
                        Setting<?> setting = ((Setting<?>) field.get(this));
                        settings.add(setting);
                    } catch (IllegalArgumentException | IllegalAccessException exception) {
                        exception.printStackTrace();
                    }
                });
    }

    public ArrayList<Setting<?>> getSettings() {
        return settings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void toggle(){
        this.enabled = !this.enabled;
        if (enabled) onEnable(); else onDisable();
    }

    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    public int getBind() {
        return bind;
    }

    public void setBind(int bind) {
        this.bind = bind;
    }

    public Category getCategory() {
        return category;
    }

    public void addSetting(Setting<?> settingGeneric){
        settings.add(settingGeneric);
    }



}
