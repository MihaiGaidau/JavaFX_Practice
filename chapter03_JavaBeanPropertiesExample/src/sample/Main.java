package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.adapter.JavaBeanStringProperty;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.Person;

import java.beans.PropertyVetoException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args)throws NoSuchMethodException {
//        launch(args);
        adaptJavaBeansProperty();
        adaptBoundProperty();
        adaptConstrainedProperty();
    }

    private static void adaptJavaBeansProperty()
            throws NoSuchMethodException {
        Person person = new Person();
        JavaBeanStringProperty nameProperty =
                JavaBeanStringPropertyBuilder.create()
                        .bean(person)
                        .name("name")
                        .build();
        nameProperty.addListener((observable, oldValue, newValue) -> {
            System.out.println("JavaFX property " +
                    observable + " changed:");
            System.out.println("\toldValue = " +
                    oldValue + ", newValue = " + newValue);
        });
        System.out.println("Setting name on the" +
                " JavaBeans property");
        person.setName("Weiqi Gao");
        System.out.println("Calling fireValueChange");
        nameProperty.fireValueChangedEvent();
        System.out.println("nameProperty.get() = " +
                nameProperty.get());
        System.out.println("Setting value on the" +
                " JavaFX property");
        nameProperty.set("Johan Vos");
        System.out.println("person.getName() = " +
                person.getName());
    }
    private static void adaptBoundProperty()
            throws NoSuchMethodException {
        System.out.println();
        Person person = new Person();
        JavaBeanStringProperty addressProperty =
                JavaBeanStringPropertyBuilder.create()
                        .bean(person)
                        .name("address")
                        .build();
        addressProperty.addListener((observable, oldValue, newValue) -> {
            System.out.println("JavaFX property " +
                    observable + " changed:");
            System.out.println("\toldValue = " +
                    oldValue + ", newValue = " + newValue);
        });
        System.out.println("Setting address on the" +
                " JavaBeans property");
        person.setAddress("12345 main Street");
    }
    private static void adaptConstrainedProperty()
            throws NoSuchMethodException {
        System.out.println();
        Person person = new Person();
        JavaBeanStringProperty phoneNumberProperty =
                JavaBeanStringPropertyBuilder.create()
                        .bean(person)
                        .name("phoneNumber")
                        .build();
        phoneNumberProperty.addListener((observable,
                                         oldValue, newValue) -> {
            System.out.println("JavaFX property " +
                    observable + " changed:");
            System.out.println("\toldValue = " +
                    oldValue + ", newValue = " + newValue);
        });
        System.out.println("Setting phoneNumber on the" +
                " JavaBeans property");
        try {
            person.setPhoneNumber("800-555-1212");
        } catch (PropertyVetoException e) {
            System.out.println("A JavaBeans property" +
                    " change is vetoed.");
        }
        System.out.println("Bind phoneNumberProperty" +
                " to another property");
        SimpleStringProperty stringProperty =
                new SimpleStringProperty("866-555-1212");
        phoneNumberProperty.bind(stringProperty);
        System.out.println("Setting phoneNumber on the" +
                " JavaBeans property");
        try {
            person.setPhoneNumber("888-555-1212");
        } catch (PropertyVetoException e) {
            System.out.println("A JavaBeans property" +
                    " change is vetoed.");
        }
        System.out.println("person.getPhoneNumber() = " +
                person.getPhoneNumber());
    }
}
