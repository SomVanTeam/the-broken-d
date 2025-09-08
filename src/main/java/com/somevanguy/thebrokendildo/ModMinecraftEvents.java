package com.somevanguy.thebrokendildo;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@EventBusSubscriber(modid = TheBrokenDildoMod.MOD_ID)
public class ModMinecraftEvents {
    @SubscribeEvent
    public static void onTick(LevelTickEvent.Post tickEvent) {
        Level level = tickEvent.getLevel();
        if (!level.isClientSide()) {
            if (TheBrokenDildoMod.EVENTRNG.nextIntBetweenInclusive(1,
                    (int)(24000/level.getGameRules().getRule(ModGameRules.EVENTS_PER_DAY).get()))==1) {
                // i dont know why there isnt a direct method
                ServerLevel serverLevel = level.getServer().getLevel(level.dimension());
                try {
                    TheBrokenDildoMod.EVENTS0.get(0).invoke(serverLevel);
                } catch (Exception e) {
                    TheBrokenDildoMod.LOGGER.warn(e.toString());
                };
                TheBrokenDildoMod.LOGGER.info("event trigger");
            }
        }
    }
}