package com.somevanguy.thebrokendildo.events;

import com.somevanguy.thebrokendildo.LevelRunnable;
import com.somevanguy.thebrokendildo.ModCommon;
import com.somevanguy.thebrokendildo.TheBrokenDildoMod;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GiftEvent extends ModEvent {
    public GiftEvent(String name, int stage, Item giftItem, int giftItemCount) {
        super(name, stage, (ServerLevel level) -> {
            ServerPlayer target = ModCommon.getRandomPlayer(level);
            if (target==null) return;
            BlockPos pos = target.getRespawnPosition();
            if (pos==null) pos = target.getOnPos();
            TheBrokenDildoMod.LOGGER.info("gift continuing");
            placingGift:
            for (int x = -3; x <= 3; x++) {
                for (int z = -3; z <= 3; z++) {
                    for (int y = 0; y <= 1; y++) {
                        BlockPos chestPos = ModCommon.addBlockPos(pos, x, y, z);
                        if (ModCommon.isAir(level, chestPos)) {
                            ItemStack giftItemStack = new ItemStack(giftItem);
                            giftItemStack.setCount(giftItemCount);
                            BlockState chestBlockState = Blocks.CHEST.defaultBlockState();
                            ChestBlockEntity chestEntity = new ChestBlockEntity(chestPos, chestBlockState);
                            chestEntity.setItem(13, giftItemStack);
                            chestEntity.setItem(12, new ItemStack(Items.REDSTONE_TORCH));
                            chestEntity.setItem(14, new ItemStack(Items.REDSTONE_TORCH));
                            chestEntity.setItem(22, new ItemStack(Items.REDSTONE_TORCH));
                            chestEntity.setItem(4, new ItemStack(Items.REDSTONE_TORCH));
                            level.setBlockAndUpdate(chestPos, chestBlockState);
                            level.setBlockEntity(chestEntity);
                            break placingGift;
                        }
                    }
                }
            }
        });
    }
}
