package presentation;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Starter extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent rootPane = new ApplicationUI();
    Scene scene = new Scene(rootPane);

    primaryStage.titleProperty().bind(TaskyPM.getInstance().getWindowTitle());
    primaryStage.setScene(scene);
    primaryStage.setHeight(800);
    primaryStage.setWidth(1000);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch();
  }
}
