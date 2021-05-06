package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import model.State;
import model.Task;
import presentation.TaskyPM;

public class Lane extends VBox {
  private static final int TASKFIELD_HEIGHT = 500;
  private static final int TASKFIELD_WIDTH = Integer.MAX_VALUE;
  private IntegerProperty selectedId;
  private List<TaskLabel> labels;
  private FlowPane taskLane;
  private String color;
  private Label title;
  private State state;

  public Lane(String color, State state) {
    this.state = state;
    this.color = color;
    this.labels = new ArrayList<>();

    initializeControls();
    layoutControls();
  }

  public void update(List<Task> list) {
    if (list != null) {
      labels = list.stream()
        .sorted()
        .map(task -> new TaskLabel(task.getTitle(), task.getId(), color, 1.0 ))
        .collect(Collectors.toList());
    } else {
      labels.clear();
    }
    updateTaskLabels();
  }

  private void updateTaskLabels() {
    labels.stream().forEach(lbl -> lbl.setOpacity(0.7));
    Optional<TaskLabel> label = labels.stream().filter(lbl -> lbl.getTaskId() == selectedId.intValue()).findFirst();
    if(label.isPresent()){
      label.get().setOpacity(1.0);
      this.setOpacity(1.0);
    } else {
      this.setOpacity(0.8);
    }
    taskLane.getChildren().clear();
    taskLane.getChildren().addAll(labels);
  }

  private void initializeControls() {
    labels = new ArrayList<>();
    taskLane = new FlowPane();
    title = new Label(this.state.toString());
    selectedId = new SimpleIntegerProperty();
    selectedId.bind(TaskyPM.getInstance().getId());
    selectedId.addListener((val,val1,val2) -> updateTaskLabels());
  }

  private void layoutControls() {
    setStyle("-fx-background-color: LIGHTGREY; -fx-background-radius: 5, 4;");
    setPrefHeight(TASKFIELD_HEIGHT);
    setPrefWidth(TASKFIELD_WIDTH);
    setMaxHeight(TASKFIELD_HEIGHT);
    setPadding(new Insets(10));
    title.setPadding(new Insets(10));
    taskLane.setHgap(10);
    taskLane.setVgap(10);
    getChildren().addAll(title,taskLane);
  }
}
