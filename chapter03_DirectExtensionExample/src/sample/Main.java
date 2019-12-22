package sample;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {


//        launch(args);

        System.out.println("Constructing x with value 2.0.");
        final DoubleProperty x = new SimpleDoubleProperty(null,"x",2.0);
        System.out.println("Constructing y with value 3.0.");
        final DoubleProperty y = new SimpleDoubleProperty(null, "y",3.0);
        System.out.println("Creating binding area" +
                " with dependencies x and y.");
        DoubleBinding area = new DoubleBinding() {

            {
                super.bind(x,y);
            }

            @Override
            protected double computeValue() {
                return x.get()*y.get();
            }

        };

        System.out.println("area.get() = " + area.get());
        System.out.println("area.get() = " + area.get());
        System.out.println("Setting x to 5");
        x.set(5);
        System.out.println("Setting y to 7");
        y.set(7);
        System.out.println("area.get() = " + area.get());

    }
}
