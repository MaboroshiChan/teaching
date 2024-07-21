package gremlins;

public class ExitDoor extends Block{
    protected ExitDoor(int xCoordinate, int yCoordinate, int currentX, int currentY, boolean collision, BlockType blockType) {
        super(xCoordinate, yCoordinate, currentX, currentY, collision, blockType);
    }

    @Override
    public void update(App app) {
        app.image(app.exitdoor, this.currentX, this.currentY);
    }

    @Override
    public void change() {

    }
}
