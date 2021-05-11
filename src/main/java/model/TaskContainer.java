package model;

import java.util.List;

public interface TaskContainer {

  public int add(int id, TaskData data);

  public void delete(int id);

  public Task get(int id);

  public void update(int id, TaskData data);

  public List<Task> get();

  public void addAll(List<Task> list);
}
