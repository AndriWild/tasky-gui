package gui;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import model.State;
import model.Task;
import model.TaskData;

public class BottomArea extends HBox {

  private HBox buttonRow;
  private ObservableList<Task> taskList;

  public BottomArea(ObservableList<Task> taskList) {
    this.taskList = taskList;

    this.setPadding(new Insets(10));
    this.minHeight(100);

    initializeControls();
    layoutControls();
  }

  private void initializeControls() {
    Button btnNew = new Button("New");
    Button btnRefresh = new Button("Refresh");
    buttonRow = new HBox(btnNew, btnRefresh);
    buttonRow.setSpacing(20);
    buttonRow.setPadding(new Insets(10, 10, 10, 0));

    btnNew.setOnAction(event -> createNewTask());
  }

  private void createNewTask() {
    Task task = new Task(new TaskData("", "", State.TODO, null));
    taskList.add(task);
    PresentationModel.getInstance().setId(task.getId());
  }

  private void layoutControls() {
    this.getChildren().add(buttonRow);
  }

}
