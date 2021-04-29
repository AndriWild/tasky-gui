package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.State;

public class CenterArea extends HBox {

  Label lblTodo;
  Label lblDoing;
  Label lblDone;

  VBox todoBox;
  VBox doingBox;
  VBox doneBox;

  TaskField regionTodo;
  TaskField regionDoing;
  TaskField regionDone;

  public CenterArea() {
    this.setPadding(new Insets(10));
    initializeControls();
    layoutControls();
  }

  private void initializeControls() {
    initializeLabels();
    initializeRegions();

    todoBox = new VBox(lblTodo, regionTodo);
    doingBox = new VBox(lblDoing, regionDoing);
    doneBox = new VBox(lblDone, regionDone);

    todoBox.setPadding(new Insets(10, 20, 10, 0));
    doingBox.setPadding(new Insets(10, 20, 10, 0));
    doneBox.setPadding(new Insets(10, 20, 10, 0));

    todoBox.setSpacing(10);
    doingBox.setSpacing(10);
    doneBox.setSpacing(10);
  }

  private void layoutControls() {
    this.getChildren().addAll(todoBox, doingBox, doneBox);
  }

  private void initializeRegions() {
    regionTodo = new TaskField("2ecc71", State.TODO);
    regionDoing = new TaskField("20Ac71", State.DOING);
    regionDone = new TaskField("2ecFF1", State.DONE);
  }

  private void initializeLabels() {
    lblTodo = new Label("Todo");
    lblDoing = new Label("Doing");
    lblDone = new Label("Done");
  }

}
