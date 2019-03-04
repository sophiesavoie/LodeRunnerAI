package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * Classe permetant d'afficher une instance de MultiColorBar
 */
public class MultiColorBarRenderer extends Renderer {

    private MultiColorBar multicolorBar;

    /**
     * Instancie un MultiColorBarRenderer pour l'instance MultiColorBar
     * passée en paramètre.
     * 
     * @param multicolorBar l'intance pour laquelle le rendue doit être effectué
     */
    public MultiColorBarRenderer(MultiColorBar multicolorBar) {
        this.multicolorBar = multicolorBar;
    }

    
    /**
     * Affiche toutes les sections d'une MultiColorBar
     * 
     * @param level le niveau du jeu (permet de calculer la postion en y)
     * @param context le context dans lequel l'affichage sera effectué
     */
    @Override
    public void draw(Level level, GraphicsContext context) {

        double[] x = multicolorBar.getAllX();
        int[] color = multicolorBar.getAllColor();

        double canvasY = Renderer.computeScreenY(level, multicolorBar.getY());
        double halfWidth = multicolorBar.getWidth() / 2;
        double correctedHalfHeight = canvasY - multicolorBar.getHeight() / 2;
        double totalWidth = x.length * multicolorBar.getWidth();

        for (int i = 0; i < x.length; i++) {
            context.setFill(Renderer.convertColor(color[i]));

            if (x[i] < halfWidth) {
                double diff = halfWidth - x[i];
                context.fillRect(
                        totalWidth - diff,
                        correctedHalfHeight,
                        diff,
                        multicolorBar.getHeight());

                double wLarge = multicolorBar.getWidth() - diff;
                context.fillRect(
                        0,
                        correctedHalfHeight,
                        multicolorBar.getWidth() - diff,
                        multicolorBar.getHeight());

            } else if (x[i] > totalWidth - halfWidth) {
                double diff = x[i] - totalWidth + halfWidth;
                context.fillRect(
                        0,
                        correctedHalfHeight,
                        diff,
                        multicolorBar.getHeight());

                double wLarge = multicolorBar.getWidth() - diff;
                context.fillRect(
                        totalWidth - wLarge,
                        correctedHalfHeight,
                        wLarge,
                        multicolorBar.getHeight());

            } else {
                context.fillRect(x[i] - halfWidth,
                        correctedHalfHeight,
                        multicolorBar.getWidth(),
                        multicolorBar.getHeight());
            }
        }

    }

}
