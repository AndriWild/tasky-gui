package gui;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.State;
import model.Task;

public class CenterArea extends HBox {

  private Label lblTodo;
  private Label lblDoing;
  private Label lblDone;

  private VBox todoBox;
  private VBox doingBox;
  private VBox doneBox;

  private TaskField regionTodo;
  private TaskField regionDoing;
  private TaskField regionDone;

  public CenterArea() {
    initializeControls();
    layoutControls();
  }

  private void updateTasks() {
    Map<State, List<Task>> map = TaskyPM.getInstance()
      .getTaskList()
      .stream()
      .collect(Collectors.groupingBy(Task::getState));

    regionTodo.update(map.get(State.TODO));
    regionDoing.update(map.get(State.DOING));
    regionDone.update(map.get(State.DONE));

  }

  private void initializeControls() {
    this.setPadding(new Insets(10));
    initializeLabels();
    initializeRegions();

    todoBox = new VBox(lblTodo, regionTodo);
    doingBox = new VBox(lblDoing, regionDoing);
    doneBox = new VBox(lblDone, regionDone);

    todoBox.setPadding(new Insets(10, 20, 10, 0));
    doingBox.setPadding(new Insets(10, 20, 10, 0));
    doneBox.setPadding(new Insets(10, 20, 10, 0));

    todoBox.setSpacing(10);
    doingBox.setSpacing(10);
    doneBox.setSpacing(10);

    BooleanProperty sync = new SimpleBooleanProperty();
    sync.bind(TaskyPM.getInstance().getSync());
    sync.addListener((val, oldValue, newValue) -> updateTasks());
  }

  private void initializeRegions() {
    regionTodo = new TaskField("2ecc71");
    regionDoing = new TaskField("20Ac71");
    regionDone = new TaskField("2ecFF1");
  }

  private void initializeLabels() {
    lblTodo = new Label("Todo");
    lblDoing = new Label("Doing");
    lblDone = new Label("Done");
  }

  private void layoutControls() {
    this.getChildren().addAll(todoBox, doingBox, doneBox);
  }
}
