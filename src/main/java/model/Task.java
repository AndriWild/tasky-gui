package model;

import java.time.LocalDate;

import gui.TaskLabel;

public class Task {

  private int id;
  private TaskData data;
  private TaskLabel label;
  private static int idCounter = 1;

  public Task(TaskData data) {
    this.id = idCounter;
    idCounter++;
    this.data = data;
    this.label = new TaskLabel(data.getTitle(), id);
  }

  public TaskLabel getLabel() {
    return this.label;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return data.getTitle();
  }

  public String getDescription() {
    return data.getDescription();
  }

  public State getStatus() {
    return data.getStatus();
  }

  public LocalDate getDate() {
    return data.getDate();
  }

  public void setTitle(String title) {
    data.setTitle(title);
  }

  public void setDescription(String description) {
    data.setDescription(description);
  }

  public void setStatus(State status) {
    data.setStatus(status);
  }

  public void setDate(LocalDate date) {
    data.setDate(date);
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Task) {
      Task task = (Task) that;
      return this.id == task.id && this.data == task.data;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = id;
    result = prime * result + ((data == null) ? 0 : data.hashCode());
    return result;
  }

  @Override
  public String toString() {
    String dateTimeString = "";
    if (data.getDate() != null) {
      dateTimeString = data.getDate().toString();
    }
    return "[ID:" + this.id + "]" + "\tTitle: " + data.getTitle() + "\tDescription: " + data.getDescription()
        + "\tStatus: " + data.getStatus().toString().toLowerCase() + "\tFällig am: " + dateTimeString;
  }

}
