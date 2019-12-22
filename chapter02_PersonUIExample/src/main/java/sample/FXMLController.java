package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import sample.model.Person;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {

    @FXML
    private ListView<Person> listView;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private TextArea notesTextArea;

    @FXML
    private Button removeButton;

    @FXML
    private Button createButton;

    @FXML
    private Button updateButton;



    private final ObservableList<Person> personList =
            FXCollections.observableArrayList(Person.extractor);

    Person selectedPerson = new Person();

    private final BooleanProperty modifiedProperty =
            new SimpleBooleanProperty(false);


    private ChangeListener<Person> personChangeListener;







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        binding();


        listView.getSelectionModel().selectedItemProperty().addListener(
                personChangeListener = (observable, oldValue, newValue) -> {
                    // newValue can be null if nothing is selected
                    selectedPerson = newValue;
                    modifiedProperty.set(false);
                    if (newValue != null) {
                        // Populate controls with selected Person
                        firstnameTextField.setText(selectedPerson.getFirstname());
                        lastnameTextField.setText(selectedPerson.getLastname());
                        notesTextArea.setText(selectedPerson.getNotes());
                    } else {
                        firstnameTextField.setText("");
                        lastnameTextField.setText("");
                        notesTextArea.setText("");
                    }
                });

        SortedList<Person> sortedList = new SortedList((ObservableList) personList);
        sortedList.setComparator((p1, p2) -> {
            int result = p1.getLastname().compareToIgnoreCase(p2.getLastname());
            if (result == 0) {
                result = p1.getFirstname().compareToIgnoreCase(p2.getFirstname());
            }
            return result;
        });
        listView.setItems(sortedList);




    }

    @FXML
    private void removeButtonAction(ActionEvent actionEvent) {
        personList.remove(selectedPerson);
    }

    @FXML
    private void createButtonAction(ActionEvent actionEvent) {
        Person person = new Person(firstnameTextField.getText(),
                lastnameTextField.getText(), notesTextArea.getText());
        personList.add(person);
        // and select it
        listView.getSelectionModel().select(person);
    }

    @FXML
    private void updateButtonAction(ActionEvent actionEvent){
        Person p = listView.getSelectionModel().getSelectedItem();
        listView.getSelectionModel().selectedItemProperty()
                .removeListener(personChangeListener);
        p.setFirstname(firstnameTextField.getText());
        p.setLastname(lastnameTextField.getText());
        p.setNotes(notesTextArea.getText());
        listView.getSelectionModel().selectedItemProperty()
                .addListener(personChangeListener);
        modifiedProperty.set(false);
    }

    @FXML
    private void handleKeyAction(KeyEvent keyEvent) {
        modifiedProperty.set(true);
    }

    private void binding(){
        createButton.disableProperty().bind(
                listView.getSelectionModel().selectedItemProperty().isNotNull()
                        .or(firstnameTextField.textProperty().isEmpty()
                                .or(lastnameTextField.textProperty().isEmpty())));

        removeButton.disableProperty().bind(
                listView.getSelectionModel().selectedItemProperty().isNull());


        updateButton.disableProperty().bind(
                listView.getSelectionModel().selectedItemProperty().isNull()
                        .or(modifiedProperty.not())
                        .or(firstnameTextField.textProperty().isEmpty()
                                .or(lastnameTextField.textProperty().isEmpty())));
    }



}
