package com.somevanguy.thebrokendildo.events;

import com.somevanguy.thebrokendildo.items.ModItems;
import com.somevanguy.thebrokendildo.TheBrokenDildoMod;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import javax.annotation.Nullable;
import java.util.List;

public class ModCustomEvents {
    @Nullable
    private static Player getRandomPlayer(ServerLevel level) {
        List<ServerPlayer> playerList = level.players();
        return playerList.get(
                TheBrokenDildoMod.EVENTRNG.nextIntBetweenInclusive(0, playerList.size()-1));
    }
    // stage 0
    public static final ModBaseEvent Disc11Give = new ModBaseEvent(0, (ServerLevel level) -> {
        getRandomPlayer(level).addItem(new ItemStack(Items.MUSIC_DISC_11));
    });
    public static final ModBaseEvent BadMaceGive = new ModBaseEvent(0, (ServerLevel level) -> {
        ItemStack mace = new ItemStack(Items.MACE);
        mace.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);
        mace.set(DataComponents.MAX_DAMAGE, 1);
        getRandomPlayer(level).addItem(mace);
    });
    // stage 1
    public static final ModBaseEvent CircuitBreadGive = new ModBaseEvent(1, (ServerLevel level) -> {
        getRandomPlayer(level).addItem(new ItemStack(ModItems.Circuit_Bread));
    });
}
