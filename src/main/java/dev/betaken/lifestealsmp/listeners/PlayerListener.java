package dev.betaken.lifestealsmp.listeners;

import dev.betaken.lifestealsmp.util.ChatUtil;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerKilled(PlayerDeathEvent e) {

        Player victim = e.getEntity();

        if(victim.getKiller() instanceof Player) {

            Player killer = victim.getKiller();

            if (killer != victim) {

                double vHealth = victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
                double kHealth = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();

                victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(vHealth - 2);
                killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(kHealth + 2);

                if (victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() <= 0.0) {
                    victim.setGameMode(GameMode.SPECTATOR);
                }
            }
        } else {
            double vHealth = victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();

            victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(vHealth - 2);

            if (victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() <= 0.0) {
                victim.setGameMode(GameMode.SPECTATOR);
            }

            if (victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() <= 0.0) {
                victim.setGameMode(GameMode.SPECTATOR);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() <= 0.0) {
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getItem().getType() == Material.RED_DYE && event.getItem().getItemMeta().getCustomModelData() == 1 && event.getHand() == EquipmentSlot.HAND) {
            event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(event.getPlayer().getMaxHealth() + 2);
            event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
        }
    }

}