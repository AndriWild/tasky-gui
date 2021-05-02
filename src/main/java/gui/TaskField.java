package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import model.State;
import model.Task;

public class TaskField extends FlowPane {
  private static final int TASKFIELD_HEIGHT = 500;
  private static final int TASKFIELD_WIDTH = Integer.MAX_VALUE;
  private List<TaskLabel> labels;
  private final State state;

  public TaskField(String color, State state) {
    this.setStyle("-fx-background-color: #" + color + ";");
    this.state = state;
    labels = new ArrayList<>();

    initializeControls();
    layoutControls();
  }

  public void update(List<Task> list) {
    if (list != null) {
      labels = list.stream()
      .map(task -> new TaskLabel(task.getTitle(), task.getId()))
      .collect(Collectors.toList());
    } else {
      labels.clear();
    }
    this.getChildren().clear();
    this.getChildren().addAll(labels);
  }

  private void initializeControls() {
    labels = new ArrayList<>();
  }

  private void layoutControls() {
    this.setPrefHeight(TASKFIELD_HEIGHT);
    this.setPrefWidth(TASKFIELD_WIDTH);
    this.setMaxHeight(TASKFIELD_HEIGHT);
    this.setHgap(10);
    this.setVgap(10);
    this.setPadding(new Insets(5));
  }
}
