package br.com.rederock.commands.executors;

import br.com.rederock.Kits;
import br.com.rederock.kits.Kit;
import br.com.rederock.model.Item;
import br.com.rederock.utils.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class KitsCommand implements CommandExecutor {

    private Inventory gui;

    public KitsCommand(Kits plugin) {
        ArrayList<Kit> kits = plugin.getKits();
        int inventorySize = (kits.size() > 9) ? (kits.size() > 18 ? 27 : 18) : 9;
        gui = Bukkit.createInventory(null, inventorySize, "§2[Kits]");
        loadKits(kits);
    }

    public void loadKits(ArrayList<Kit> kits) {
        for (int i = 0; i < kits.size(); i++) {
            Item item = new Item(kits.get(i).getThumbnail_item(), kits.get(i).getName(), kits.get(i).getDescription());
            gui.setItem(i, item.getItemStack());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Messages.info("Apenas jogadores podem executar esse comando!");
            return true;
        }
        Player p = (Player) sender;

        p.openInventory(gui);

        Messages.debug(p.getName() + " executou o comando 'KITS'.");
        Messages.informPlayer(p, "Carregando os kits...");
        return true;
    }
}
