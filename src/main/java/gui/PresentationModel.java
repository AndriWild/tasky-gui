package gui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.State;

public class PresentationModel {
  private static final PresentationModel INSTANCE = new PresentationModel();
  private IntegerProperty id;
  private StringProperty title;
  private StringProperty description;
  private ObjectProperty<State> state;

  public static PresentationModel getInstance() {
    return INSTANCE;
  }

  private PresentationModel() {
    initializeProperties();
  }

  public IntegerProperty getId() {
    return id;
  }

  public void setId(int id) {
    this.id.set(id);
  }

  public ObjectProperty<State> getState() {
    return state;
  }

  public void setState(ObjectProperty<State> state) {
    this.state = state;
  }

  public StringProperty getTitle() {
    return title;
  }

  public void setTitle(StringProperty title) {
    this.title = title;
  }

  public void setTitleValue(String title) {
    this.title.set(title);
  }

  public StringProperty getDescription() {
    return description;
  }

  public void setDescription(StringProperty description) {
    this.description = description;
  }

  private void initializeProperties() {
    id = new SimpleIntegerProperty();
    title = new SimpleStringProperty();
    description = new SimpleStringProperty();
    state = new SimpleObjectProperty<>();

    id.addListener(change -> System.out.println("ID Change: " + id.getValue()));
  }

}
