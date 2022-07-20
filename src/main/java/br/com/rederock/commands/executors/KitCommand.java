package br.com.rederock.commands.executors;

import br.com.rederock.Kits;
import br.com.rederock.kits.Kit;
import br.com.rederock.utils.messages.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class KitCommand implements CommandExecutor {

    private ArrayList<Kit> kits;
    private boolean kitFound;

    public KitCommand(Kits plugin) {
        kits = plugin.getKits();
        kitFound = false;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Messages.info("Apenas jogadores podem executar esse comando!");
            return true;
        }
        Player p = (Player) sender;
        if (args.length == 0) {
            Messages.informPlayer(p, "Favor digite o nome do kit!");
            Messages.informPlayer(p, "Use o comando: /kit <nome>");
            return true;
        }

        kits.forEach(kit -> {
            if (kit.getName().equalsIgnoreCase(args[0])) {
                kit.getItems().forEach(((material, amount) -> {
                    p.getInventory().addItem(new ItemStack(material, amount));
                }));
                Messages.informPlayer(p, "→ Você recebeu o kit " + kit.getName() + " ←");
                Messages.debug("Foi entregue para o jogador " + p.getName() + " o kit " + kit.getName());
                kitFound = true;
                return;
            }
        });

        if (!kitFound) {
            Messages.informPlayer(p, "→ Não encontramos nenhum kit com esse nome =( ←");
        }
        kitFound = false;
        return true;
    }
}
