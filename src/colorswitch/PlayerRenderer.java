package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * Fait le rendu d'un Player sur l'écran en dessinant un cercle coloré
 */
public class PlayerRenderer extends Renderer {

    private Player player;

    public PlayerRenderer(Player player) {
        this.player = player;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, player.getY());
        PlayerAura aura = player.getAura();

        /**
         * Dessiner une aura autour de la sorcière pour représenter
         * l'effet actif.
         */
        if (aura != null && aura.isActive()) {
            context.setFill(Renderer.convertColor(aura.getCurrentColor()));

            double auraRadius = player.getRadius() + 5;
            context.fillOval(
                    player.getX() - auraRadius,
                    canvasY - auraRadius,
                    2 * auraRadius,
                    2 * auraRadius);
        }
        
        /**
         * Dessiner l'avatar de la sorcière.
         */
        context.setFill(Renderer.convertColor(player.getColor()));

        context.fillOval(
                player.getX() - player.getRadius(),
                canvasY - player.getRadius(),
                2 * player.getRadius(),
                2 * player.getRadius());
    }
}
