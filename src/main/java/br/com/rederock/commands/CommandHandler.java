package br.com.rederock.commands;

import br.com.rederock.Kits;
import br.com.rederock.commands.executors.KitCommand;
import br.com.rederock.commands.executors.KitsCommand;

public class CommandHandler {
    public CommandHandler(Kits plugin) {

        plugin.getCommand("kits").setExecutor(new KitsCommand(plugin));
        plugin.getCommand("kit").setExecutor(new KitCommand(plugin));
    }
}
