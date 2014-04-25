package ru.terra.universal.worldserver.world;

import ru.terra.universal.shared.entity.WorldEntity;
import ru.terra.universal.shared.world.World;
import ru.terra.universal.shared.world.WorldControlInterface;
import ru.terra.universal.shared.persistance.impl.JsonWorldLoaderImpl;
import ru.terra.universal.shared.persistance.impl.JsonWorldSaverImpl;

import java.util.List;

/**
 * Date: 24.04.14
 * Time: 20:00
 */
public class WorldThread implements Runnable, WorldControlInterface {

    private World world;
    private boolean stop = false;

    public WorldThread() {
        world = new World(new JsonWorldLoaderImpl(), new JsonWorldSaverImpl(), "uid");
    }

    @Override
    public void run() {
        world.load();
        while (!stop) {
            world.tick();
        }
    }

    public synchronized List<WorldEntity> getEntities() {
        return world.getEntities();
    }

    @Override
    public void stop() {
        stop = true;
    }
}
