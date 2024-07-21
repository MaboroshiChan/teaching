package gremlins;

import java.util.List;

public class Fireball extends BlockMove{

    private final int speed = 4;

    protected Fireball(int xCoordinate, int yCoordinate, int currentX, int currentY, boolean collision, BlockType blockType, int speed) {
        super(xCoordinate, yCoordinate, currentX, currentY, collision, blockType, speed);
    }

    @Override
    public void update(App app) {
        app.image(app.fireball, this.currentX, this.currentY);
    }


}
