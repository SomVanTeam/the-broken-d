package com.somevanguy.thebrokendildo;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.core.jmx.Server;

public class ModGameRules {
    public static GameRules.Key<GameRules.IntegerValue> EVENTS_PER_DAY;
    public static GameRules.Key<GameRules.BooleanValue> DO_EVENTS_PER_DAY_SPINUP;
    public static GameRules.Key<GameRules.IntegerValue> CURRENT_STAGE;
    public static GameRules.Key<GameRules.BooleanValue> DO_STAGE_AUTO_INCREASE;
    public static int intValue(ServerLevel level, GameRules.Key<GameRules.IntegerValue> key) {
        return level.getLevelData().getGameRules().getRule(key).get();
    }
    public static void setIntValue(ServerLevel level, int value, GameRules.Key<GameRules.IntegerValue> key) {
        level.getLevelData().getGameRules().getRule(key).set(value, level.getServer());
    }
    public static boolean boolValue(ServerLevel level, GameRules.Key<GameRules.BooleanValue> key) {
        return level.getLevelData().getGameRules().getRule(key).get();
    }
    // im not even sure that this is used ever
    public static void setBoolValue(ServerLevel level, boolean value, GameRules.Key<GameRules.BooleanValue> key) {
        level.getLevelData().getGameRules().getRule(key).set(value, level.getServer());
    }
    public static void register() {
        EVENTS_PER_DAY = GameRules.register("eventsPerDay", GameRules.Category.SPAWNING,
                GameRules.IntegerValue.create(1));
        DO_EVENTS_PER_DAY_SPINUP = GameRules.register("doEventsPerDaySpinup", GameRules.Category.SPAWNING,
                GameRules.BooleanValue.create(true));
        CURRENT_STAGE = GameRules.register("currentStage", GameRules.Category.SPAWNING,
                GameRules.IntegerValue.create(0));
        DO_STAGE_AUTO_INCREASE = GameRules.register("doStageAutoIncrease", GameRules.Category.SPAWNING,
                GameRules.BooleanValue.create(true));
    }
}
