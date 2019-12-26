import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import jdk.jfr.DataAmount;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class Main extends Application {


    public void start(Stage stage) throws Exception {

        //RadioButtons
        RadioButton tb1 = new RadioButton("Radio btn 1");
        RadioButton tb2 = new RadioButton("Radio btn 2");
        RadioButton tb3 = new RadioButton("Radio btn 3");
        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(tb1, tb2, tb3);
        ObservableList<ToggleButton> list = FXCollections.observableArrayList();
        list.add(tb1);
        list.add(tb2);
        list.add(tb3);
        ListView<ToggleButton> myList = new ListView<>();
        myList.setItems(list);

        //ProgressBar
        ProgressBar p2 = new ProgressBar();

        //Slider
        Slider slider = new Slider(0.0f, 1.0f, 0.5f);
        slider.valueProperty().addListener((o, oldValue, newValue) -> log.info("Slider value is: {} ", newValue));
        p2.setProgress(0.25F);

        //TitlePad
        TitledPane t1 = new TitledPane("TitledPane 1", new Button("Button 1"));
        TitledPane t2 = new TitledPane("TitledPane 2", new Button("Button 2"));
        TitledPane t3 = new TitledPane("TitledPane 3", new Button("Button 3"));
        Accordion accordion = new Accordion();
        accordion.getPanes().addAll(t1, t2, t3);

        //ButtonBar
        // Create the ButtonBar instance
        ButtonBar buttonBar = new ButtonBar();
        // Create the buttons to go into the ButtonBar
        Button yesButton = new Button("Yes");
        ButtonBar.setButtonData(yesButton, ButtonBar.ButtonData.YES);
        Button noButton = new Button("No");
        ButtonBar.setButtonData(noButton, ButtonBar.ButtonData.NO);
        // Add buttons to the ButtonBar
        buttonBar.getButtons().addAll(yesButton, noButton);

        TitledPane t4 = new TitledPane("TitledPane 4",buttonBar);
        accordion.getPanes().add(t4);

        //Split Pane
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.HORIZONTAL);
        StackPane leftStackPane = new StackPane();
        leftStackPane.getChildren().addAll(myList);
        StackPane rightStackPane = new StackPane();
        rightStackPane.getChildren().setAll(accordion);
        splitPane.getItems().addAll(leftStackPane,rightStackPane);

        //ScrolPane
        Stop[] stops = new Stop[] { new Stop(0, Color.BLACK), new Stop(1, Color.RED)};
        LinearGradient gradient = new LinearGradient(0, 0, 1500, 1000, false,
                CycleMethod.NO_CYCLE, stops);
// we place the linear gradient inside a big rectangle
        Rectangle rect = new Rectangle(2000, 2000, gradient);
// which is placed inside a scrollpane that is quite small in comparison
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(120, 120);
        scrollPane.setContent(rect);
        // and we then listen (and log) when the user is scrolling vertically or horizontally
        ChangeListener<? super Number> o = (obs, oldValue, newValue) ->
        {
        log.info("x / y values are: (" + scrollPane.getHvalue() + ", " + scrollPane.
                    getVvalue() + ")");
        };
        scrollPane.hvalueProperty().addListener(o);
        scrollPane.vvalueProperty().addListener(o);
        splitPane.getItems().add(scrollPane);

        //tabPane
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        for (int i = 0; i < 5; i++) {
            Tab tab = new Tab("Tab " + i, new Rectangle(200, 200, randomColor()));
            tabPane.getTabs().add(tab);
        }
//        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        splitPane.getItems().add(tabPane);

        //tool Bar
        ToolBar toolBar = new ToolBar();
        toolBar.getItems().addAll(
                new Button("New"),
                new Button("Open"),
                new Button("Save"),
                new Separator(),
                new Button("Clean"),
                new Button("Compile"),
                new Button("Run"),
                new Separator(),
                new Button("Debug"),
                new Button("Profile")
        );

        //pagination
        Pagination pagination = new Pagination(10, 0);
        pagination.setPageFactory(pageIndex -> {
            VBox box = new VBox(5);
            for (int i = 0; i < 10; i++) {
                int linkNumber = pageIndex * 10 + i;
                Hyperlink link = new Hyperlink("Hyperlink #" + linkNumber);
                  link.setOnAction(e -> log.info("Hyperlink #" + linkNumber + " clicked!"));
                box.getChildren().add(link);
            }
            return box;
        });

        //Spinner
        Spinner<Integer> spinner = new Spinner<>();
        spinner.setValueFactory(new SpinnerValueFactory.
                IntegerSpinnerValueFactory(5, 10));
        spinner.valueProperty().addListener((ol, oldValue, newValue) -> {
            log.info("value changed: '" + oldValue + "' -> '" + newValue + "'");
        });

        SplitPane buttomSplitPane = new SplitPane();
        buttomSplitPane.setOrientation(Orientation.HORIZONTAL);
        buttomSplitPane.getItems().setAll(pagination,spinner);


        BorderPane mainWindows = new BorderPane();
//        mainWindows.getChildren().setAll(toolBar,splitPane);
//        mainWindows.setTop(toolBar);
        mainWindows.setCenter(splitPane);
        mainWindows.setBottom(buttomSplitPane);



        //Tooltip
        Tooltip t = new Tooltip("A Square");
        Tooltip.install(spinner, t);

        //menu
        // Firstly we create our menu instances (and populate with menu items)
        final Menu fileMenu = new Menu("File");
        final Menu helpMenu = new Menu("Help");
// we are creating a Menu here to add as a submenu to the File menu
        Menu newMenu = new Menu("Create New...");
        newMenu.getItems().addAll(
                makeMenuItem("Project"),
                makeMenuItem("JavaFX class"),
                makeMenuItem("FXML file")
        );
// add menu items to each menu
        fileMenu.getItems().addAll(
                newMenu,
                new SeparatorMenuItem(),
                makeMenuItem("Exit")
        );
        helpMenu.getItems().addAll(makeMenuItem("Help"));
// then we create the MenuBar instance and add in the menus
        SplitPane top = new SplitPane();
        top.setOrientation(Orientation.VERTICAL);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, helpMenu);


        mainWindows.setTop(top);

        //menuButton
        MenuButton menuButton = new MenuButton("Choose a meal...");
        menuButton.getItems().addAll(
                makeMenuItem("Burgers"),
                makeMenuItem("Pizza"),
                makeMenuItem("Hot Dog"));
