package gremlins;

import java.util.ArrayList;
import java.util.List;

public class Wizard extends BlockMove {
    private final int speed = 2;
    private Direction currentFacing;
    private List<Fireball> fireBalls = new ArrayList<>();

    public Wizard(int xCoordinate, int yCoordinate, int currentX, int currentY, boolean collision, BlockType blockType) {
        super(xCoordinate, yCoordinate, currentX, currentY, collision, blockType, 2);
        this.direction = Direction.RIGHT;
        this.currentFacing = Direction.NONE;
    }

    public void produceFireballs() {
        Fireball fireball = new Fireball(xCoordinate, yCoordinate, currentX, currentY, true, BlockType.Fireball, 2);
        fireball.setDirection(this.direction);
        this.fireBalls.add(fireball);
    }

    @Override
    public void update(App app) {
        app.image(app.wizards.get(this.direction), this.currentX, this.currentY);

        if (this.fireBalls.size() != 0) {
            for (Fireball fireball : this.fireBalls) {
                fireball.update(app);
            }
        }
    }

    @Override
    public void change() {
    }

    public Direction getCurrentFacing() {
        return currentFacing;
    }

    public void setCurrentFacing(Direction currentFacing) {
        this.currentFacing = currentFacing;
    }

    public void reset() {
        this.currentX = this.xCoordinate;
        this.currentY = this.yCoordinate;
        this.direction = Direction.RIGHT;
        this.currentFacing = Direction.NONE;
    }

    public List<Fireball> getFireBalls() {
        return fireBalls;
    }
}
