package com.monkeyteam.monkeyutils

import com.monkeyteam.monkeyutils.modules.*
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class MonkeyUtils : JavaPlugin() {

  companion object {
    private var realInstance: MonkeyUtils? = null
    val instance
      get() = realInstance!!

    fun log(message: String) {
      Bukkit.getConsoleSender().sendMessage("§6[MonkeyUtils] §r${message}")
    }
  }

  override fun onEnable() {
    realInstance = this

    // Events
    server.pluginManager.registerEvents(CropsBreak, this)
    server.pluginManager.registerEvents(Back, this)
    server.pluginManager.registerEvents(Cat, this)
    server.pluginManager.registerEvents(MOTD, this)
    server.pluginManager.registerEvents(Whitelist, this)

    // Commands
    getCommand("openinventory")!!.setExecutor(OpenInventory)
    getCommand("back")!!.setExecutor(Back)
    getCommand("home")!!.setExecutor(Home.HomeCommand)
    getCommand("sethome")!!.setExecutor(Home.SetHomeCommand)
    getCommand("monkeyreload")!!.setExecutor(Reload)
    getCommand("whitelist")!!.setExecutor(Whitelist)

    log("Ready to monkeying !")
  }

  override fun onDisable() {
    // Plugin shutdown logic
  }
}
