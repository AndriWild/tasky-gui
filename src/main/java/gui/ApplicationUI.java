package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

public class ApplicationUI extends BorderPane {

  private BottomArea bottomArea;
  private CenterArea centerArea;
  private Details details;

  public ApplicationUI() {

    initializeControls();
    layoutControls();
  }

  private void layoutControls() {
    this.setPadding(new Insets(20));
    this.setBottom(bottomArea);
    this.setCenter(centerArea);
    this.setRight(details);
  }

  private void initializeControls() {
    bottomArea = new BottomArea();
    details = new Details();
    centerArea = new CenterArea();
  }

}
