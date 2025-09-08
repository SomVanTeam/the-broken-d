package com.somevanguy.thebrokendildo.events;

import com.somevanguy.thebrokendildo.LevelRunnable;
import com.somevanguy.thebrokendildo.TheBrokenDildoMod;
import net.minecraft.server.level.ServerLevel;

public class ModBaseEvent {
    public int stage = 0;
    public LevelRunnable onInvoke;
    public ModBaseEvent(int stage, LevelRunnable onInvoke) {
        this.stage = stage;
        this.onInvoke = onInvoke;
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