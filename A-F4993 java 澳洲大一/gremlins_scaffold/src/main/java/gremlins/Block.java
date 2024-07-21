package gremlins;

public abstract class Block {

    protected int xCoordinate;
    protected int yCoordinate;
    protected int currentX;
    protected int currentY;

    protected boolean collision;
    protected boolean removed = false;

    protected BlockType blockType;

    protected Block(int xCoordinate, int yCoordinate, int currentX, int currentY, boolean collision, BlockType blockType) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.currentX = currentX;
        this.currentY = currentY;
        this.blockType = blockType;
        this.collision = collision;
    }

    public abstract void update(App app);

    public abstract void change();

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public boolean isRemoved() {
        return removed;
    }

    public boolean isCollision() {
        return collision;
    }
}
