package com.somevanguy.thebrokendildo;

import net.minecraft.world.level.GameRules;

public class ModGameRules {
    public static GameRules.Key<GameRules.IntegerValue> EVENTS_PER_DAY;
    public static GameRules.Key<GameRules.BooleanValue> DO_EVENTS_PER_DAY_SPINUP;
    public static GameRules.Key<GameRules.IntegerValue> CURRENT_STAGE;
    public static GameRules.Key<GameRules.BooleanValue> DO_STAGE_AUTO_INCREASE;
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
