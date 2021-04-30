package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Task;

public class Details extends GridPane {

  private Label[] labels;
  private TextField txtFieldTitle;
  private TextField txtFieldId;
  private TextArea textAreaDesc;
  private DatePicker datePicker;
  private ChoiceBox<State> choiceBoxState;

  private HBox buttonRow;
  private Button btnSave;
  private Button btnDelete;

  public Details(ObservableList<Task> taskList) {
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
    txtFieldId.setDisable(true);
    txtFieldId.textProperty().bind(PresentationModel.getInstance().getId().asString());

    txtFieldTitle = new TextField();
    txtFieldTitle.textProperty().bindBidirectional(PresentationModel.getInstance().getTitle());
    textAreaDesc = new TextArea();
    textAreaDesc.textProperty().bindBidirectional(PresentationModel.getInstance().getDescription());

    datePicker = new DatePicker();
    choiceBoxState = new ChoiceBox<>(FXCollections.observableArrayList(State.values()));

    btnSave = new Button("Save");
    btnDelete = new Button("Delete");
    buttonRow = new HBox(btnSave, btnDelete);
    buttonRow.setPadding(new Insets(10, 10, 10, 0));
    buttonRow.setSpacing(20);
    btnDelete.setOnAction(event -> deleteTask());
    btnSave.setOnAction(event -> saveChanges());

  }

  private void saveChanges() {
    // if (!txtFieldId.getText().isEmpty()) {
    // int id = Integer.parseInt(txtFieldId.getText());
    // Optional<Task> task = list.stream().filter(e -> e.getId() == id).findAny();
    // if (task.isPresent()) {
    // list.remove(task.get());
    // task.get().setTitle(txtFieldTitle.getText());
    // task.get().setDescription(textAreaDesc.getText());
    // task.get().setStatus(choiceBoxState.getValue());
    // task.get().setDate(datePicker.getValue());
    // list.add(task.get());
    // }
    // }
  }

  private void deleteTask() {
    // if (!txtFieldId.getText().isEmpty()) {
    // int id = Integer.parseInt(txtFieldId.getText());
    // Optional<Task> task = list.stream().filter(e -> e.getId() == id).findAny();
    // if (task.isPresent()) {
    // list.remove(task.get());
    // }
    // }
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
