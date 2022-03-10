package xyz.minum.empress.api.module;


import xyz.minum.empress.impl.modules.client.ClickGui;
import xyz.minum.empress.impl.modules.TestModule;
import xyz.minum.empress.impl.modules.client.Watermark;
import xyz.minum.empress.impl.modules.combat.Aura;
import xyz.minum.empress.impl.modules.combat.ChorusController;
import xyz.minum.empress.impl.modules.player.Phase;
import xyz.minum.empress.impl.modules.player.Timer;
import xyz.minum.empress.impl.modules.player.Velocity;
import xyz.minum.empress.impl.modules.world.ESP;
import xyz.minum.empress.impl.modules.world.Fakeplayer;
import xyz.minum.empress.impl.modules.world.Fullbright;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ModuleManager {

    private final ArrayList<Module> modules = new ArrayList();

    public ModuleManager(){
        //Add new moudles here
        modules.add(new TestModule());
        modules.add(new ClickGui());
        modules.add(new Velocity());
        modules.add(new Fullbright());
        modules.add(new Timer());
        modules.add(new Phase());
        modules.add(new Fakeplayer());
        modules.add(new Aura());
        modules.add(new Watermark());
        modules.add(new ESP());
        modules.add(new ChorusController());

    }

    public ArrayList<Module> getModules(){
        return modules;
    }

    public ArrayList<Module> getEnabledModules(){
        return modules.stream().filter(Module::isEnabled).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Module> getModules(Category category){
        return modules.stream().filter(module -> module.getCategory() == category).collect(Collectors.toCollection(ArrayList::new));
    }

    //Thank you A2H for this code :p
    public <T extends Module> T getModule(Class<T> aClass) {
        return (T) modules.stream().filter(module -> module.getClass().equals(aClass)).findFirst().orElse(null);
    }



}
