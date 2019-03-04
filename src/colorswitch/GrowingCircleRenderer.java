package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * Classe qui fait le rendu d'un cercle qui grossit et rétrécit avec le temps.
 */
public class GrowingCircleRenderer extends Renderer {
    private final GrowingCircle growingCircle;
    
    
    /**
     * Constructeur de la classe GrowingCircleRenderer.
     * 
     * @param growingCircle Le cercle
     */
    public GrowingCircleRenderer(GrowingCircle growingCircle) {
        this.growingCircle = growingCircle;
    }

    
    /**
     * Est appelé à chaque frame pour dessiner le cercle qui change de taille.
     * 
     * @param level Le niveau
     * @param context Le contexte graphique
     */
    @Override
    public void draw(Level level, GraphicsContext context) {
        double canvasY = Renderer.computeScreenY(level, growingCircle.getY());

        context.setFill(Renderer.convertColor(growingCircle.getColor()));

        context.fillOval(
                growingCircle.getX() - growingCircle.getRadius(),
                canvasY - growingCircle.getRadius(),
                growingCircle.getWidth(),
                growingCircle.getHeight());
    }    
}

