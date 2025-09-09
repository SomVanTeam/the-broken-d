package com.somevanguy.thebrokendildo.events;

import com.somevanguy.thebrokendildo.LevelRunnable;
import com.somevanguy.thebrokendildo.TheBrokenDildoMod;
import net.minecraft.server.level.ServerLevel;

public class ModEvent {
    public int stage;
    public LevelRunnable onInvoke;
    public String name;
    public ModEvent(String name, int stage, LevelRunnable onInvoke) {
        this.stage = stage;
        this.onInvoke = onInvoke;
        this.name = name;
        switch (stage) {
            default -> TheBrokenDildoMod.EVENTS0.add(this);
            case 1 -> TheBrokenDildoMod.EVENTS1.add(this);
            case 2 -> TheBrokenDildoMod.EVENTS2.add(this);
        }
    }
    public void invoke(ServerLevel level) {
        this.onInvoke.run(level);
    }
}