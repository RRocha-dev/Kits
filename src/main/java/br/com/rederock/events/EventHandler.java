package br.com.rederock.events;

import br.com.rederock.Kits;
import br.com.rederock.events.listeners.InventoryClick;
import br.com.rederock.events.listeners.InventoryDrag;
import br.com.rederock.events.listeners.InventoryMoveItem;
import br.com.rederock.events.listeners.InventoryPickUpItem;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class EventHandler {
    public EventHandler(Kits plugin) {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new InventoryClick(plugin), plugin);
        pm.registerEvents(new InventoryDrag(), plugin);
        pm.registerEvents(new InventoryMoveItem(), plugin);
        pm.registerEvents(new InventoryPickUpItem(), plugin);
    }
}
