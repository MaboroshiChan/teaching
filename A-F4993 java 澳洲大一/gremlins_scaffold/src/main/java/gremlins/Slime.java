package gremlins;

public class Slime extends BlockMove{
    private final int speed = 4;
    protected Slime(int xCoordinate, int yCoordinate, int currentX, int currentY, boolean collide, BlockType BlockType, int speed) {
        super(xCoordinate, yCoordinate, currentX, currentY, collide, BlockType, speed);
    }

    @Override
    public void update(App app) {
        app.image(app.slime, this.currentX, this.currentY);
    }

    @Override
    public void change() {

    }

    public void move() {
    }
}
