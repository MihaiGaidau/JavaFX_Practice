import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jdk.jfr.DataAmount;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {


    public void start(Stage stage) throws Exception {

        ToggleButton tb1 = new ToggleButton("Toggle btn 1");
        ToggleButton tb2 = new ToggleButton("Toggle btn 2");
        ToggleButton tb3 = new ToggleButton("Toggle btn 3");
        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(tb1, tb2, tb3);
        ObservableList<ToggleButton> list = FXCollections.observableArrayList();
        list.add(tb1);
        list.add(tb2);
        list.add(tb3);
        ListView<ToggleButton> myList = new ListView<>();
        myList.setItems(list);
//        tb1.setOnAction(e -> log("ToggleButton 1 was clicked on!"));
//// but it is better to add a listener to the toggle group  selectedToggle property
//        group.selectedToggleProperty().addListener(i -> log("Selected toggle is " + group.
//                getSelectedToggle()));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(myList);
        Scene scene = new Scene(stackPane, 350, 230,
                Color.LIGHTYELLOW);
        stage.setTitle("MyShapes with JavaFX");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args)throws NoSuchMethodException {
        launch(args);

    }
}
