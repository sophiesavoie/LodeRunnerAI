package colorswitch;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 *
 * Classe responsable d'afficher du texte sur le canvas.
 */
public class TextRenderer extends Renderer {

    private final String text;
    private final Entity entity;
    private final double fontSize;

    /**
     * Initialise un TextRenderer
     * 
     * @param text le texte à afficher
     * @param entity l'entité associée au renderer
     * @param fontSize taille de la fonte
     */
    public TextRenderer(String text, Entity entity, double fontSize) {
        this.text = text;
        this.entity = entity;
        this.fontSize = fontSize;
    }

    
    /**
     * Affiche du texte sur le canvas en le centrant par rapport
     * aux coordonnées de l'entité et en créant un effet drop-shadow.
     * 
     * @param level (non utilisé) niveau du jeu
     * @param context le context utilisé pour afficher le texte
     */
    @Override
    public void draw(Level level, GraphicsContext context) {
        context.save();
        
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.PINK);
        
        context.setEffect(ds);
        context.setTextAlign(TextAlignment.CENTER);
        context.setFont(Font.font("Bauhaus 93", FontWeight.BOLD, fontSize));
        context.setFill(Color.RED);
        context.fillText(text, entity.getX(), entity.getY());
        
        context.restore();
    }

}
