import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private Label label = new Label("stylized label");
    private TextField witdthField = new TextField("500"){
        @Override
        public void replaceText(int i, int i1, String s) {
            if (s.matches("[0-9]*")){
                super.replaceText(i, i1, s);
            }
        }

        @Override
        public void replaceSelection(String s) {
            if (s.matches("[0-9]*")){
                super.replaceSelection(s);
            }
        }
    };

    public static void main(String[] args)throws NoSuchMethodException {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        updateLabelStyle();
        witdthField.setOnAction(e -> updateLabelStyle());

        VBox root = new VBox(10,label, witdthField);
        root.setStyle(
                "-fx-background-color: lightblue;" +
                        "-fx-padding: 20px;"
        );
        Scene scene = new Scene(root, 250,100);
        stage.setTitle("My first CSS application");
        stage.setScene(scene);
        stage.show();

    }

    private void updateLabelStyle(){
        label.setStyle(
                "-fx-background-color: black;"+
                        "-fx-text-fill: white;"+
                        "-fx-padding: 10;"+
                        "-fx-pref-width: "+witdthField.getText()+"px;"
        );
    }


}
