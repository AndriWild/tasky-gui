package presentation;

import java.time.LocalDate;
import java.util.List;

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
  private BooleanProperty sync;
  private StringProperty windowTitle;

  public static TaskyPM getInstance() {
    return INSTANCE;
  }

  private TaskyPM() {
    taskList = new TaskList();
    addTestTasks();
    initializeProperties();
  }

  public void setId(int id) {
    Task task = taskList.get(id);
    this.id.set(id);
    this.title.set(task.getTitle());
    this.description.set(task.getDescription());
    this.date.set(task.getDate());
    this.state.set(task.getState());
  }

  public List<Task> getTaskList() {
    System.out.println(taskList.get());
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

  public void setTitle(StringProperty title) {
    this.title = title;
  }


  public StringProperty getDescription() {
    return description;
  }


  public BooleanProperty getSync() {
    return sync;
  }

  public StringProperty getWindowTitle() {
    return windowTitle;
  }

  public void createTask() {
    int newTaskID = taskList.add(new TaskData("", "", State.TODO, null));
    this.id.set(newTaskID);
    syncUserInterface();
  }

  public void deleteTask() {
    taskList.delete(id.get());
    syncUserInterface();
  }

  public void save() {
    int selectedId = this.id.get();
    Task selectedTask = taskList.get(selectedId);
    selectedTask.setTitle(title.get());
    selectedTask.setDescription(description.get());
    selectedTask.setDate(date.get());
    selectedTask.setStatus(state.get());
    syncUserInterface();
  }

  public void refresh() {
    syncUserInterface();
  }

  private void syncUserInterface(){
    this.sync.set(!this.sync.get());
  }

  private void initializeProperties() {
    id = new SimpleIntegerProperty();
    title = new SimpleStringProperty();
    description = new SimpleStringProperty();
    state = new SimpleObjectProperty<>();
    date = new SimpleObjectProperty<>();
    sync = new SimpleBooleanProperty(true);
    windowTitle = new SimpleStringProperty("Tasky");
  }

  private void addTestTasks() {
    taskList.add(new TaskData("A1", "B", State.TODO, LocalDate.parse("2001-08-21")));
    taskList.add(new TaskData("B2", "B", State.DOING, LocalDate.parse("2001-08-22")));
    taskList.add(new TaskData("C3", "B", State.DONE, LocalDate.parse("2001-08-23")));
  }

}
