package ru.terra.universal.client;

import org.apache.log4j.BasicConfigurator;
import ru.terra.universal.client.game.GameStateHolder;
import ru.terra.universal.client.gui.GuiManager;

public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        GameStateHolder.getInstance().setGameState(GameStateHolder.GameState.INIT);
        GuiManager.getInstance().startLoginWindow();
    }
}
