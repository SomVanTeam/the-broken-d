package com.somevanguy.thebrokendildo.items;

import com.somevanguy.thebrokendildo.TheBrokenDildoMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Tool;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Optional;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TheBrokenDildoMod.MOD_ID);

    public static final DeferredItem<Item> Nulls_Dildo = ITEMS.register("nulls_dildo", () -> new NullsDildoItem());
    public static final DeferredItem<Item> Circuit_Bread = ITEMS.register("circuit_bread",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.EPIC)
                    .stacksTo(16)
                    .food(new FoodProperties(
                            -666,
                            0,
                            true,
                            8,
                            Optional.empty(),
                            List.of()))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
