package gui;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import model.Task;

public class ApplicationUI extends BorderPane {

  private BottomArea bottomArea;
  private CenterArea centerArea;
  private RightArea rightArea;
  private PresentationModel model;

  public ApplicationUI(PresentationModel model) {
    this.model = model;
    bottomArea = new BottomArea(model);

  }

  public static ObservableList<Task> getRepository() {
    return null;
  }

  public static Object updateTaskSelected(long id) {
    return null;
  }

}
