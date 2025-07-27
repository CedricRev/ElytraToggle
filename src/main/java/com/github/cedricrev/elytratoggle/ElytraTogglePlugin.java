package com.github.cedricrev.elytratoggle;

import com.github.cedricrev.elytratoggle.listener.ElytraJumpListener;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class ElytraTogglePlugin extends JavaPlugin {

    @Override
    public void onLoad() {
        PacketEvents.getAPI().getEventManager().registerListener(new ElytraJumpListener(), PacketListenerPriority.NORMAL);
    }

}
