package gui;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import model.State;
import model.Task;

public class CenterArea extends HBox {
  private BooleanProperty sync;
  private Map<State,TaskField> fieldMap;

  public CenterArea() {
    initializeControls();
    layoutControls();
  }

  private void updateTasks() {
    System.out.println("CenterArea.updateTasks()");
    Map<State, List<Task>> map = TaskyPM.getInstance()
      .getTaskList()
      .stream()
      .collect(Collectors.groupingBy(Task::getState));

    for (State state : State.values()) {
      fieldMap.get(state).update(map.get(state));
    }
  }

  private void initializeControls() {
    setPadding(new Insets(10));
    fieldMap = new EnumMap<>(State.class);

    for (State state  : State.values()) {
      fieldMap.put(state, new TaskField("cfcfcf", state));
    }

    sync = new SimpleBooleanProperty();
    sync.bind(TaskyPM.getInstance().getSync());
    sync.addListener((val, oldValue, newValue) -> updateTasks());
  }

  private void layoutControls() {
    getChildren().addAll(fieldMap.values());
    getChildren().stream().forEach(e -> setMargin(e, new Insets(10)));
  }
}
