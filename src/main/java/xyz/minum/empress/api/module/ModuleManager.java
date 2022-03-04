package xyz.minum.empress.api.module;


import xyz.minum.empress.impl.modules.ClickGui;
import xyz.minum.empress.impl.modules.TestModule;
import xyz.minum.empress.impl.modules.Velocity;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ModuleManager {

    private final ArrayList<Module> modules = new ArrayList();

    public ModuleManager(){
        //Add new moudles here
        modules.add(new TestModule());
        modules.add(new ClickGui());
        modules.add(new Velocity());

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
