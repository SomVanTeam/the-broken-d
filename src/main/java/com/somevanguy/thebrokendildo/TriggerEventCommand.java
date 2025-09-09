package com.somevanguy.thebrokendildo;

import com.mojang.brigadier.CommandDispatcher;
import com.somevanguy.thebrokendildo.events.ModEvent;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class TriggerEventCommand {
    private void registerTriggerEventCommand(ModEvent event, CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("triggerevent").then(
                        Commands.literal("stage"+event.stage).then(
                                Commands.literal(event.name).executes((command) -> {
                                    event.invoke(command.getSource().getLevel());
                                    return 0;
                                })
                        )));
    }

    public TriggerEventCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        TheBrokenDildoMod.EVENTS0.forEach((event) -> {
            registerTriggerEventCommand(event, dispatcher);
        });
        TheBrokenDildoMod.EVENTS1.forEach((event) -> {
            registerTriggerEventCommand(event, dispatcher);
        });
        TheBrokenDildoMod.EVENTS2.forEach((event) -> {
            registerTriggerEventCommand(event, dispatcher);
        });
    }
}
