package com.monkeyteam.monkeyutils.modules;

import com.monkeyteam.monkeyutils.MonkeyUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class MOTD implements Listener {

    private final List<String> messages;

    public MOTD() {
        messages = MonkeyUtils.getInstance().getConfig().getStringList("motd");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        messages.forEach(player::sendMessage);
    }

}
