package gremlins;

import java.util.List;

public class GameWindow {
    private GameEngine gameEngine;

    public GameWindow(GameEngine gameEngine) {this.gameEngine = gameEngine; }

    public void update(App app) {
        List<List<Block>> level = this.gameEngine.getMap();
        int xSize = this.gameEngine.getMap().size();
        int ySize = this.gameEngine.getMap().get(0).size();

        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                level.get(i).get(j).update(app);
                if (level.get(i).get(j).isRemoved()) {
                    level.get(i).set(j, this.gameEngine.crateSpace(j, i));
                }
                level.get(i).get(j).update(app);
            }
        }

        this.gameEngine.wizardMove();;
        this.gameEngine.getWizard().update(app);

        this.gameEngine.gremlinsMove();
        for (Gremlin gremlin : this.gameEngine.getGremlins()) {
            gremlin.update(app);
        }
    }
}
