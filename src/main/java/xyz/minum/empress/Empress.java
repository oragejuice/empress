package xyz.minum.empress;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.minum.empress.api.event.EventHandler;
import xyz.minum.empress.api.module.ConfigManager;
import xyz.minum.empress.api.module.Module;
import xyz.minum.empress.api.module.ModuleManager;
import xyz.minum.empress.api.setting.Setting;
import xyz.minum.empress.impl.modules.TestModule;

@Mod(modid = Empress.MOD_ID)
public class Empress {

    @Mod.Instance
    public static Empress INSTANCE;

    public boolean SETUP = false;

    public static final String MOD_ID = "empress";
    public static final String VERSION = "0.1b";
    public static final Logger logger = LogManager.getLogger();

    public ModuleManager moduleManager;
    public ConfigManager configManager;


    public Empress(){
        INSTANCE = this;
    }


    @Mod.EventHandler
    public void onPostInit (FMLPostInitializationEvent event) {
        logger.info(MOD_ID + "-" + VERSION + " loaded");
        moduleManager = new ModuleManager();
        MinecraftForge.EVENT_BUS.register(new EventHandler());

        for(Module m : moduleManager.getModules()){
            logger.info(m.getName() + " loaded");
           for(Setting s : m.getSettings()){
                   logger.info("⌞" + s.getName());
           }
        }

        configManager = new ConfigManager();

    }



}
