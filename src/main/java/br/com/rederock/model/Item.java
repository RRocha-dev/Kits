package br.com.rederock.model;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Item {
    private ItemStack item;

    public Item(Material material, String name, String description) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + name);
        meta.setLore(Arrays.asList(ChatColor.GRAY + description));

        item.setItemMeta(meta);

        this.item = item;
    }

    public ItemStack getItemStack() {
        return item;
    }
}
