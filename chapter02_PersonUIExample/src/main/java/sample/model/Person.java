package sample.model;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;

import java.util.Objects;

public class Person {
    private final StringProperty firstname = new SimpleStringProperty(this, "firstname","");
    private final StringProperty lastname = new SimpleStringProperty(this, "lastname","");
    private final StringProperty notes = new SimpleStringProperty(this, "notes","sample notes");


    //constr
    public Person(){

    }

    public Person(String firstname, String lastname, String notes){
        this.firstname.set(firstname);
        this.lastname.set(lastname);
        this.notes.set(notes);
    }

    //get
    public String getFirstname(){
        return firstname.get();
    }

    public String getLastname() {
        return lastname.get();
    }

    public String getNotes() {
        return notes.get();
    }


    public static Callback<Person, Observable[]> extractor =
            p-> new Observable[] {
                    p.lastnameProperty(), p.firstnameProperty()
            };


    //prop
    public StringProperty firstnameProperty() {
        return firstname;
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public StringProperty notesProperty() {
        return notes;
    }


    //set
    public void setLastname(String lastname){
        this.lastname.set(lastname);
    }
    public void setFirstname(String firstname){
        this.firstname.set(firstname);
    }

    public void setNotes(String notes){
        this.notes.set(notes);
    }

    @Override
    public String toString() {
        return firstname.get() + " " + lastname.get();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return Objects.equals(firstname, person.firstname) &&
                Objects.equals(lastname, person.lastname) &&
                Objects.equals(notes, person.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, notes);
    }

}
