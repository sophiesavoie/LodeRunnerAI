package colorswitch;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Fait le rendu d'un item à l'aide d'une animation d'images.
 */
public class AnimationRenderer extends Renderer {

    private final Image[] frame;
    private final Entity entity;
    private final double framerate;
    private final int nbFrames;
    
    /**
     * Constructeur de la classe AnimationRenderer.
     * 
     * @param prefix le chemin de base de l’image (sans le numéro de frame)
     * @param number le nombre de frames totales dans la série d’images
     * @param framerate le nombre de fois que l’image doit être mise à jour à chaque seconde
     * @param entity l’entité de jeu associée au rendu
     */
    public AnimationRenderer(String prefix, int number, double framerate, Entity entity) {
        frame = new Image[number];
        
        for (int i=0; i<number; i++) {
            frame[i] = new Image("/" + prefix + (i+1) + ".png");
        }
        this.nbFrames = number;
        this.framerate = framerate;
        this.entity = entity;
    }

    
    /**
     * Est appelé à chaque frame pour afficher les images.
     * 
     * @param level Le niveau
     * @param context Le contexte graphique
     */
    @Override
    public void draw(Level level, GraphicsContext context) {

        int current = (int)(System.nanoTime() * 1e-9 * framerate) % nbFrames;
        
        double x = entity.getX() - entity.getWidth() / 2;
        double y = Renderer.computeScreenY(level, entity.getY())
                - entity.getHeight() / 2;

        context.drawImage(frame[current], x, y,
                entity.getWidth(), entity.getHeight());
    }
    
}
