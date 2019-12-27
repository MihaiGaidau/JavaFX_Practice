import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args)throws NoSuchMethodException {
        launch(args);

    }


    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label("Stylized label");
        label.setId("big-bold-label");
        VBox root = new VBox(label);
        Scene scene = new Scene(root, 200, 100);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
//         label.getStyleClass().add("big-bold-text");
        stage.setTitle("My first css example");
        stage.setScene(scene);
        stage.show();
    }


}
