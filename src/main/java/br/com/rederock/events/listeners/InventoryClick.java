package br.com.rederock.events.listeners;

import br.com.rederock.Kits;
import br.com.rederock.kits.Kit;
import br.com.rederock.utils.messages.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class InventoryClick implements Listener {

    private ArrayList<Kit> kits;

    public InventoryClick(Kits plugin) {
        kits = plugin.getKits();
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e) {
        if (e.getInventory().getType() == InventoryType.CHEST && e.getInventory().getHolder() == null) {
            e.setCancelled(true);

            Player p = (Player) e.getWhoClicked();
            if (e.getCurrentItem() != null) {
                kits.forEach(kit -> {
                    if (kit.getThumbnail_item() == e.getCurrentItem().getType()) {
                        kit.getItems().forEach((material, amount) -> {
                            p.getInventory().addItem(new ItemStack(material, amount));
                        });

                        Messages.debug("Entregue a " + p.getName() + " o kit " + kit.getName());
                        Messages.informPlayer(p, "§3→ Você recebeu o kit §4" + kit.getName() + "§3 ←");
                        return;
                    }
                });
            }
        }
    }
}
