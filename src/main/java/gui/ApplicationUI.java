package gui;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import model.State;
import model.Task;
import model.TaskData;

public class ApplicationUI extends BorderPane {

  private BottomArea bottomArea;
  private CenterArea centerArea;
  private Details details;
  private IntegerProperty id;
  private ObservableList<Task> taskList;

  public ApplicationUI() {
    taskList = FXCollections.observableArrayList();
    id = new SimpleIntegerProperty(0);
    bottomArea = new BottomArea(taskList);
    details = new Details(taskList);
    centerArea = new CenterArea(taskList);

    addTestTasks();
    updateTask();
    layoutControls();

    id.bindBidirectional(PresentationModel.getInstance().getId());
    id.addListener(change -> updateTask());
  }

  private void layoutControls() {
    this.setPadding(new Insets(20));
    this.setBottom(bottomArea);
    this.setCenter(centerArea);
    this.setRight(details);
  }

  private void addTestTasks() {
    taskList.add(new Task(new TaskData("A1", "B", State.TODO, LocalDate.parse("2001-08-21"))));
    taskList.add(new Task(new TaskData("B2", "B", State.DOING, LocalDate.parse("2001-08-21"))));
    taskList.add(new Task(new TaskData("C3", "B", State.DONE, LocalDate.parse("2001-08-21"))));
  }

  private void updateTask() {
    if (!taskList.isEmpty()) {
      Task task = taskList.get(id.get());
      PresentationModel.getInstance().setTitleValue(task.getTitle());
    }
  }

}
