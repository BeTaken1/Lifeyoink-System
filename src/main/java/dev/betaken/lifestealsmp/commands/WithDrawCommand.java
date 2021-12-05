package dev.betaken.lifestealsmp.commands;

import dev.betaken.lifestealsmp.util.ChatUtil;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class WithDrawCommand implements CommandExecutor {

    private List<String> lore = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatUtil.format("&c/withdraw [number]"));
        } else if (args.length == 1) {
            try {
                if ((player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2) <= Integer.parseInt(args[0])) {
                    player.sendMessage(ChatUtil.format("&cIf you do this that will mean that you will lose all of ur hearts!"));
                } else {
                    ItemStack item = new ItemStack(Material.RED_DYE);
                    item.setAmount(Integer.parseInt(args[0]));

                    ItemMeta meta = item.getItemMeta();
                    meta.setCustomModelData(1);
                    meta.setDisplayName(ChatUtil.format("&c&lHeart"));
                    meta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                    item.setItemMeta(meta);

                    lore.add(ChatUtil.format("&eRight Click To Gain One Heart!"));

                    player.getInventory().addItem(item);

                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - (Integer.parseInt(args[0]) * 2));
                }
            } catch (NumberFormatException exception) {
                player.sendMessage(ChatUtil.format("&c/withdraw [number]"));
            }
        }

        return false;
    }

}