// because the MenuButton does not have an 'action' area,
// onAction does nothing
        menuButton.setOnAction(e -> log.info("MenuButton onAction event"));

        SplitMenuButton splitMenuButton = new SplitMenuButton();
// this is the text in the 'action' area
        splitMenuButton.setText("Perform action!");
// these are the menu items to display in the popup menu
        splitMenuButton.getItems().addAll(
                makeMenuItem("Burgers"),
                makeMenuItem("Pizza"),
                makeMenuItem("Hot Dog"));
// splitMenuButton does fire an onAction event,
// when the 'action' area is pressed
        splitMenuButton.setOnAction(e -> log.info("SplitMenuButton onAction event"));


        //contextMenu
//        / create a standard JavaFX Button
        Button button = new Button("Right-click Me!");
        button.setOnAction(event -> log.info("Button was clicked"));
// create a ContextMenu
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(
                makeMenuItem("Hello"),
                makeMenuItem("World!"),
                new SeparatorMenuItem(),
                makeMenuItem("Goodbye Again!")
        );

        Rectangle rectangle = new Rectangle(50, 50, Color.RED);
        rectangle.setOnContextMenuRequested(e -> {
            // show the contextMenu to the right of the rectangle with zero
            // offset in x and y directions
            contextMenu.show(rectangle, Side.RIGHT, 0, 0);
        });

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(
                "Choice 1",
                "Choice 2",
                "Choice 3",
                "Choice 4"
        );
        choiceBox.getSelectionModel()
                .selectedItemProperty()
                .addListener((oq, oldValue, newValue) -> log.info(newValue));

        final DatePicker datePicker = new DatePicker();
        datePicker.setOnAction(e -> {
            LocalDate date = datePicker.getValue();
            System.err.println("Selected date: " + date);
        });
        top.getItems().setAll(menuBar,toolBar,menuButton,splitMenuButton,rectangle,choiceBox,datePicker);


        Scene scene = new Scene(mainWindows, 1280, 720, Color.LIGHTYELLOW);
        stage.setTitle("MyShapes with JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    private Paint randomColor() {
        Random rand = new Random(System.currentTimeMillis());
        return Color.rgb(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
    }

    public static void main(String[] args)throws NoSuchMethodException {
        launch(args);

    }
    MenuItem makeMenuItem(String text){
        return new MenuItem(text);
    }
}
