package com.somevanguy.thebrokendildo;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.util.List;

// used for static common methods found in other classes
public abstract class ModCommon {
    public static BlockPos addBlockPos(BlockPos pos, int x, int y, int z) {
        return new BlockPos(pos.getX()+x, pos.getY()+y, pos.getZ()+z);
    }
    // i dont think this will be used ever
    public static BlockPos addBlockPos(BlockPos pos, BlockPos pos2) {
        return new BlockPos(pos.getX()+pos2.getX(), pos.getY()+pos2.getX(), pos.getZ()+pos2.getX());
    }
    public static boolean isAir(ServerLevel level, BlockPos pos) {
        Block block = level.getBlockState(pos).getBlock();
        return block == Blocks.AIR || block == Blocks.CAVE_AIR || block == Blocks.VOID_AIR;
    }
    public static int randomNegate() {
        return TheBrokenDildoMod.EVENTRNG.nextIntBetweenInclusive(0,1)*2-1;
    }
    @Nullable
    public static ServerPlayer getRandomPlayer(ServerLevel level) {
        List<ServerPlayer> playerList = level.players();
        if (!playerList.isEmpty()) {
            return playerList.get(
                    TheBrokenDildoMod.EVENTRNG.nextIntBetweenInclusive(0, playerList.size()-1));
        }
        return null;
    }
}
