package presentation;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import view.Bottom;
import view.CenterArea;
import view.Details;

public class ApplicationUI extends BorderPane {

  private Bottom bottomArea;
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
    bottomArea = new Bottom();
    centerArea = new CenterArea();
    details = new Details();
  }
}
