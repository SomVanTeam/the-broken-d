package com.somevanguy.thebrokendildo.events;

import net.minecraft.server.level.ServerLevel;

public interface LevelRunnable {
    void run(ServerLevel level);
}