package br.com.rederock;

import br.com.rederock.commands.CommandHandler;
import br.com.rederock.events.EventHandler;
import br.com.rederock.kits.Kit;
import br.com.rederock.utils.messages.Messages;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Kits extends JavaPlugin {

    private ArrayList<Kit> kits;

    public static FileConfiguration config;

    @Override
    public void onEnable() {
        // CARREGA CONFIGURAÇÕES
        saveDefaultConfig();
        config = getConfig();

        // ATIVA PLUGINS
        Messages.debug(config.getBoolean("debug"));

        // IMPORTA OS KITS DO ARQUIVO DE CONFIGURAÇÃO
        importKits();

        new CommandHandler(this);
        new EventHandler(this);
    }

    private void importKits() {
        Messages.debug("importing kits...");

        kits = new ArrayList<Kit>();

        for (String kitName : config.getConfigurationSection("kits").getKeys(false)) {
            String name = kitName.substring(0, 1).toUpperCase() + kitName.substring(1);
            String description = config.getString("kits." + kitName + ".descricao");
            Material thumbnail_item = Material.matchMaterial(config.getString("kits." + kitName + ".miniatura").toUpperCase());

            // CRIA O KIT
            Kit kit = new Kit(name, description, thumbnail_item);

            Messages.debug("--");
            Messages.debug("Nome: " + kitName);
            Messages.debug("Descrição: " + description);
            Messages.debug("Miniatura: " + thumbnail_item);
            Messages.debug("items: ");

            // ADICIONA OS ITENS
            List<String> items = config.getStringList("kits." + kitName + ".items");
            items.forEach(itemStr -> {
                String[] splittedItemStr = itemStr.split(" ");
                Material item = Material.matchMaterial(splittedItemStr[0].toUpperCase());
                int amount = Integer.parseInt(splittedItemStr[1]);

                if (item != null && amount > 0) {
                    kit.addItem(item, amount);
                    Messages.debug("- " + splittedItemStr[0] + " (" + amount + ")");
                }
            });

            // ADICIONA O KIT NA LISTA DE KITS
            kits.add(kit);
        }

        Messages.debug("KITS IMPORTADOS COM SUCESSO!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ArrayList<Kit> getKits() {
        return kits;
    }
}
