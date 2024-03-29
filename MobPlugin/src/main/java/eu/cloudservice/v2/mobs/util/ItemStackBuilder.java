package eu.cloudservice.v2.mobs.util;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;

public final class ItemStackBuilder {


    protected ItemMeta itemMeta;
    protected ItemStack itemStack;

    public ItemStackBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemStackBuilder(Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemStackBuilder(Material material, int amount, int sub) {
        this.itemStack = new ItemStack(material, amount, (short) sub);
        this.itemMeta = itemStack.getItemMeta();
    }


    public static ItemStackBuilder builder(Material material) {
        return new ItemStackBuilder(material);
    }

    public static ItemStackBuilder builder(Material material, int amount) {
        return new ItemStackBuilder(material, amount);
    }

    public static ItemStackBuilder builder(Material material, int amount, int sub) {
        return new ItemStackBuilder(material, amount, sub);
    }


    public ItemStackBuilder enchantment(Enchantment enchantment, int value) {
        itemMeta.addEnchant(enchantment, value, true);
        return this;
    }

    public ItemStackBuilder color(Color color) {
        if (itemMeta instanceof LeatherArmorMeta) {
            ((LeatherArmorMeta) itemMeta).setColor(color);
        }
        return this;
    }

    public ItemStackBuilder owner(String name) {
        if (itemMeta instanceof SkullMeta) {
            ((SkullMeta) itemMeta).setOwner(name);
        }
        return this;
    }

    public ItemStackBuilder displayName(String name) {
        itemMeta.setDisplayName(name);
        return this;
    }

    public ItemStackBuilder lore(String... lore) {
        itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemStackBuilder lore(List<String> lore) {
        itemMeta.setLore(lore);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
