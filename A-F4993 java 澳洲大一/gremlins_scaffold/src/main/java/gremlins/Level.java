package gremlins;

public class Level {

    private double wizardCoolDown;
    private double enemyCoolDown;
    private String layout;

    public Level(double wizardCoolDown, double enemyCoolDown, String layout) {
        this.enemyCoolDown = enemyCoolDown;
        this.wizardCoolDown = wizardCoolDown;
        this.layout = layout;
    }

    public String getLayout() {
        return layout;
    }

    public double getWizardCoolDown() {
        return wizardCoolDown;
    }

    public double getEnemyCoolDown() {
        return enemyCoolDown;
    }
}
