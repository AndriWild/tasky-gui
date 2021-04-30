package gui;

import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import model.State;
import model.Task;

public class TaskField extends FlowPane {
  private static final int TASKFIELD_HEIGHT = 500;
  private static final int TASKFIELD_WIDTH = Integer.MAX_VALUE;
  private String color;
  private final State state;
  private ObservableList<Task> taskList;

  public TaskField(String color, State state, ObservableList<Task> taskList) {
    this.taskList = taskList;
    this.color = color;
    this.state = state;

    initializeControls();
    layoutControls();
  }

  private void initializeControls() {
    this.setStyle("-fx-background-color: #" + color + ";");
    this.setPrefHeight(TASKFIELD_HEIGHT);
    this.setPrefWidth(TASKFIELD_WIDTH);
    this.setMaxHeight(TASKFIELD_HEIGHT);
    this.setHgap(10);
    this.setVgap(10);
    this.setPadding(new Insets(5));

    ListChangeListener<Task> listener = change -> listChangeOccure(change);
    taskList.addListener(listener);

  }

  private void listChangeOccure(Change<? extends Task> change) {
    while (change.next()) {
      if (change.wasPermutated()) {
        for (int i = change.getFrom(); i < change.getTo(); ++i) {
          // permutate
          System.out.println("Permutet!");
        }
      } else if (change.wasUpdated()) {
        System.out.println("Change was updated");
      } else {
        for (Task remitem : change.getRemoved()) {

          if (this.getChildren().contains(remitem.getLabel())) {
            System.out.println("Item removed: " + remitem);
            this.getChildren().remove(remitem.getLabel());
          }
        }
        for (Task additem : change.getAddedSubList()) {
          System.out.println(additem);
          if (additem.getStatus() == state) {
            additem.getLabel().updateTitle(additem.getTitle());
            this.getChildren().add(additem.getLabel());
          }
        }
      }
    }
  }

  private void layoutControls() {

  }
}
