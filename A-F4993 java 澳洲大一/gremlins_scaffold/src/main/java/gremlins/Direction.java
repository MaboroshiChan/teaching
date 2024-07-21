package gremlins;

public enum Direction {
    UP(0, 1),
    DOWN(1, 0),
    LEFT(2, 3),
    RIGHT(3, 2),
    NONE(-1, -1);

    public int index;
    public int opposite;

    Direction(int index, int opposite) {
        this.index = index;
        this.opposite = opposite;
    }
}
