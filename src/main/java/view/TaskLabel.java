package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import presentation.TaskyPM;

public class TaskLabel extends BorderPane {
  private static final int TASK_HEIGHT = 80;
  private static final int TASK_WIDTH = 80;

  private int id;
  private String title;
  private Label titleLabel;
  private Label idLabel;

  public TaskLabel(String title, int id, String color, double opacity) {
    this.title = title;
    this.id = id;
    initializeControls(color, opacity);
    layoutControls();
  }

  public int getTaskId(){
    return id;
  }

  private void initializeControls(String color, double opacity) {
    setStyle("-fx-background-color: " + color + "; -fx-background-radius: 5, 4;");
    setOpacity(opacity);

    titleLabel = new Label(title);
    idLabel = new Label(String.valueOf(id));
    setOnMouseClicked(event -> TaskyPM.getInstance().setPropertiesById(id));
  }

  private void layoutControls() {
    setPadding(new Insets(10));
    setPrefHeight(TASK_HEIGHT);
    setPrefWidth(TASK_WIDTH);
    setTop(idLabel);
    setBottom(titleLabel);
  }

}
