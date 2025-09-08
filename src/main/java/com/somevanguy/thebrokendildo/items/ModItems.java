package com.somevanguy.thebrokendildo.items;

import com.somevanguy.thebrokendildo.TheBrokenDildoMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Optional;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, TheBrokenDildoMod.MOD_ID);

    public static final DeferredHolder<Item, Item> Nulls_Dildo = ITEMS.register("nulls_dildo", () -> new NullsDildoItem());
    public static final DeferredHolder<Item, Item> Circuit_Bread = ITEMS.register("circuit_bread",
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
