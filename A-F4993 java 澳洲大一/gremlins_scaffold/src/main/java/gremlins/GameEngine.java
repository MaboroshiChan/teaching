package gremlins;

import processing.data.JSONArray;
import processing.data.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GameEngine {
    private int lives;
    private final int spriteSize;
    private final JSONObject config;
    private final int fps;
    private List<Level> levels = new ArrayList<>();
    private int indexLevel = 0;
    private List<List<Block>> currentIndex = new ArrayList<>();

    private final Random random = new Random();
    private final Map<Integer, Direction> directionMap = new HashMap<>();

    private Wizard wizard;
    private boolean wizardMove = false;
    private int wizardCoolDown = 0;

    private List<Gremlin> gremlins = new ArrayList<>();
    private int gremlinsCoolDown = 0;

    public GameEngine(int spriteSize, JSONObject config, int fps) {
        this.spriteSize = spriteSize;
        this.config = config;
        this.fps = fps;

        this.directionMap.put(0, Direction.UP);
        this.directionMap.put(1, Direction.DOWN);
        this.directionMap.put(2, Direction.LEFT);
        this.directionMap.put(3, Direction.RIGHT);

        this.readJson(this.config);
        this.readLayout();
    }

    private void readJson(JSONObject jsonObject) {
        JSONArray levels = jsonObject.getJSONArray("levels");
        for (int i = 0; i < levels.size(); i++) {
            String layout = levels.getJSONObject(i).getString("layout");
            double wizardCoolDown = levels.getJSONObject(i).getDouble("wizard_cooldown");
            double enemyCoolDown = levels.getJSONObject(i).getDouble("enemy_cooldown");

            Level level = new Level(enemyCoolDown, wizardCoolDown, layout);
            this.levels.add(level);
        }

        this.lives = jsonObject.getInt("lives");
    }

    private void readLayout() {
        Level level = this.levels.get(this.indexLevel);
        try {
            File file = new File(level.getLayout());
            Scanner s = new Scanner(file);
            int j = 0;
            while (s.hasNextLine()) {
                String line = s.nextLine().trim();
                List<Block> eachRow = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == 'X') {
                        StoneWall stoneWall = new StoneWall(
                                this.spriteSize * i,
                                this.spriteSize * j,
                                this.spriteSize * i,
                                this.spriteSize * j,
                                false,
                                BlockType.StoneWall);
                        eachRow.add(stoneWall);
                    }
                    else if (line.charAt(i) == 'B') {
                        BrickWall brickWall = new BrickWall(
                                this.spriteSize * i,
                                this.spriteSize * j,
                                this.spriteSize * i,
                                this.spriteSize * j,
                                false,
                                BlockType.BrickWall);
                        eachRow.add(brickWall);
                    }
                    else if (line.charAt(i) == ' ') {
                        Space space = new Space(
                                this.spriteSize * i,
                                this.spriteSize * j,
                                this.spriteSize * i,
                                this.spriteSize * j,
                                BlockType.Space,
                                true);
                        eachRow.add(space);
                    }
                    else if (line.charAt(i) == 'E') {
                        ExitDoor exitDoor = new ExitDoor(
                                this.spriteSize * i,
                                this.spriteSize * j,
                                this.spriteSize * i,
                                this.spriteSize * j,
                                true,
                                BlockType.ExitDoor);
                        eachRow.add(exitDoor);
                    }
                    else if (line.charAt(i) == 'G') {
                        Space space = new Space(
                                this.spriteSize * i,
                                this.spriteSize * j,
                                this.spriteSize * i,
                                this.spriteSize * j,
                                BlockType.Space,
                                true);
                        eachRow.add(space);
                        Gremlin gremlin = new Gremlin(
                                this.spriteSize * i,
                                this.spriteSize * j,
                                this.spriteSize * i,
                                this.spriteSize * j,
                                true,
                                BlockType.Gremlin);
                        for (int z = 0; z < 4; z++) {
                            gremlin.setDirection(this.directionMap.get(z));
                        }
                        this.gremlins.add(gremlin);
                    }
                    else if (line.charAt(i) == 'W') {
                        Space space = new Space(this.spriteSize * i,
                                this.spriteSize * j,
                                this.spriteSize * i,
                                this.spriteSize * j,
                                BlockType.Space,
                                true);

                        this.wizard = new Wizard(
                                this.spriteSize * i,
                                this.spriteSize * j,
                                this.spriteSize * i,
                                this.spriteSize * j,
                                true,
                                BlockType.Wizard);
                        eachRow.add(space);
                    }
                }
                j++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File of Level Could Not Been Found!");
        }
    }

    public void wizardMove() {
        if (nextBlock(this.wizard).isCollision()) {
            if (this.wizard.getCurrentFacing() == Direction.NONE) {
                if ((this.wizard.getDirection() == Direction.UP || this.wizard.getDirection() == Direction.DOWN)
                        && this.wizard.getCurrentY() % this.spriteSize != 0) {
                    this.wizard.change();
                    this.wizardMove = true;
                } else if ((this.wizard.getDirection() == Direction.LEFT || this.wizard.getDirection() == Direction.RIGHT)
                        && this.wizard.getCurrentX() % this.spriteSize != 0) {
                    this.wizard.change();
                    this.wizardMove = true;
                } else {
                    this.wizardMove = false;
                }
            } else {
                this.wizard.change();
                this.wizardMove = true;
            }
        } else {
            this.wizardMove = false;
        }

        if (this.wizardCoolDown != 0) {
            this.wizardCoolDown += 1;
        }

        if (this.wizard.getFireBalls().size() != 0) {
            for (int i = 0; i < this.wizard.getFireBalls().size(); i++) {
                this.wizard.getFireBalls().get(i).change();
                Block nextBlock = nextBlock(this.wizard.getFireBalls().get(i));
                if (!nextBlock.isCollision()) {
                    this.wizard.getFireBalls().remove(i);
                    if (nextBlock.getBlockType() == BlockType.BrickWall) {
                        ((BrickWall) nextBlock).destroyed();

                    }
                }
            }
        }
    }

    public void wizardShoot() {
        if (this.wizardCoolDown == 0) {
            this.wizard.produceFireballs();
            this.wizardCoolDown += 1;
        } else if (this.wizardCoolDown >= this.levels.get(this.indexLevel).getWizardCoolDown() * this.fps) {
            this.wizardCoolDown = 0;
        }
    }

    public void gremlinsMove() {
        this.gremlinsCoolDown += 1;
        if (this.gremlinsCoolDown >= this.levels.get(this.indexLevel).getEnemyCoolDown() * this.fps) {
            this.gremlinsCoolDown = 0;
        }

        for (Gremlin gremlin : this.gremlins) {
            if (!nextBlock(gremlin).collision) {
                List<Integer> availableSteps = availableSteps(gremlin);
                if (availableSteps.size() == 1) {
                    gremlin.setDirection(this.directionMap.get(gremlin.getDirection().opposite));
                } else if (availableSteps.size() >= 2) {
                    int nextDirection = -1;
                    boolean valid = false;
                    while (!valid) {
                        nextDirection = this.random.nextInt(4);
                        for (int i : availableSteps) {
                            if (i == nextDirection && i != gremlin.getDirection().opposite) {
                                valid = true;
                                break;
                            }
                        }
                    }
                    gremlin.setDirection(this.directionMap.get(nextDirection));
                }
            }

            if (this.gremlinsCoolDown == 0) {
                gremlin.addSlime();
            }

            if (gremlin.getSlimes().size() != 0) {
                for (int i = 0; i < gremlin.getSlimes().size(); i++) {
                    gremlin.getSlimes().get(i).move();
                    Block nextBlock = nextBlock(gremlin.getSlimes().get(i));
                    if (!nextBlock.collision) {
                        gremlin.getSlimes().remove(i);
                    } else if (collied(gremlin.getSlimes().get(i), this.wizard)) {
                        this.wizard.reset();
                        this.lives -= 1;
                    }
                }
            }
        }
    }

    private List<Integer> availableSteps(Block block) {
        int blockX = block.getCurrentX() / this.spriteSize;
        int blockY = block.getCurrentY() / this.spriteSize;
        Block UP = this.currentIndex.get(blockY - 1).get(blockX);
        Block DOWN = this.currentIndex.get(blockY + 1).get(blockX);
        Block LEFT = this.currentIndex.get(blockY).get(blockX - 1);
        Block RIGHT = this.currentIndex.get(blockY).get(blockX + 1);

        List<Integer> availableSteps = new ArrayList<>();
        if (UP.collision) {
            availableSteps.add(0);
        }
        if (DOWN.collision) {
            availableSteps.add(1);
        }
        if (LEFT.collision) {
            availableSteps.add(2);
        }
        if (RIGHT.collision) {
            availableSteps.add(3);
        }
        return availableSteps;
    }

    private Block nextBlock(BlockMove block) {
        Block nextBlock = null;
        if (block.getDirection() == Direction.UP) {
            nextBlock = this.currentIndex.get((block.getCurrentY() - block.getSpeed()) / this.spriteSize).get(block.getCurrentX() / this.spriteSize);
        }
        if (block.getDirection() == Direction.DOWN) {
            nextBlock = this.currentIndex.get(block.getCurrentY() / this.spriteSize + 1).get(block.getCurrentX() / this.spriteSize);
        }
        if (block.getDirection() == Direction.LEFT) {
            nextBlock = this.currentIndex.get(block.getCurrentY() / this.spriteSize).get((block.getCurrentX() - wizard.getSpeed()) / this.spriteSize);
        }
        if (block.getDirection() == Direction.RIGHT) {
            nextBlock = this.currentIndex.get(block.getCurrentY() / this.spriteSize).get(block.getCurrentX() / this.spriteSize + 1);
        }
        return nextBlock;
    }

    public boolean collied(Block a, Block b) {
        return a.getCurrentX() + this.spriteSize >= b.getCurrentX()
                && a.getCurrentX() <= b.getCurrentX() + this.spriteSize
                && a.getCurrentY() + this.spriteSize >= b.getCurrentY()
                && a.getCurrentY() <= b.getCurrentY() + this.spriteSize;
    }

    public List<List<Block>> getMap() {
        return currentIndex;
    }

    public Wizard getWizard() {
        return wizard;
    }

    public List<Gremlin> getGremlins() {
        return gremlins;
    }

    public Block crateSpace(int j, int i) {
        return null;
    }

    public void setWizardDirection(Direction direction) {

    }
}
