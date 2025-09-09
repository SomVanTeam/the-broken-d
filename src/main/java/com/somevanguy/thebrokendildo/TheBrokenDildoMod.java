package com.somevanguy.thebrokendildo;

import com.somevanguy.thebrokendildo.events.ModEvent;
import com.somevanguy.thebrokendildo.events.ModEvents;
import com.somevanguy.thebrokendildo.items.ModItems;
import net.minecraft.util.RandomSource;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import java.util.ArrayList;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(TheBrokenDildoMod.MOD_ID)
public class TheBrokenDildoMod {
    public static final String MOD_ID = "thebrokendildo";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final RandomSource EVENTRNG = RandomSource.create();
    public static ArrayList<ModEvent> EVENTS0 = new ArrayList<>();
    public static ArrayList<ModEvent> EVENTS1 = new ArrayList<>();
    public static ArrayList<ModEvent> EVENTS2 = new ArrayList<>();
    public static boolean LOADED = false;

    public TheBrokenDildoMod(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(this);
        ModGameRules.register();
        ModSounds.register(modEventBus);
        ModItems.register(modEventBus);
        new ModEvents(); // automagically registers everything
        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.Circuit_Bread.get());
            event.accept(ModItems.Nulls_Dildo.get());
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
