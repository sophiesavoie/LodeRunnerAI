package colorswitch;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Classe abstraite
 *
 * Définit quelques fonctions statiques utiles lors du rendu
 */
public abstract class Renderer {

    public abstract void draw(Level level, GraphicsContext context);

    /**
     * Convertie un numéro de couleur 0 à 7 en une couleur de JavaFX
     *
     * @param color le numéro de couleur
     * @return la couleur associée
     */
    public static Color convertColor(int color) {
        switch (color) {
            case 0:
                return Color.LIGHTCORAL;
            case 1:
                return Color.SPRINGGREEN;
            case 2:
                return Color.DODGERBLUE;
            case 3:
                return Color.ORANGE;
            case 4:
                return Color.ANTIQUEWHITE;  // couleur effet shield
            case 5:
                return Color.CRIMSON;       // couleur effet gravity
            case 6:
                return Color.ORANGE;        // couleur effet jetpack
            case 7:
                return Color.YELLOW;        // couleur cheat mode
            case 8:
                return Color.DARKGRAY;      // couleur oscillante pour les effets
        }

        throw new IllegalArgumentException("Couleur inconnue");
    }

    
    /**
     * Calcule la position sur l'écran d'une entité à pratir de sa position Y
     * dans le niveau.
     *
     * @param level Niveau actuel
     * @param levelY Coordonnée Y dans le niveau
     * @return La coordonnée Y dans le système de coordonnées de la fenêtre
     * JavaFX
     */
    public static double computeScreenY(Level level, double levelY) {

        double y = levelY - level.getScroll();

        return level.getScreenHeight() - y;
    }
}
