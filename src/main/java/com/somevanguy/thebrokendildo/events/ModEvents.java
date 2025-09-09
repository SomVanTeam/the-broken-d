package com.somevanguy.thebrokendildo.events;

import com.somevanguy.thebrokendildo.ModCommon;
import com.somevanguy.thebrokendildo.items.ModItems;
import com.somevanguy.thebrokendildo.TheBrokenDildoMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class ModEvents {

    // ==== stage 0 ====

    public static final ModEvent Disc11Give = new ModEvent("disc11give", 0, (ServerLevel level) -> {
        ServerPlayer target = ModCommon.getRandomPlayer(level);
        if (target==null) return;
        target.addItem(new ItemStack(Items.MUSIC_DISC_11));
    });
    public static final ModEvent BadMaceGive = new ModEvent("badmacegive", 0, (ServerLevel level) -> {
        ServerPlayer target = ModCommon.getRandomPlayer(level);
        if (target==null) return;
        ItemStack mace = new ItemStack(Items.MACE);
        mace.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);
        mace.set(DataComponents.MAX_DAMAGE, 1);
        target.addItem(mace);
    });
    public static final ModEvent Hole = new ModEvent("hole", 0, (ServerLevel level) -> {
        ServerPlayer target = ModCommon.getRandomPlayer(level);
        if (target==null) return;
        BlockPos pos = target.getOnPos();
        int holePosX = pos.getX()+25*ModCommon.randomNegate();
        int holePosZ = pos.getZ()+25*ModCommon.randomNegate();
        for (int y = -64; y <= 319; y++) {
            level.setBlockAndUpdate(new BlockPos(holePosX, y, holePosZ), Blocks.AIR.defaultBlockState());
        }
    });
    public static final ModEvent DiamondGiftSmall = new GiftEvent("diamondgiftsmall", 0, Items.DIAMOND, 6);

    // ==== stage 1 ====

    public static final ModEvent DiamondGiftLarge = new GiftEvent("diamondgiftlarge", 1, Items.DIAMOND, 33);
    public static final ModEvent NetheriteScrapGift = new GiftEvent("netheritescrapgift", 1, Items.NETHERITE_SCRAP, 13);
    public static final ModEvent CircuitBreadGive = new ModEvent("circuitbreadgive", 1, (ServerLevel level) -> {
        ServerPlayer target = ModCommon.getRandomPlayer(level);
        if (target==null) return;
        target.addItem(new ItemStack(ModItems.Circuit_Bread));
    });
    public static final ModEvent LargeHole = new ModEvent("largehole", 1, (ServerLevel level) -> {
        ServerPlayer target = ModCommon.getRandomPlayer(level);
        if (target==null) return;
        BlockPos pos = target.getOnPos();
        int holePosX = pos.getX()+25*ModCommon.randomNegate();
        int holePosZ = pos.getZ()+25*ModCommon.randomNegate();
        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {
                for (int y = -64; y <= 319; y++) {
                    level.setBlockAndUpdate(new BlockPos(holePosX+x, y, holePosZ+z), Blocks.AIR.defaultBlockState());
                }
            }
        }
    });
}
