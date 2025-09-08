package com.somevanguy.thebrokendildo;

import com.somevanguy.thebrokendildo.events.ModBaseEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.ArrayList;

@EventBusSubscriber(modid = TheBrokenDildoMod.MOD_ID)
public class ModMinecraftEvents {
    @SubscribeEvent
    public static void onLoad(LevelEvent.Load loadEvent) {
        if (!loadEvent.getLevel().isClientSide()) {
            TheBrokenDildoMod.LOADED = true;
        }
    }

    @SubscribeEvent
    public static void onUnload(LevelEvent.Unload unloadEvent) {
        if (!unloadEvent.getLevel().isClientSide()) {
            TheBrokenDildoMod.LOADED = false;
        }
    }

    public static ModBaseEvent getRandomEvent(ArrayList<ModBaseEvent> eventList) {
        return eventList.get(TheBrokenDildoMod.EVENTRNG.nextIntBetweenInclusive(1, eventList.size()));
    }

    public static ModBaseEvent getRandomEvent(int stage) {
        switch (TheBrokenDildoMod.EVENTRNG.nextIntBetweenInclusive(0,2)) {
            default:
                return getRandomEvent(TheBrokenDildoMod.EVENTS0);
            case 1:
                return getRandomEvent(TheBrokenDildoMod.EVENTS1);
            case 2:
                return getRandomEvent(TheBrokenDildoMod.EVENTS2);
        }
    }

    @SubscribeEvent
    public static void onTick(LevelTickEvent.Post tickEvent) {
        Level level = tickEvent.getLevel();
        ServerLevel serverLevel = (ServerLevel)level;//level.getServer().getLevel(level.dimension());
        // for SOME FUCKING reason the tick event fires in all dimensions even if there are no players, so gotta check
        // i wasted almost an hour just debugging this shit
        if (TheBrokenDildoMod.LOADED&&!level.players().isEmpty()&&!level.isClientSide()) {
            if (TheBrokenDildoMod.EVENTRNG.nextIntBetweenInclusive(1,
                    (int)(24000/ModGameRules.intValue(serverLevel, ModGameRules.EVENTS_PER_DAY)))==1) {
                // i dont know why there isnt a direct method
                TheBrokenDildoMod.LOGGER.info("event trigger");
                try {
                    getRandomEvent(Math.clamp(
                            ModGameRules.intValue(serverLevel, ModGameRules.CURRENT_STAGE),0,2))
                            .invoke(serverLevel);
                } catch (Exception e) {
                    TheBrokenDildoMod.LOGGER.warn(e.toString());
                };
            } else if (ModGameRules.boolValue(serverLevel, ModGameRules.DO_EVENTS_PER_DAY_SPINUP)
                    &&TheBrokenDildoMod.EVENTRNG.nextIntBetweenInclusive(1,
                    16000+(ModGameRules.intValue(serverLevel, ModGameRules.EVENTS_PER_DAY)*9500)) == 1) {
                ModGameRules.setIntValue(serverLevel,
                        1+ModGameRules.intValue(serverLevel, ModGameRules.EVENTS_PER_DAY),
                        ModGameRules.EVENTS_PER_DAY);
            }
        }
    }
}