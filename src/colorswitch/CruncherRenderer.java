package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * Classe qui fait le rendu du cruncher.
 */
public class CruncherRenderer extends Renderer {

    private final Cruncher cruncher;
    private final int side;

    /**
     * Constructeur de ce renderer.
     * 
     * @param cruncher l'instance du cruncher à afficher.
     * @param side détermine les côtés dessinés : -1 -> gauche seulement,
     * 1 -> droit seulement, 0 -> les deux côté (full cruncher)
     */
    public CruncherRenderer(Cruncher cruncher, int side) {
        this.cruncher = cruncher;
        this.side = side;
    }

    
    /**
     * Affiche les rectangles représentant le cruncher.
     * 
     * @param level le niveau du jeu (permet de calculer la position en y)
     * @param context le context dans lequel on doit afficher
     */
    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, cruncher.getY());

        context.setFill(Renderer.convertColor(cruncher.getColor()));

        // moitié droite
        if (side < 1) {
            context.fillRect(
                    cruncher.getX() - cruncher.getWidth() / 2,
                    canvasY - cruncher.getHeight() / 2,
                    cruncher.getWidth(),
                    cruncher.getHeight());
        }

        // moitié gauche
        if (side > -1) {
            context.fillRect(
                    cruncher.getX2() - cruncher.getWidth() / 2,
                    canvasY - cruncher.getHeight() / 2,
                    cruncher.getWidth(),
                    cruncher.getHeight());
        }
    }

}
