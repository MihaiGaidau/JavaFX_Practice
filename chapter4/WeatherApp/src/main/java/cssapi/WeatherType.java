package cssapi;

import javafx.scene.text.Text;

public enum WeatherType {
    SUNNY("\uf00d",false),
    CLOUDY("\uf00d",false),
    RAIN("\uf019",false),
    THUNDERSTORM("\uf033",true);

    private final boolean dangerous;
    private final String c;


    WeatherType(String c, boolean dangerous) {
        this.dangerous = dangerous;
        this.c = c;
    }
    public boolean isDangerous(){
        return dangerous;
    }
    Text buildGraphic(){
        Text text = new Text(c);
        text.setStyle("-fx-font-family: 'Weather Icons Regular';" +
                "-fx-font-size: 25;");
        return text;
    }
}
