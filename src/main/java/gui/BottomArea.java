package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class BottomArea extends HBox {

  private HBox buttonRow;
  private PresentationModel model;


  public BottomArea(PresentationModel model) {
    this.model = model;
    this.setPadding(new Insets(10));

    this.minHeight(100);
    initializeControls();

    layoutControls();
  }

  private void initializeControls() {
    Button btnNew = new Button("New");
    Button btnRefresh = new Button("Refresh");
    buttonRow = new HBox(btnNew, btnRefresh);
    buttonRow.setSpacing(20);
    buttonRow.setPadding(new Insets(10, 10, 10, 0));

    btnNew.setOnAction(event -> createNewTask());
  }

  private void createNewTask() {

  }

  private void layoutControls() {
    this.getChildren().add(buttonRow);
  }

}
