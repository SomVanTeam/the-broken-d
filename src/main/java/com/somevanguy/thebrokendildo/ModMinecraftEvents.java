package com.somevanguy.thebrokendildo;

import com.somevanguy.thebrokendildo.events.ModEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;

@EventBusSubscriber(modid = TheBrokenDildoMod.MOD_ID)
public class ModMinecraftEvents {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent registerCommandsEvent) {
        new TriggerEventCommand(registerCommandsEvent.getDispatcher());
    }

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

    public static ModEvent getRandomEvent(ArrayList<ModEvent> eventList) {
        return eventList.get(TheBrokenDildoMod.EVENTRNG.nextIntBetweenInclusive(0, eventList.size()-1));
    }

    public static ModEvent getRandomEvent(int stage) {
        switch (TheBrokenDildoMod.EVENTRNG.nextIntBetweenInclusive(0,stage)) {
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
        // for SOME FUCKING reason the tick event fires in all dimensions even if there are no players, so gotta check
        // i wasted almost an hour just debugging this shit
        if (TheBrokenDildoMod.LOADED&&!level.players().isEmpty()&&!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel)level;//level.getServer().getLevel(level.dimension());
            if (TheBrokenDildoMod.EVENTRNG.nextIntBetweenInclusive(1,
                    (int)(24000/ModGameRules.intValue(serverLevel, ModGameRules.EVENTS_PER_DAY)))==1) {
                // i dont know why there isnt a direct method
                TheBrokenDildoMod.LOGGER.info("event trigger");
                //try {
                    ModEvent ev = getRandomEvent(Math.clamp(
                            ModGameRules.intValue(serverLevel, ModGameRules.CURRENT_STAGE),0,2));
                    TheBrokenDildoMod.LOGGER.info(ev.toString());
                    ev.invoke(serverLevel);
                //} catch (Exception e) {
                //    e.printStackTrace();
                //};
                TheBrokenDildoMod.LOGGER.info("event finish");
            } else if (ModGameRules.boolValue(serverLevel, ModGameRules.DO_EVENTS_PER_DAY_SPINUP)
                    &&TheBrokenDildoMod.EVENTRNG.nextIntBetweenInclusive(1,
                    20000+(ModGameRules.intValue(serverLevel, ModGameRules.EVENTS_PER_DAY)*9500)) == 1) {
                ModGameRules.setIntValue(serverLevel,
                        1+ModGameRules.intValue(serverLevel, ModGameRules.EVENTS_PER_DAY),
                        ModGameRules.EVENTS_PER_DAY);
            }
        }
    }
}