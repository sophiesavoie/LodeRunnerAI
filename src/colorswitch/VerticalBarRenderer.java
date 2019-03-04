package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * Classe qui fait le rendu d'une barre verticale.
 */
public class VerticalBarRenderer extends Renderer {

    private final VerticalBar verticalBar;
    
    /**
     * Constructeur de la classe VerticalBarRenderer
     * 
     * @param verticalBar La barre verticale
     */
    public VerticalBarRenderer(VerticalBar verticalBar) {
        this.verticalBar = verticalBar;
    }

    
    /**
     * Est appelé à chaque frame pour dessiner la barre verticale en mouvement.
     * 
     * @param level Le niveau
     * @param context Le contexte graphique
     */
    @Override
    public void draw(Level level, GraphicsContext context) {
        
        double canvasY = Renderer.computeScreenY(level, verticalBar.getY());

        context.setFill(Renderer.convertColor(verticalBar.getColor()));

        context.fillRect(
                verticalBar.getX() - verticalBar.getWidth() / 2,
                canvasY - verticalBar.getHeight() / 2,
                verticalBar.getWidth(),
                verticalBar.getHeight());
    }
    
}
