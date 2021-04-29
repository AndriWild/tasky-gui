package model;

import java.time.LocalDate;

public class TaskData {

  private String title;
  private String description;
  private State status;
  private LocalDate date;

  public TaskData(String title, String description, State status, LocalDate date) {
    this.title = title;
    this.description = description;
    this.status = status;
    this.date = date;
  }

  public void setTitle(String title) {
    if (title != null)
      this.title = title;
  }

  public void setDescription(String description) {
    if (description != null)
      this.description = description;

  }

  public void setStatus(State status) {
    if (status != null)
      this.status = status;
  }

  public void setDate(LocalDate date) {
    if (date != null)
      this.date = date;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public LocalDate getDate() {
    return date;
  }

  public State getStatus() {
    return this.status;
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TaskData) {
      TaskData data = (TaskData) that;
      return this.title == data.title && this.description == data.description && this.date == data.date
          && this.status == data.status;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((status == null) ? 0 : status.hashCode());
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    return result;
  }

}
