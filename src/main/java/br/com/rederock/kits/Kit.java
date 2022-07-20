package br.com.rederock.kits;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.Material;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Kit {
    private String name;
    private String description;
    private Material thumbnail_item;
    private HashMap<Material, Integer> items;

    public Kit(String name, String description, Material thumbnail_item) {
        this.name = name;
        this.description = description;
        this.thumbnail_item = thumbnail_item;
        items = new HashMap<>();
    }

    public void addItem(Material material, int amount) {
        items.put(material, amount);
    }
}
