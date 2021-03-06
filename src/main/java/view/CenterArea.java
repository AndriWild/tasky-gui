package view;

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
import presentation.TaskyPM;

public class CenterArea extends HBox {
  private BooleanProperty refresh;
  private Map<State,Lane> laneMap;

  public CenterArea() {
    initializeControls();
    layoutControls();
    updateTasks();
  }

  private void updateTasks() {
    Map<State, List<Task>> map = TaskyPM.getInstance()
      .getTaskList()
      .stream()
      .collect(Collectors.groupingBy(Task::getState));

    for (State state : State.values()) {
      laneMap.get(state).update(map.get(state));
    }
  }

  private void initializeControls() {
    setPadding(new Insets(10));
    laneMap = new EnumMap<>(State.class);

    for (State state  : State.values()) {
      laneMap.put(state, new Lane(state.color(), state));
    }

    refresh = new SimpleBooleanProperty();
    refresh.bind(TaskyPM.getInstance().getRefresh());
    refresh.addListener((val,val1,val2)-> updateTasks());
  }

  private void layoutControls() {
    getChildren().addAll(laneMap.values());
    getChildren().stream().forEach(e -> setMargin(e, new Insets(10)));
  }
}
