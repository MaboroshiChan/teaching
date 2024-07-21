package gremlins;

import java.util.ArrayList;
import java.util.List;

public class Gremlin extends BlockMove{
    private final int speed = 1;
    private List<Slime> slimes = new ArrayList<>();

    public Gremlin(int xCoordinate,
                   int yCoordinate,
                   int currentX,
                   int currentY,
                   boolean collision,
                   BlockType blockType) {
        super(xCoordinate, yCoordinate, currentX, currentY, collision, blockType, 1);
    }

    @Override
    public void update(App app) {
        app.image(app.gremlin, this.currentX, this.currentY);

        if (this.slimes.size() != 0) {
            for (Slime slim : this.slimes) {
                slim.update(app);
            }
        }
    }

    public void addSlime() {
        Slime slime = new Slime(this.xCoordinate, this.yCoordinate, this.currentX, this.currentY, true, BlockType.Slime, 4);
        slime.setDirection(this.direction);
        this.slimes.add(slime);
    }

    public List<Slime> getSlimes() { return slimes; }
}
