package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * Classe qui fait le rendu d'un cercle rotatif.
 */
public class RotatingCircleRenderer extends Renderer {
    private final RotatingCircle rotatingCircle;
    
    /**
     * Constructeur de la classe RotatingCircle
     * 
     * @param rotatingCircle Le cercle rotatif
     */
    public RotatingCircleRenderer(RotatingCircle rotatingCircle) {
        this.rotatingCircle = rotatingCircle;
    }
    
    
    /**
     * Est appelé à chaque frame pour dessiner le cercle en rotation.
     * 
     * @param level Le niveau
     * @param context Le contexte graphique
     */
    @Override
    public void draw(Level level, GraphicsContext context) {
        double canvasY = Renderer.computeScreenY(level, rotatingCircle.getY());

        context.setFill(Renderer.convertColor(rotatingCircle.getColor()));

        context.fillOval(
                rotatingCircle.getX() - rotatingCircle.getRadius(),
                canvasY - rotatingCircle.getRadius(),
                rotatingCircle.getWidth(),
                rotatingCircle.getHeight());
    }
    
}
