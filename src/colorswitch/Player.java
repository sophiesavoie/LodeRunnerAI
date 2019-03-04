package colorswitch;

/**
 * Classe représentant l'entité de la personne qui joue (aka, la sorcière).
 *
 * La sorcière est représentée par un cercle.
 */
public class Player extends Entity {

    private final double radius;
    private double vy;
    private final double maxVy;
    private final double ay;
    private int color;

    /**
     * Halo de couleur indicatif de l'effet actif.
     */
    private final PlayerAura shieldAura, gravityAura, jetPackAura, cheatAura;

    /**
     * Tient à jour le status de l'effet jetpack
     */
    private boolean hasJetPack;
    private boolean showJetPackInfo;
    private double timeSinceJetPackFound;

    public Player(double x, double y, double r) {
        super(x, y);

        this.color = 1;
        this.radius = r;

        this.vy = 0;
        this.maxVy = 300;
        this.ay = -400;
        
        this.shieldAura = new PlayerAura(4, 3);
        this.gravityAura = new PlayerAura(5, 6);
        this.jetPackAura = new PlayerAura(6, 1.3);
        this.cheatAura = new PlayerAura(7, Double.POSITIVE_INFINITY);

        this.hasJetPack = false;
        this.showJetPackInfo = false;
        this.timeSinceJetPackFound = 0;

        this.renderer = new PlayerRenderer(this);
    }

    
    /**
     * Accesseur du rayon du player.
     *
     * @return le rayon
     */
    public double getRadius() {
        return radius;
    }

    
    /**
     * Fonction appelée à chaque frame pour mettre à jour les attributs de
     * l'entité
     *
     * @param dt Delta-Temps en secondes
     */
    @Override
    public void tick(double dt) {
        // Mise à jour de la vitesse
        vy += jetPackAura.isActive() ? 0 :
                gravityAura.isActive() ? 2.5 * dt * ay : dt * ay;

        // Mise à jour de la position
        y += dt * vy;

        // Clip la vitesse pour rester entre -maxVy et maxVy
        vy = Math.min(vy, maxVy);
        vy = Math.max(vy, -maxVy);

        if (jetPackAura.isActive()) {
            jetPackAura.tick(dt);
        }
        if (shieldAura.isActive()) {
            shieldAura.tick(dt);
        }
        if (gravityAura.isActive()) {
            gravityAura.tick(dt);
        }

        if (showJetPackInfo) {
            timeSinceJetPackFound += dt;
            if (timeSinceJetPackFound > 2) {
                showJetPackInfo = false;
            }
        }
    }

    
    /**
     * La couleur de l'avatar du joueur.
     *
     * @return un entier représentatif de cette couleur
     */
    public int getColor() {
        return color;
    }

    
    /**
     * Remplace la couleur actuelle par une nouvelle couleur aléatoire
     */
    public void randomizeColor() {
        int newColor;

        do {
            newColor = (int) (Math.random() * 4);
        } while (newColor == this.color);

        this.color = newColor;
    }

    
    /**
     * Fait sauter la sorcière
     */
    public void jump() {
        vy = Math.max(vy, 0);
        vy += gravityAura.isActive() ? 150 : 200;
    }

    
    /**
     * Active l'effet shield.
     */
    public void shieldUp() {
        shieldAura.start();
    }

    
    /**
     * Cheat mode utilisé pour permettre au correcteur de
     * passer rapidement des obstacles.
     */
    public void toggleInvulnerability() {
        cheatAura.toggle();
    }

    
    /**
     * Retourne vrai si le joueur est invulnérable aux collisions avec les
     * obstacles. Le joueur ne peux pas ramasser un item lorsqu'il est
     * invulnérable.
     * @return vrai si le joueur est invulnérable.
     */
    public boolean isInvulnerable() {
        return (cheatAura.isActive() ||
                shieldAura.isActive() ||
                jetPackAura.isActive());
    }

    
    /**
     * Retourne l'aura de l'effect actif.
     * @return l'aura qui doit être affichée, null si aucun effet n'est activé.
     */
    public PlayerAura getAura() {
        if (cheatAura.isActive()) {
            return cheatAura;
        } else if (jetPackAura.isActive()) {
            return jetPackAura;
        } else if (shieldAura.isActive()) {
            return shieldAura;
        } else if (gravityAura.isActive()) {
            return gravityAura;
        }
        return null;
    }

    
    /**
     * Active l'effet gravity.
     */
    public void increaseGravity() {
        gravityAura.start();
    }


    /**
     * Ramasse un jetpack.
     */
    public void jetPackUp() {
        hasJetPack = true;
        showJetPackInfo = true;
    }

    
    /**
     * Détermine si le joueur a un jetpack en sa possession.
     *
     * @return true si le joueur a un jetpack.
     */
    public boolean hasJetPack() {
        return hasJetPack;
    }

    
    /**
     * Détermine si l'on doit afficher un message indiquant comment
     * utiliser le JetPack.
     *
     * @return true si un message doit être affiché.
     */
    public boolean showJetPackInfo() {
        return showJetPackInfo;
    }

    
    /**
     * Active le jetpack.
     */
    public void useJetPack() {
        if (hasJetPack) {
            jetPackAura.start();
            vy = maxVy;
        }
    }


    /**
     * Positionne le joueur selon l'axe Y
     * @param y la coordonnée en pixel
     */
    public void setY(double y) {
        this.y = y;
    }

    
    /**
     * Le point le plus au nord de la circonférence de l'avatar du joueur.
     *
     * @return la valeur de ce point
     */
    public double getNorthBorder() {
        return y + radius;
    }

    
    /**
     * Le point le plus au sud de la circonférence de l'avatar du joueur.
     *
     * @return la valeur de ce point
     */
    public double getSouthBorder() {
        return y - radius;
    }

    
    /**
     * Le point le plus à l'ouest de la circonférence de l'avatar du joueur.
     *
     * @return la valeur de ce point
     */
    public double getWestBorder() {
        return x - radius;
    }

    
    /**
     * Le point le plus à l'est de la circonférence de l'avatar du joueur.
     *
     * @return la valeur de ce point
     */
    public double getEastBorder() {
        return x + radius;
    }

    
    /**
     * Largeur de l'avatar du joueur
     *
     * @return la valeur en pixel
     */
    @Override
    public double getWidth() {
        return this.getRadius() * 2;
    }

    
    /**
     * Hauteur de l'avatar du joueur
     *
     * @return la valeur en pixel
     */
    @Override
    public double getHeight() {
        return this.getRadius() * 2;
    }
}
