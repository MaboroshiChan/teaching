package gremlins;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.io.*;


public class App extends PApplet {

    public static final int WIDTH = 720;
    public static final int HEIGHT = 720;
    public static final int SPRITESIZE = 20;
    public static final int BOTTOMBAR = 60;

    public static final int FPS = 60;

    public static final Random randomGenerator = new Random();

    public String configPath;

    public PImage brickwall;
    public PImage stonewall;
    public PImage gremlin;
    public PImage slime;
    public PImage fireball;
    public Map<Direction, PImage> wizards = new HashMap<>();
    public PImage exitdoor;
    public PImage[] destroyedBrickWalls = new PImage[4];

    private GameWindow gameWindow;
    private GameEngine gameEngine;


    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
    */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
    */
    public void setup() {
        frameRate(FPS);

        // Load images during setup
        this.stonewall = loadImage(this.getClass().getResource("stonewall.png").getPath().replace("%20", " "));
        this.brickwall = loadImage(this.getClass().getResource("brickwall.png").getPath().replace("%20", " "));

        this.gremlin = loadImage(this.getClass().getResource("gremlin.png").getPath().replace("%20", " "));
        this.slime = loadImage(this.getClass().getResource("slime.png").getPath().replace("%20", " "));
        this.fireball = loadImage(this.getClass().getResource("fireball.png").getPath().replace("%20", " "));
        this.exitdoor = loadImage(this.getClass().getResource("exitdoor.png").getPath().replace("%20", " "));

        this.wizards.put(Direction.LEFT, loadImage(this.getClass().getResource("wizard_left.png").getPath().replace("%20", " ")));
        this.wizards.put(Direction.UP, loadImage(this.getClass().getResource("wizard_leftup.png").getPath().replace("%20", " ")));
        this.wizards.put(Direction.RIGHT, loadImage(this.getClass().getResource("wizard_right.png").getPath().replace("%20", " ")));
        this.wizards.put(Direction.DOWN, loadImage(this.getClass().getResource("wizard_rightdown.png").getPath().replace("%20", " ")));

        this.destroyedBrickWalls[0] = loadImage(this.getClass().getResource("brickwall_destroyed0.png").getPath().replace("%20", " "));
        this.destroyedBrickWalls[1] = loadImage(this.getClass().getResource("brickwall_destroyed1.png").getPath().replace("%20", " "));
        this.destroyedBrickWalls[2] = loadImage(this.getClass().getResource("brickwall_destroyed2.png").getPath().replace("%20", " "));
        this.destroyedBrickWalls[3] = loadImage(this.getClass().getResource("brickwall_destroyed3.png").getPath().replace("%20", " "));


        JSONObject conf = loadJSONObject(new File(this.configPath));

        GameEngine gameEngine = new GameEngine(this.SPRITESIZE, conf, FPS);
        this.gameEngine = gameEngine;

        GameWindow gameWindow = new GameWindow(gameEngine);
        this.gameWindow = gameWindow;

    }

    /**
     * Receive key pressed signal from the keyboard.
    */
    public void keyPressed(){
        if (this.keyCode == UP) {
            this.gameEngine.setWizardDirection(Direction.UP);
        }
        if (this.keyCode == DOWN) {
            this.gameEngine.setWizardDirection(Direction.DOWN);
        }
        if (this.keyCode == LEFT) {
            this.gameEngine.setWizardDirection(Direction.LEFT);
        }
        if (this.keyCode == RIGHT) {
            this.gameEngine.setWizardDirection(Direction.RIGHT);
        }
        if (this.keyCode == 32) {
              this.gameEngine.wizardShoot();
        }
    }
    
    /**
     * Receive key released signal from the keyboard.
    */
    public void keyReleased(){
        if ((this.keyCode == UP && this.gameEngine.getWizard().getDirection() == Direction.UP) ||
                (this.keyCode == DOWN && this.gameEngine.getWizard().getDirection() == Direction.DOWN) ||
                (this.keyCode == LEFT && this.gameEngine.getWizard().getDirection() == Direction.LEFT) ||
                (this.keyCode == RIGHT && this.gameEngine.getWizard().getDirection() == Direction.RIGHT)) {
            this.gameEngine.getWizard().setCurrentFacing(Direction.NONE);
        }
    }


    /**
     * Draw all elements in the game by current frame. 
	 */
    public void update() {
        background(190, 152, 113);
        this.gameWindow.update(this);
    }

    public static void main(String[] args) {
        PApplet.main("gremlins.App");
    }
}
