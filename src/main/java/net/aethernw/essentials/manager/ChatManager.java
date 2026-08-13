package net.aethernw.essentials.manager;

import net.aethernw.essentials.AetherEssentials;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatManager implements Listener {

    private final AetherEssentials plugin;
    private boolean muted;

    public ChatManager(AetherEssentials plugin) {
        this.plugin = plugin;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!muted) {
            return;
        }
        event.setCancelled(true);
        event.getPlayer().sendMessage(plugin.getConfigManager().message("chat-is-muted"));
    }

    public void clearChat() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < 100; i++) {
                player.sendMessage("");
            }
        }
    }
}