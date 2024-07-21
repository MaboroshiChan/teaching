package gremlins;

public class BrickWall extends Block{

    private int currentFrame = 0;
    private int destroyedIndex = 0;
    private boolean destroyed = false;

    protected BrickWall(int xCoordinate, int yCoordinate, int currentX, int currentY, boolean collision, BlockType blockType) {
        super(xCoordinate, yCoordinate, currentX, currentY, collision, blockType);
    }

    @Override
    public void update(App app) {
        if (!this.destroyed) {
            app.image(app.brickwall, this.currentX, this.currentY);
        } else {
            if (this.currentFrame == 4) {
                this.currentFrame = 0;
                this.destroyedIndex += 1;
            }
            if (this.destroyedIndex > 3) {
                this.removed = true;
                return;
            }
            app.image(app.destroyedBrickWalls[this.destroyedIndex], this.currentX, this.currentY);
            this.currentFrame += 1;
        }

    }

    @Override
    public void change() {

    }

    public void destroyed() { this.destroyed = true; }
}
