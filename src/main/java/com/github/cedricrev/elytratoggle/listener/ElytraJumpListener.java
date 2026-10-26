package com.github.cedricrev.elytratoggle.listener;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerInput;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class ElytraJumpListener implements PacketListener {

    private final Plugin plugin;

    public ElytraJumpListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {

        if (event.getPacketType() != PacketType.Play.Client.PLAYER_INPUT)
            return;

        WrapperPlayClientPlayerInput packet = new WrapperPlayClientPlayerInput(event);

        if (!packet.isJump())
            return;

        Player player = event.getPlayer();

        if (player == null)
            return;

        Bukkit.getScheduler().runTask(plugin, () -> {
            if (!player.isOnline() || !player.isGliding())
                return;

            if (!player.hasPermission("elytratoggle.use"))
                return;

            if (!isWearingGlider(player))
                return;

            player.setGliding(false);
        });

    }

    private boolean isWearingGlider(Player player) {
        for (ItemStack item : player.getInventory().getArmorContents()) {
            if (isGlider(item))
                return true;
        }
        return false;
    }

    private boolean isGlider(ItemStack item) {
        if (item == null || item.getType().isAir())
            return false;

        if (item.getType() == Material.ELYTRA)
            return true;

        ItemMeta meta = item.getItemMeta();
        return meta != null && meta.isGlider();
    }

}
