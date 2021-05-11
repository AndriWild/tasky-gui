package presentation;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import io.FileRepository;
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
  private FileRepository fr;

  private int maxId = 0;

  public static TaskyPM getInstance() {
    return INSTANCE;
  }

  private TaskyPM() {
    taskList = new TaskList();
    // addTestTasks();
    initializeProperties();
    loadTaskFromFile();
    setMaxId();
  }



  public void createTask() {
    int taskId = taskList.add(++maxId ,new TaskData("", "", State.TODO, null));
    setPropertiesById(taskId);
    if( btnSaveDisable.get() ) btnSaveDisable.set(false);
    refresh();
  }

  public void deleteTask() {
    taskList.delete(id.get());
    if (taskList.get().isEmpty()) btnSaveDisable.set(true);
    
    int taskId = getIdOfFirstTask();
    setPropertiesById(taskId);
    refresh();
  }

  public void setPropertiesById(int newId) {
    Task selectedTask = taskList.get()
        .stream()
        .filter(task -> task.getId() == newId)
        .findAny().orElse(null);

    if( selectedTask != null ) {
      setAllProperties(selectedTask);
    } else {
      clearAllProperties();
    }
  }

  public void save() {
    Task selectedTask = taskList.get(id.get());
    updateProperties(selectedTask);
    fr.save2(taskList.get());
  }

  public void refresh() {
    refresh.set(!refresh.get());
    fr.save2(taskList.get());
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

  private void setMaxId() {
    Optional<Task> maxIdTask = taskList.get().stream().max(Comparator.comparingInt(Task::getId));
    if(maxIdTask.isPresent()){
      maxId = maxIdTask.get().getId();
    }
  }

  private void loadTaskFromFile(){
    List<Task> list = fr.load2();
    taskList.addAll(list);
  }

  private void clearAllProperties() {
    id.set(-1);
    title.set("");
    description.set("");
    date.set(null);
    state.set(null);
  }

  private void setAllProperties(Task task) {
    id.set(task.getId());
    title.set(task.getTitle());
    description.set(task.getDescription());
    date.set(task.getDate());
    state.set(task.getState());
  }

  private int getIdOfFirstTask() {
    Optional<Task> selectedTask = 
      taskList.get().stream()
                    .sorted()
                    .filter(task -> task.getId() != -1)
                    .findFirst();

    if (selectedTask.isPresent()) {
      return selectedTask.get().getId();
    } else {
      return -1;
    }
  }

  private void updateProperties(Task selectedTask) {
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
    fr = new FileRepository();
  }

  // private void addTestTasks() {
  //   taskList.add(new TaskData("A1", "A", State.TODO, LocalDate.parse("2001-08-21")));
  //   taskList.add(new TaskData("B2", "B", State.DOING, LocalDate.parse("2001-08-22")));
  //   taskList.add(new TaskData("C3", "C", State.DONE, LocalDate.parse("2001-08-23")));
  //   taskList.add(new TaskData("D3", "D", State.REVIEW, LocalDate.parse("2001-08-24")));
  // }

}
