package com.somevanguy.thebrokendildo.items;

import com.somevanguy.thebrokendildo.ModSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class NullsDildoItem extends Item {
    public NullsDildoItem() {
        super(new Properties().rarity(Rarity.EPIC).durability(676767));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        level.playSound(player, player.getOnPos(), ModSounds.Goon.get(), SoundSource.MASTER);
        if (level instanceof ServerLevel) {
            player.getItemInHand(usedHand).hurtAndBreak(1, (ServerLevel) level, null, a -> {});
        }
         return InteractionResultHolder.success(player.getItemInHand(usedHand));
    }
}
