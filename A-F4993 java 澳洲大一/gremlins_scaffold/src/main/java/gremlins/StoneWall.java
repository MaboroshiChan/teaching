package gremlins;

public class StoneWall extends Block{
    protected StoneWall(int xCoordinate, int yCoordinate, int currentX, int currentY, boolean collision, BlockType blockType) {
        super(xCoordinate, yCoordinate, currentX, currentY, collision, blockType);
    }

    @Override
    public void update(App app) {
        app.image(app.stonewall, this.currentX, this.currentY);
    }

    @Override
    public void change() {

    }
}
