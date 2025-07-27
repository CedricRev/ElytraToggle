package com.github.cedricrev.elytratoggle.listener;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerInput;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ElytraJumpListener implements PacketListener {

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {

        if (event.getPacketType() != PacketType.Play.Client.PLAYER_INPUT)
            return;

        Player player = event.getPlayer();

        WrapperPlayClientPlayerInput packet = new WrapperPlayClientPlayerInput(event);

        if (!packet.isJump())
            return;

        ItemStack chest = player.getInventory().getChestplate();
        if (chest == null || chest.getType() != Material.ELYTRA)
            return;

        if (!player.isGliding())
            return;

        player.setGliding(false);

    }

}
