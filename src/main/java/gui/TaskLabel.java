package gui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import model.State;

public class TaskLabel extends Label {
  private static final int TASK_HEIGHT = 80;
  private static final int TASK_WIDTH = 80;
  private final int id;
  private IntegerProperty selectedId;
  private State state;

  public TaskLabel(String title, int id, State state) {
    super(title);
    this.id = id;
    this.state = state;

    initializeControls();
    layoutControls();
  }

  public void updateTitle(String title) {
    this.setText(title);
  }

  private void initializeControls() {
    setOpacity(0.8);
    // setStyle("-fx-border-color: white;");
    setStyle("-fx-background-color: " + state.color() + "; -fx-background-radius: 5, 4;");
    setPadding(new Insets(10));
    setPrefHeight(TASK_HEIGHT);
    setPrefWidth(TASK_WIDTH);
    setOnMouseClicked(event -> TaskyPM.getInstance().setId(id));
    selectedId = new SimpleIntegerProperty();
    selectedId.bind(TaskyPM.getInstance().getId());

    selectedId.addListener((val,oldVal,newVal) -> {
      if(newVal.intValue() == id){
        setOpacity(1.0);
      } else {
        setOpacity(0.6);
      }
    });
  }

  private void layoutControls() {
  }

}
