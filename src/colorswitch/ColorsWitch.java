package colorswitch;

/**
 * @author Sophie Savoie et Normand Desmarais
 */
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Classe principale. Définit la vue.
 */
public class ColorsWitch extends Application {

    public static final double WIDTH = 320, HEIGHT = 480;

    private Controller controller;
    private GraphicsContext context;
    private Stage primaryStage;
    private Canvas canvas;
    private Scene menuScene, levelScene;

    public static void main(String[] args) {
        launch(args);
    }

    
    @Override
    public void start(Stage stage) throws Exception {
        controller = new Controller();
        primaryStage = stage;
        canvas = new Canvas(WIDTH, HEIGHT);
        context = canvas.getGraphicsContext2D();
        
        setLevelScene();
        setMenuScene();
        showMenu();

        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = System.nanoTime();

            @Override
            public void handle(long now) {
                controller.tick((now - lastTime) * 1e-9);

                context.setFill(Color.BLACK);
                context.fillRect(0, 0, WIDTH, HEIGHT);

                List<Entity> entities = controller.getEntities();

                for (Entity e : entities) {
                    e.getRepresentation().draw(controller.getCurrentLevel(), context);
                }

                lastTime = now;
            }
        };
        timer.start();

        primaryStage.setTitle("Colors Witch");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    
    /**
     * Crée la scène associée au menu du jeu.
     */
    public void setMenuScene() {
        VBox root = new VBox();
        root.setStyle("-fx-background-color: black;");
        GridPane grid = new GridPane();
        menuScene = new Scene(root, WIDTH, HEIGHT);
        
        Text csText = new Text();
        csText.setText("Colors Witch");
        csText.setFont(Font.font("Bauhaus 93", FontWeight.BOLD, 42));
        csText.setFill(Color.SPRINGGREEN);
        root.getChildren().add(csText);
        
        Image bars = new Image("/barImgMenu.png");
        ImageView barMenu = new ImageView(bars);
        root.getChildren().add(barMenu);
        
        Text menuText = new Text();
        menuText.setText("SELECT A LEVEL");
        menuText.setFont(Font.font("Calibri", FontWeight.BOLD, 28));
        menuText.setFill(Color.LIGHTCORAL);
        root.getChildren().add(menuText);
        
        for (int y = 1; y <= 5; y++) {
            for (int x = 0; x <= 1; x++) {
                Button button = new Button();
                button.setText(x * 5 + y + "");
                button.setPrefWidth(90);
                button.setPrefHeight(30);
                button.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
                grid.add(button, x, y);
                
                button.setOnAction((event) -> {
                    controller.setLevel(Integer.parseInt(button.getText()));
                    showLevel();
                });
            }
        }
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        root.getChildren().add(grid);
        root.setSpacing(30);
        root.setAlignment(Pos.CENTER);
    }
    
    
    /**
     * Affiche la scène du menu du jeu.
     */
    public void showMenu() {
        primaryStage.setScene(menuScene);
    }
    
    
    /**
     * Crée la scène utilisée pour afficher les niveaux.
     */
    public void setLevelScene() {
        BorderPane root = new BorderPane(canvas);
        levelScene = new Scene(root, WIDTH, HEIGHT);

        levelScene.setOnKeyPressed((event) -> {
            if (null != event.getCode()) switch (event.getCode()) {
                case SPACE:
                    controller.spaceTyped();
                    break;
                case ESCAPE:
                    showMenu();
                    break;
                case TAB:
                    controller.tabTyped();
                    break;
                case J:
                    controller.jTyped();
                    break;
                default:
                    break;
            }
        });
    }
    
    
    /**
     * Affiche la scène utilisée par les niveaux du jeu.
     */
    public void showLevel() {
        primaryStage.setScene(levelScene);
    }
}
