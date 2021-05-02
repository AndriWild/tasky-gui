package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskMap implements TaskContainer {

  private int idCounter = 0;
  private Map<Integer, Task> tasks = new HashMap<>();

  @Override
  public int add(TaskData taskData) {
    if (taskData != null) {
      Task task = new Task(taskData);
      tasks.put(idCounter++, task);
      return task.getId();
    }
    return -1;
  }

  @Override
  public void delete(int id) {
    if (tasks.containsKey(id)) {
      tasks.remove(id);
    }
  }

  @Override
  public Task get(int id) {
    return tasks.get(id);
  }

  @Override
  public void update(int id, TaskData data) {
    if (tasks.containsKey(id)) {
      Task task = tasks.get(id);

      task.setTitle(data.getTitle());

      task.setDescription(data.getDescription());

      task.setDate(data.getDate());

      task.setStatus(data.getStatus());

    }
  }

  @Override
  public List<Task> get() {
    return new ArrayList<>(tasks.values());
  }

}
