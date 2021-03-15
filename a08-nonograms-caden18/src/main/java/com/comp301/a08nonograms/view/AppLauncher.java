package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.PuzzleLibrary;
import com.comp301.a08nonograms.controller.Controller;
import com.comp301.a08nonograms.controller.ControllerImpl;
import com.comp301.a08nonograms.model.Board;
import com.comp301.a08nonograms.model.Clues;
import com.comp301.a08nonograms.model.Model;
import com.comp301.a08nonograms.model.ModelImpl;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Launch your GUI here
    // Model
    List<Clues> clues = PuzzleLibrary.create();
    Model model = new ModelImpl(clues);

    // Controller
    Controller controller = new ControllerImpl(model);

    // View
    View view = new View(controller);

    // Make scene
    stage.setScene(new Scene(view.render()));

    model.addObserver(
        (Model m) -> {
          stage.setScene(new Scene(view.render()));
        });
    stage.setTitle("Caden's Nonogram");
    stage.show();
  }
}
