package fr.velris.cashflow.managers;

import fr.velris.cashflow.CashFlow;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MFiles {

    private final CashFlow plugin = CashFlow.getInstance();

    public MFiles() {}

    public void loadConfigs() {
        loadConfig("config.yml");
        loadConfig("lang.yml");
    }

    public YamlConfiguration getConfig(String fileName) {
        File file = new File(plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            plugin.saveResource(fileName, false);
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public void saveConfig(YamlConfiguration config, String fileName) {
        try {
            config.save(new File(plugin.getDataFolder(), fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAllConfigs() {
        saveConfig(plugin.config, "config.yml");
        saveConfig(plugin.lang, "lang.yml");
    }

    private void loadConfig(String fileName) {
        YamlConfiguration config = getConfig(fileName);
        switch (fileName) {
            case "config.yml":
                plugin.config = config;
                break;
            case "lang.yml":
                plugin.lang = config;
                break;
            default:
                break;
        }
    }

    public void updateConfigsIfNeeded() {
        updateConfigIfNeeded(plugin.config, "config.yml");
        updateConfigIfNeeded(plugin.lang, "lang.yml");
    }

    private void updateConfigIfNeeded(YamlConfiguration config, String fileName) {
        // Charger la configuration par défaut
        YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(
                new File(plugin.getDataFolder(), fileName + ".default"));

        // Vérifier si toutes les sections et clés de la configuration par défaut sont présentes dans la configuration actuelle
        for (String sectionName : defaultConfig.getKeys(false)) {
            ConfigurationSection section = config.getConfigurationSection(sectionName);
            if (section == null) {
                // La section n'existe pas dans la configuration actuelle, on l'ajoute avec toutes les clés par défaut
                section = config.createSection(sectionName);
                Map<String, Object> defaults = defaultConfig.getConfigurationSection(sectionName).getValues(false);
                for (Map.Entry<String, Object> entry : defaults.entrySet()) {
                    section.set(entry.getKey(), entry.getValue());
                }
            } else {
                // La section existe dans la configuration actuelle, on vérifie que toutes les clés par défaut sont présentes
                Map<String, Object> defaults = defaultConfig.getConfigurationSection(sectionName).getValues(false);
                for (Map.Entry<String, Object> entry : defaults.entrySet()) {
                    if (!section.contains(entry.getKey())) {
                        // La clé n'existe pas dans la section, on l'ajoute avec sa valeur par défaut
                        section.set(entry.getKey(), entry.getValue());
                    }
                }
            }
        }

        // Enregistrer la configuration mise à jour
        saveConfig(config, fileName);
    }

}