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
    labels = new Label[ControlNames.values().length];

    for (ControlNames name : ControlNames.values()) {
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
    addColumn(0, labels);
    add(txtFieldId, 1, 0);
    add(txtFieldTitle, 1, 1);
    add(textAreaDesc, 1, 2);
    add(datePicker, 1, 3);
    add(choiceBoxState, 1, 4);
    add(buttonRow, 0, 5, 2, 1);
  }
}
