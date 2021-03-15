package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ButtonView implements FXComponent {
  private final Controller controller;

  public ButtonView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox hBox = new HBox();
    hBox.setPadding(new Insets(15, 12, 15, 12));
    hBox.setSpacing(10);
    hBox.setStyle("-fx-background-color: #87CEFA;");

    Button buttonPrev = new Button("<");
    buttonPrev.setPrefSize(20, 20);
    buttonPrev.setOnMouseClicked(
        (MouseEvent event) -> {
          if (event.getButton() == MouseButton.PRIMARY) {
            controller.prevPuzzle();
          }
        });
    Button buttonNext = new Button(">");
    buttonNext.setPrefSize(20, 20);
    buttonNext.setOnMouseClicked(
        (MouseEvent event) -> {
          if (event.getButton() == MouseButton.PRIMARY) {
            controller.nextPuzzle();
          }
        });
    Button puzzleCount = new Button("Puzzle: " + (controller.getPuzzleIndex() + 1) + "/5");
    puzzleCount.setPrefSize(100, 20);

    Button randPuzzle = new Button("Random");
    randPuzzle.setPrefSize(100, 20);
    randPuzzle.setOnMouseClicked(
        (MouseEvent event) -> {
          if (event.getButton() == MouseButton.PRIMARY) {
            controller.randPuzzle();
          }
        });
    Button clearPuzzle = new Button("Clear");
    clearPuzzle.setPrefSize(100, 20);
    clearPuzzle.setOnMouseClicked(
        (MouseEvent event) -> {
          if (event.getButton() == MouseButton.PRIMARY) {
            controller.clearBoard();
          }
        });
    hBox.getChildren().addAll(buttonPrev, puzzleCount, buttonNext, randPuzzle, clearPuzzle);
    return hBox;
  }
}
