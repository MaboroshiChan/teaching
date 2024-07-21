package gremlins;

public class BlockMove extends Block{
    private final int speed;
    protected Direction direction;

    public BlockMove(int xCoordinate, int yCoordinate, int currentX, int currentY, boolean collision, BlockType blockType, int speed) {
        super(xCoordinate, yCoordinate, currentX, currentY, collision, blockType);
        this.speed = speed;
    }

    @Override
    public void update(App app) {
    }

    @Override
    public void change() {
        if (this.direction == Direction.UP) {
            this.currentY -= this.speed;
        }
        if (this.direction == Direction.DOWN) {
            this.currentY += this.speed;
        }
        if (this.direction == Direction.LEFT) {
            this.currentX -= this.speed;
        }
        if (this.direction == Direction.RIGHT) {
            this.currentX += this.speed;
        }
    }

    public Direction getDirection() { return direction; }

    public int getSpeed() {
        return speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
