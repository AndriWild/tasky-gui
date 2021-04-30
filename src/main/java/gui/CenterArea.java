package gui;

import javafx.collections.ObservableList;
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

  private ObservableList<Task> taskList;

  public CenterArea(ObservableList<Task> taskList) {
    this.taskList = taskList;
    this.setPadding(new Insets(10));
    initializeControls();
    layoutControls();
  }

  private void layoutControls() {
    this.getChildren().addAll(todoBox, doingBox, doneBox);
  }

  private void initializeRegions() {
    regionTodo = new TaskField("2ecc71", State.TODO, taskList);
    regionDoing = new TaskField("20Ac71", State.DOING, taskList);
    regionDone = new TaskField("2ecFF1", State.DONE, taskList);
  }

  private void initializeLabels() {
    lblTodo = new Label("Todo");
    lblDoing = new Label("Doing");
    lblDone = new Label("Done");
  }

  private void initializeControls() {
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
  }
}
