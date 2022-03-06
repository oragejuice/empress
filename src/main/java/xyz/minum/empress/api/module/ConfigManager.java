package xyz.minum.empress.api.module;

import com.moandjiezana.toml.Toml;
import xyz.minum.empress.Empress;
import xyz.minum.empress.api.setting.Setting;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Linustouchtips (for now)
 */

public class ConfigManager {

    private String currentConfig;

    private final File mainDirectory = new File("empress");

    private final List<String> configs = new CopyOnWriteArrayList<>();


    public ConfigManager() {

        // create a default preset, if the user does not make any custom presets this will be loaded
        configs.add("default");
        currentConfig = "default";

        // load and save the default config
        load();
        save();

        // save the config when the game is closed
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Empress.INSTANCE.configManager.save();
        }));
    }

    public void load() {
        writeDirectories();
        loadModules();

    }

    /**
     * Saves the client configuration
     */
    public void save() {
        saveInfo();
        saveModules();
    }

    public void saveInfo() {
        try {
            // the file writer
            OutputStreamWriter infoOutputStreamWriter = new OutputStreamWriter(new FileOutputStream(mainDirectory.getName() + "/info.toml"), StandardCharsets.UTF_8);

            // the output string
            StringBuilder outputTOML = new StringBuilder();

            try {
                outputTOML.append("[Info]").append("\r\n");

                // write our client info
                outputTOML.append("Setup = ").append(Empress.INSTANCE.SETUP).append("\r\n");
                //outputTOML.append("Prefix = ").append('"').append(Cosmos.PREFIX).append('"').append("\r\n");
                outputTOML.append("Preset = ").append('"').append(currentConfig).append('"').append("\r\n");
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            infoOutputStreamWriter.write(outputTOML.toString());
            infoOutputStreamWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }



    public void saveModules() {
        try {
            // the file writer
            OutputStreamWriter moduleOutputStreamWriter = new OutputStreamWriter(new FileOutputStream(mainDirectory.getName() + "/" + currentConfig + "/modules.toml"), StandardCharsets.UTF_8);

            // the output string
            StringBuilder outputTOML = new StringBuilder();

            Empress.INSTANCE.moduleManager.getModules().forEach(module -> {
                if (module != null) {
                    try {
                        // writes the enabled state, drawn state, and bind
                        outputTOML.append("[").append(module.getName()).append("]").append("\r\n");
                        outputTOML.append("Enabled = ").append(module.isEnabled()).append("\r\n");
                        //outputTOML.append("Drawn = ").append(module.isDrawn()).append("\r\n");
                        outputTOML.append("Bind = ").append(module.getBind()).append("\r\n");

                        module.getSettings().forEach(setting -> {
                            if (setting != null) {
                                // write the setting value
                                outputTOML.append(setting.getName());
                                outputTOML.append(" = ");

                                {
                                    if (setting.getValue() instanceof Enum<?>) {
                                        outputTOML.append('"').append(setting.getValue().toString()).append('"');
                                    }

                                    else if (setting.getValue() instanceof Color) {
                                        outputTOML.append(((Color) setting.getValue()).getRGB());
                                    }

                                    else {
                                        outputTOML.append(setting.getValue());
                                    }
                                }

                                // put the next setting on a new line
                                outputTOML.append("\r\n");
                            }
                        });

                        outputTOML.append("\r\n");
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });

            moduleOutputStreamWriter.write(outputTOML.toString());
            moduleOutputStreamWriter.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void loadModules() {
        try {
            // the stream from the configuration file
            InputStream inputStream = Files.newInputStream(Paths.get(mainDirectory.getName() + "/" + currentConfig + "/modules.toml"));

            // the toml input from the file
            Toml inputTOML = new Toml().read(inputStream);

            if (inputTOML != null) {
                Empress.INSTANCE.moduleManager.getModules().forEach(module -> {
                    if (module != null) {
                        try {
                            // set the enabled state
                            if (inputTOML.getBoolean(module.getName() + ".Enabled") != null) {
                                if (inputTOML.getBoolean(module.getName() + ".Enabled", false)) {
                                    module.setEnabled(true);
                                }
                            }

                            // set the keybind
                            if (inputTOML.getLong(module.getName() + ".Bind") != null) {
                                int key = inputTOML.getLong(module.getName() + ".Bind", 0L).intValue();
                                module.setBind(key);
                            }

                            // set the setting values
                            module.getSettings().forEach(setting -> {
                                if (setting != null) {
                                    try {
                                        // the setting identifier in the TOML file
                                        String identifier;

                                        identifier = module.getName() + "." + setting.getName();
                                        // set the value based on the setting data type
                                        if (setting.getValue() instanceof Boolean) {
                                            if (inputTOML.getBoolean(identifier) != null) {
                                                boolean value = inputTOML.getBoolean(identifier, false);
                                                ((Setting<Boolean>) setting).setValue(value);
                                            }
                                        }

                                        else if (setting.getValue() instanceof Double) {
                                            if (inputTOML.getDouble(identifier) != null) {
                                                double value = inputTOML.getDouble(identifier, 0.0);
                                                ((Setting<Double>) setting).setValue(value);
                                            }
                                        }

                                        else if (setting.getValue() instanceof Float) {
                                            if (inputTOML.getDouble(identifier) != null) {
                                                float value = inputTOML.getDouble(identifier, 0.0).floatValue();
                                                ((Setting<Float>) setting).setValue(value);
                                            }
                                        }

                                        else if (setting.getValue() instanceof Enum<?>) {
                                            if (inputTOML.getString(identifier) != null) {
                                                Enum<?> value = Enum.valueOf(((Enum<?>) setting.getValue()).getClass(), inputTOML.getString(identifier, ""));
                                                ((Setting<Enum<?>>) setting).setValue(value);
                                            }
                                        }

                                        else if (setting.getValue() instanceof Color) {
                                            if (inputTOML.getLong(identifier) != null) {
                                                Color value = new Color(inputTOML.getLong(identifier, -1L).intValue(), true);
                                                ((Setting<Color>) setting).setValue(value);
                                            }
                                        }
                                    } catch (Exception exception) {
                                        exception.printStackTrace();
                                    }
                                }
                            });
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                });
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


    public void writeDirectories() {
        if (!mainDirectory.exists()) {
            mainDirectory.mkdirs();
        }

        configs.forEach(preset -> {
            File presetDirectory = new File("empress/" + preset);

            if (!presetDirectory.exists()) {
                presetDirectory.mkdirs();
            }
        });
    }

}
