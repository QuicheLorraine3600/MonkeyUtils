package com.monkeyteam.monkeyutils.modules;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Cat implements Listener {

    @EventHandler
    public void onCatShearing(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInUse() != null && player.getItemInUse().getType() == Material.SHEARS && event.getRightClicked().getType() == EntityType.CAT) {
            LivingEntity cat = (LivingEntity) event.getRightClicked();
            cat.damage(0);
            ItemStack moustache = new ItemStack(Material.STRING, 1);
            ItemMeta meta = moustache.getItemMeta();
            meta.setItemName("§rMoustache");
            moustache.setItemMeta(meta);
            cat.getWorld().dropItemNaturally(cat.getLocation(), moustache);
            cat.getWorld().playSound(cat, Sound.ENTITY_CAT_HURT, 2f, 0.75f);
        }
    }

}
