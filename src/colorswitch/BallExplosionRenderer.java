package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * Fait le rendu d'une balle explosive.
 */
public class BallExplosionRenderer extends Renderer {
    private BallExplosion ball;

    public BallExplosionRenderer(BallExplosion ball) {
        this.ball = ball;
    }

    
    /**
     * Est appelé à chaque frame pour dessiner la balle en explosion.
     * 
     * @param level Le niveau
     * @param context Le contexte graphique
     */
    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, ball.getY());

        context.setFill(Renderer.convertColor(ball.getColor()));
        
        context.fillOval(
                ball.getX() - ball.getRadius(),
                canvasY - ball.getRadius(),
                2 * ball.getRadius(),
                2 * ball.getRadius());
    }
}
