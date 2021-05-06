package presentation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.State;
import model.Task;
import model.TaskContainer;
import model.TaskData;
import model.TaskList;

public class TaskyPM {
  private static final TaskyPM INSTANCE = new TaskyPM();

  private TaskContainer taskList;
  private IntegerProperty id;
  private StringProperty title;
  private StringProperty description;
  private ObjectProperty<State> state;
  private ObjectProperty<LocalDate> date;
  private StringProperty windowTitle;
  private BooleanProperty btnSaveDisable;
  private BooleanProperty refresh;

  public static TaskyPM getInstance() {
    return INSTANCE;
  }

  private TaskyPM() {
    taskList = new TaskList();
    addTestTasks();
    initializeProperties();
  }

  public void setId(int newId) {
    Optional<Task> selectedTask = taskList.get()
        .stream()
        .filter(task -> task.getId() == newId)
        .findAny();

    if( selectedTask.isPresent() ){
      Task task = selectedTask.get();
      id.set(newId);
      title.set(task.getTitle());
      description.set(task.getDescription());
      date.set(task.getDate());
      state.set(task.getState());
      System.out.println("TaskyPM.setId():" + this.id.get());
      }
  }

  public List<Task> getTaskList() {
    return taskList.get();
  }

  public IntegerProperty getId() {
    return id;
  }

  public ObjectProperty<LocalDate> getDate() {
    return date;
  }

  public ObjectProperty<State> getState() {
    return state;
  }

  public StringProperty getTitle() {
    return title;
  }

  public StringProperty getDescription() {
    return description;
  }

  public StringProperty getWindowTitle() {
    return windowTitle;
  }

  public BooleanProperty getBtnSaveEnable(){
    return btnSaveDisable;
  }

  public BooleanProperty getRefresh() {
    return refresh;
  }

  public void createTask() {
    int taskId = taskList.add(new TaskData("", "", State.TODO, null));
    setId(taskId);
    btnSaveDisable.set(false);
    refresh();
  }

  public void deleteTask() {
    taskList.delete(id.get());
    if(taskList.get().isEmpty()){
      btnSaveDisable.set(true);
    } 
    refresh();
  }

  public void save() {
    Task selectedTask = taskList.get(id.get());
    selectedTask.setTitle(title.get());
    selectedTask.setDescription(description.get());
    selectedTask.setDate(date.get());
    selectedTask.setStatus(state.get());
    refresh();
  }

  private void initializeProperties() {
    id = new SimpleIntegerProperty();
    title = new SimpleStringProperty();
    description = new SimpleStringProperty();
    state = new SimpleObjectProperty<>();
    date = new SimpleObjectProperty<>();
    windowTitle = new SimpleStringProperty("Tasky");
    btnSaveDisable = new SimpleBooleanProperty();
    refresh = new SimpleBooleanProperty();
  }

  public void refresh(){
    refresh.set(!refresh.get());
  }

  private void addTestTasks() {
    taskList.add(new TaskData("A1", "A", State.TODO, LocalDate.parse("2001-08-21")));
    taskList.add(new TaskData("B2", "B", State.DOING, LocalDate.parse("2001-08-22")));
    taskList.add(new TaskData("C3", "C", State.DONE, LocalDate.parse("2001-08-23")));
    taskList.add(new TaskData("D3", "D", State.REVIEW, LocalDate.parse("2001-08-24")));
  }

}
