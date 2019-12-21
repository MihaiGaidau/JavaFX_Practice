package sample;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.MouseEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //create an Ellipse and set fill color

        Stop[] stops = new Stop[] { new Stop(0, Color.DODGERBLUE),
                new Stop(0.5, Color.LIGHTBLUE),
                new Stop(1.0, Color.LIGHTGREEN)};

        LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true,
                CycleMethod.NO_CYCLE, stops);


        Ellipse ellipse = new Ellipse(110, 70);
        ellipse.setFill(Color.rgb(173, 216, 230, .5));
        ellipse.setFill(gradient);
        ellipse.setEffect(new DropShadow(30,10,10,Color.GRAY));

        //Create a Text shape with font and size
        Text text = new Text("My Shapes");
        text.setFont(new Font("Arial Bold", 24));
        Reflection r = new Reflection();
        r.setFraction(.8);
        r.setTopOffset(1.0);
        text.setEffect(r);





        text.setOnMouseClicked(mouseEvent -> {
            System.out.println(mouseEvent.getSource().getClass()+ "clicked");
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(ellipse, text);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(2500),stackPane);
        rotateTransition.setToAngle(360);
        rotateTransition.setFromAngle(0);
        rotateTransition.setInterpolator(Interpolator.LINEAR);

        stackPane.setOnMouseClicked(mouseEvent -> {
            if (rotateTransition.getStatus().equals(Animation.Status.RUNNING)){
                rotateTransition.pause();
            }
            else{
                rotateTransition.play();
            }
        });

        Scene scene = new Scene(stackPane, 350, 230, Color.LIGHTYELLOW);
        primaryStage.setTitle("MyShapes with JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
