package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import model.State;
import model.Task;

public class TaskField extends VBox {
  private static final int TASKFIELD_HEIGHT = 500;
  private static final int TASKFIELD_WIDTH = Integer.MAX_VALUE;
  private FlowPane taskLane;
  private Label title;
  private State state;
  private List<TaskLabel> labels;

  public TaskField(String color, State state) {
    this.setStyle("-fx-background-color: #" + color + ";");
    this.state = state;
    labels = new ArrayList<>();

    initializeControls();
    layoutControls();
  }

  public void update(List<Task> list) {
    System.out.println("TaskField.update()");
    if (list != null) {
      labels = list.stream()
        .map(task -> new TaskLabel(task.getTitle(), task.getId(), task.getState()))
        .collect(Collectors.toList());
    } else {
      labels.clear();
    }
    taskLane.getChildren().clear();
    taskLane.getChildren().addAll(labels);
  }

  private void initializeControls() {
    labels = new ArrayList<>();
    taskLane = new FlowPane();
    title = new Label(this.state.toString());
  }

  private void layoutControls() {
    setPrefHeight(TASKFIELD_HEIGHT);
    setPrefWidth(TASKFIELD_WIDTH);
    setMaxHeight(TASKFIELD_HEIGHT);
    setPadding(new Insets(5));
    taskLane.setHgap(10);
    taskLane.setVgap(10);
    getChildren().addAll(title,taskLane);
  }
}
