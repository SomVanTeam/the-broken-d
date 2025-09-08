package com.somevanguy.thebrokendildo.events;

import com.somevanguy.thebrokendildo.TheBrokenDildoMod;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public class ModCustomEvents {
    private static Player getRandomPlayer(ServerLevel level) {
        List<ServerPlayer> playerList = level.players();
        while (playerList.isEmpty()) {
            playerList = level.players();
        }
        return playerList.get(
                TheBrokenDildoMod.EVENTRNG.nextIntBetweenInclusive(0, playerList.size()-1));
    }
    public static final ModBaseEvent Disc11Give = new ModBaseEvent(0, (ServerLevel level) -> {
        getRandomPlayer(level).addItem(new ItemStack(Items.MUSIC_DISC_11));
    });
}
