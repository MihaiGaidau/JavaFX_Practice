package sample;

import javafx.application.Application;
import javafx.collections.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

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
        ObservableList<String> list =
                FXCollections.observableArrayList();
        ObservableMap<String, String> map =
                FXCollections.observableHashMap();
        ObservableSet<Integer> set =
                FXCollections.observableSet();
        ObservableFloatArray array =
                FXCollections.observableFloatArray();
        list.addListener((ListChangeListener<String>) c -> {
            System.out.println("\tlist = " +
                    c.getList());
        });
        map.addListener((MapChangeListener<String, String>) c -> {
            System.out.println("\tmap = " +
                    c.getMap());
        });

        set.addListener((SetChangeListener<Integer>) c -> {
            System.out.println("\tset = " +
                    c.getSet());
        });
        array.addListener((observableArray,
                           sizeChanged, from, to) -> {
            System.out.println("\tarray = " +
                    observableArray);
        });
        manipulateList(list);
        manipulateMap(map);
        manipulateSet(set);
        manipulateArray(array);
    }

    private static void manipulateList(
            ObservableList<String> list) {
        System.out.println("Calling list.addAll(\"Zero\"," +
                " \"One\", \"Two\", \"Three\"):");
        list.addAll("Zero", "One", "Two", "Three");
        System.out.println("Calling copy(list," +
                " Arrays.asList(\"Four\", \"Five\")):");
        FXCollections.copy(list,
                Arrays.asList("Four", "Five"));
        System.out.println("Calling replaceAll(list," +
                " \"Two\", \"Two_1\"):");
        FXCollections.replaceAll(list, "Two", "Two_1");
        System.out.println("Calling reverse(list):");
        FXCollections.reverse(list);
        System.out.println("Calling rotate(list, 2):");
        FXCollections.rotate(list, 2);
        System.out.println("Calling shuffle(list):");
        FXCollections.shuffle(list);
        System.out.println("Calling shuffle(list," +
                " new Random(0L)):");
        FXCollections.shuffle(list, new Random(0L));
        System.out.println("Calling sort(list):");
        FXCollections.sort(list);
        System.out.println("Calling sort(list, c)" +
                " with custom comparator: ");
        FXCollections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                // Reverse the order
                return rhs.compareTo(lhs);
//                return lhs.compareTo(rhs);
            }
        });
        System.out.println("Calling fill(list," +
                " \"Ten\"): ");
        FXCollections.fill(list, "Ten");
    }
    private static void manipulateMap(
            ObservableMap<String, String> map) {
        System.out.println("Calling map.put(\"Key\"," +
                " \"Value\"):");
        map.put("Key", "Value");
    }
    private static void manipulateSet(
            ObservableSet<Integer> set) {
        System.out.println("Calling set.add(1024):");
        set.add(1024);
    }

    private static void manipulateArray(
            ObservableFloatArray array) {
        System.out.println("Calling  array.addAll(3.14159f," +
                " 2.71828f):");
        array.addAll(3.14159f, 2.71828f);
    }
}
