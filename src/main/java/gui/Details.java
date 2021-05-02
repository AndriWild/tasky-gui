package gui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.State;

public class Details extends GridPane {

  private Label[] labels;
  private TextField txtFieldTitle;
  private TextField txtFieldId;
  private TextArea textAreaDesc;
  private DatePicker datePicker;
  private ChoiceBox<State> choiceBoxState;

  private HBox buttonRow;

  public Details() {
    this.setGridLinesVisible(false);
    this.setPadding(new Insets(50));
    this.setVgap(20);
    this.setHgap(10);
    this.setPrefWidth(400);

    initializeControls();
    layoutControls();
  }

  private void initializeControls() {
    labels = new Label[GridPosition.values().length];

    for (GridPosition name : GridPosition.values()) {
      labels[name.index] = new Label(name.name);
    }
    
    txtFieldId = new TextField();
    txtFieldTitle = new TextField();
    textAreaDesc = new TextArea();
    datePicker = new DatePicker();
    choiceBoxState = new ChoiceBox<>(FXCollections.observableArrayList(State.values()));
    
    TaskyPM pm = TaskyPM.getInstance();
    txtFieldId.textProperty().bind(pm.getId().asString());
    txtFieldTitle.textProperty().bindBidirectional(pm.getTitle());
    textAreaDesc.textProperty().bindBidirectional(pm.getDescription());
    choiceBoxState.valueProperty().bindBidirectional(pm.getState());
    datePicker.valueProperty().bindBidirectional(pm.getDate());
    
    txtFieldId.setDisable(true);
    
    Button btnSave = new Button("Save");
    Button btnDelete = new Button("Delete");
    buttonRow = new HBox(btnSave, btnDelete);
    buttonRow.setPadding(new Insets(10, 10, 10, 0));
    buttonRow.setSpacing(20);
    
    btnDelete.setOnAction(event -> TaskyPM.getInstance().deleteTask());
    btnSave.setOnAction(event -> TaskyPM.getInstance().save());
  }

  private void layoutControls() {
    int row = 0;
    addColumn(row, labels);
    row = 1;
    add(txtFieldId, row, GridPosition.ID.index);
    add(txtFieldTitle, row, GridPosition.TITLE.index);
    add(textAreaDesc, row, GridPosition.DESCRIPTION.index);
    add(datePicker, row, GridPosition.DATE.index);
    add(choiceBoxState, row, GridPosition.STATE.index);
    add(buttonRow, 0, 5, 2, 1);
  }
}
