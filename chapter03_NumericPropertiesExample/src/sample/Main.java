package sample;

import javafx.application.Application;
import javafx.beans.property.*;
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
        IntegerProperty i =
                new SimpleIntegerProperty(null, "i", 1024);
        LongProperty l =
                new SimpleLongProperty(null, "l", 0L);
        FloatProperty f =
                new SimpleFloatProperty(null, "f", 0.0F);
        DoubleProperty d =
                new SimpleDoubleProperty(null, "d", 0.0);
        System.out.println("Constructed numerical" +
                " properties i, l, f, d.");

        System.out.println("i.get() = "+ i.get());
        System.out.println("l.get() = "+ l.get());
        System.out.println("f.get() = "+ f.get());
        System.out.println("d.get() = "+ d.get());

        l.bind(i);
        f.bind(l);
        d.bind(f);

        System.out.println("Bound l to i, f to l, d to f.");

        System.out.println("i.get() = "+ i.get());
        System.out.println("l.get() = "+ l.get());
        System.out.println("f.get() = "+ f.get());
        System.out.println("d.get() = "+ d.get());

        System.out.println("Calling i.set(2048).");
        i.set(2048);

        System.out.println("i.get() = "+ i.get());
        System.out.println("l.get() = "+ l.get());
        System.out.println("f.get() = "+ f.get());
        System.out.println("d.get() = "+ d.get());

        d.unbind();
        f.unbind();
        l.unbind();
        System.out.println("Unbound l to i, f to l, d to f.");
        f.bind(d);
        l.bind(f);
        i.bind(l);


        System.out.println("Bound f to d, l to f, i to l.");
        System.out.println("Calling d.set(10000000000L).");
        d.set(10000000000L);

        System.out.println("i.get() = "+ i.get());
        System.out.println("l.get() = "+ l.get());
        System.out.println("f.get() = "+ f.get());
        System.out.println("d.get() = "+ d.get());




    }

}
