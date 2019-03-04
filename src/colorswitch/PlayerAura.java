package colorswitch;

/**
 * Classe tenant à jour l'aura reliée à un effet
 */
public class PlayerAura {

    private final int color;
    private final int bckColor;
    private int currentColor;

    private final double delay;
    private double timeSinceActivated;

    private boolean isActive;

    
    /**
     * Constructeur de la classe Player Aura.
     * 
     * @param color la couleur de l'aura à afficher.
     * @param delay le temps pendant lequel l'effet demeure actif.
     */
    public PlayerAura(int color, double delay) {
        this.color = color;
        this.bckColor = 8;
        this.delay = delay;

        this.currentColor = color;
        this.isActive = false;
        this.timeSinceActivated = 0;
    }
    
    
    /**
     * Démarer l'effet.
     */
    public void start() {
        timeSinceActivated = 0;
        isActive = true;
    }

    
    /**
     * Accesseur de l'attribut isActive.
     * 
     * @return vrai si l'effet est actuellement actif.
     */
    public boolean isActive() {
        return isActive;
    }
    
    
    /**
     * Active/désactive l'effet.
     */
    public void toggle() {
        isActive = !isActive;
    }

    
    /**
     * Lorsque l'effet est actif, la couleur oscille entre une couleur de
     * fond et la couleur représentative de l'effet.
     * 
     * @return la couleur à afficher.
     */
    public int getCurrentColor() {
        return currentColor;
    }
    
    
    /**
     * Méthode appelée à chaque frame pour mettre à jour les attributs de
     * l'entité
     *
     * @param dt Delta-Temps en secondes
     */
    public void tick(double dt) {
        if (isActive) {
            timeSinceActivated += dt;

            if (timeSinceActivated > delay) {
                timeSinceActivated = 0;
                isActive = false;
            } else if ((timeSinceActivated * 100) % 20 > 10) {
                currentColor = color;
            } else {
                currentColor = bckColor;
            }
        }
    }
}
