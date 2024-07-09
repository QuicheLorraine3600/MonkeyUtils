package com.monkeyteam.monkeyutils.modules

import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable

object Cat : Listener {
  @EventHandler
  fun onCatShearing(event: PlayerInteractEntityEvent) {
    val player = event.player
    if (player.inventory.itemInMainHand.type == Material.SHEARS &&
        event.rightClicked.type == EntityType.CAT) {
      val cat = event.rightClicked as LivingEntity
      cat.damage(0.0)
      val moustache = ItemStack(Material.STRING, 1)
      val meta = moustache.itemMeta!!
      meta.setItemName("§rMoustache")
      moustache.setItemMeta(meta)
      cat.world.dropItemNaturally(cat.location, moustache)
      cat.world.playSound(cat, Sound.ENTITY_CAT_HURT, 2f, 0.75f)
      val shearsMeta = player.inventory.itemInMainHand.itemMeta as Damageable
      shearsMeta.damage += 25
      player.inventory.itemInMainHand.setItemMeta(shearsMeta)
      if (shearsMeta.damage >= 238) {
        player.inventory.itemInMainHand.amount = 0
      }
    }
  }
}
