package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import presentation.TaskyPM;

public class Bottom extends HBox {

  private HBox buttonRow;

  public Bottom() {

    this.setPadding(new Insets(10));
    this.minHeight(100);

    initializeControls();
    layoutControls();
  }

  private void initializeControls() {
    Button btnNew = new Button("New"); // Muss dieser Test auch in die PM Klasse?
    Button btnRefresh = new Button("Refresh");
    buttonRow = new HBox(btnNew, btnRefresh);
    buttonRow.setSpacing(20);
    buttonRow.setPadding(new Insets(10, 10, 10, 0));
    btnRefresh.setOnAction(event -> TaskyPM.getInstance().refresh());
    btnNew.setOnAction(event -> TaskyPM.getInstance().createTask());
  }

  private void layoutControls() {
    this.getChildren().add(buttonRow);
  }
}
