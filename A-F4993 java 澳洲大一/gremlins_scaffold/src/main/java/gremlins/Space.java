package gremlins;

public class Space extends Block{

    public Space(int xCoordinate, int yCoordinate, int currentX, int currentY, BlockType blockType, boolean collision) {
        super(xCoordinate, yCoordinate, currentX, currentY, collision, blockType);
    }

    @Override
    public void update(App app) {
    }

    @Override
    public void change() {
    }
}
