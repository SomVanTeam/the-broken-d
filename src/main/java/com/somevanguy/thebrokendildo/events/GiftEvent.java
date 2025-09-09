package com.somevanguy.thebrokendildo.events;

import com.somevanguy.thebrokendildo.ModCommon;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;

public class GiftEvent extends ModEvent {
    public GiftEvent(String name, int stage, ItemLike giftItem, int giftItemCount) {
        super(name, stage, (ServerLevel level) -> {
            ServerPlayer target = ModCommon.getRandomPlayer(level);
            if (target==null) return;
            BlockPos pos = target.getRespawnPosition();
            if (pos==null) pos = target.getOnPos();
            placingGift:
            for (int y = 0; y <= 2; y++) {
                for (int z = -3; z <= 3; z++) {
                    for (int x = -3; x <= 3; x++) {
                        BlockPos chestPos = ModCommon.addBlockPos(pos, x, y, z);
                        if (ModCommon.isAir(level, chestPos)) {
                            ItemStack giftItemStack = new ItemStack(giftItem);
                            giftItemStack.applyComponents(DataComponentMap.builder().set(DataComponents.ITEM_NAME,
                                    Component.translatable("item.thebrokendildo.gift")).build());
                            giftItemStack.setCount(giftItemCount);
                            BlockState chestBlockState = Blocks.CHEST.defaultBlockState();
                            ChestBlockEntity chestEntity = new ChestBlockEntity(chestPos, chestBlockState);
                            chestEntity.setItem(13, giftItemStack);
                            chestEntity.setItem(12, new ItemStack(Items.REDSTONE_TORCH));
                            chestEntity.setItem(14, new ItemStack(Items.REDSTONE_TORCH));
                            chestEntity.setItem(22, new ItemStack(Items.REDSTONE_TORCH));
                            chestEntity.setItem(4, new ItemStack(Items.REDSTONE_TORCH));
                            chestEntity.setChanged(); // important so the chunk gets saved and not discarded on reload
                            level.setBlockAndUpdate(chestPos, chestBlockState);
                            level.setBlockEntity(chestEntity);
                            BlockPos signPos = ModCommon.addBlockPos(chestPos, 0, 1 ,0);
                            break placingGift;
                        }
                    }
                }
            }
        });
    }
}
