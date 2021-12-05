package dev.betaken.lifestealsmp.recipe;

import dev.betaken.lifestealsmp.Main;
import dev.betaken.lifestealsmp.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class RecipeManager {

    private List<String> lore = new ArrayList<>();

    public void reload() {
        Bukkit.addRecipe(getHeartRecipe());
    }

    private ShapedRecipe getHeartRecipe() {
        ItemStack item = new ItemStack(Material.RED_DYE);

        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(1);
        meta.setDisplayName(ChatUtil.format("&c&lHeart"));
        meta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        lore.add(ChatUtil.format("&eRight Click To Gain One Heart!"));

        meta.setLore(lore);

        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class), "dye");

        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("DGD", "GTG", "DGD");

        recipe.setIngredient('G', Material.GOLD_BLOCK);
        recipe.setIngredient('D', Material.DIAMOND_BLOCK);
        recipe.setIngredient('T', Material.TOTEM_OF_UNDYING);

        return recipe;
    }

}