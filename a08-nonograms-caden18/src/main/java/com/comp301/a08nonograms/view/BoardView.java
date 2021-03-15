package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

public class BoardView implements FXComponent {
  private final Controller controller;

  public BoardView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane gridPane = new GridPane();
    gridPane.setHgap(3);
    gridPane.setVgap(3);
    gridPane.setPadding(new Insets(25, 25, 25, 25));

    for (int i = 1; i < controller.getClues().getHeight() + 1; i++) {
      int count = 0;
      String resulting_string = "";
      for (int j = 0; j < controller.getClues().getRowCluesLength(); j++) {
        if (controller.getClues().getRowClues(i - 1)[j] != 0) {
          count++;
        }
      }
      resulting_string +=
          controller.getClues().getRowClues(i - 1)[controller.getClues().getRowCluesLength() - 1];
      for (int k = 1; k < count; k++) {
        resulting_string +=
            " "
                + controller.getClues()
                    .getRowClues(i - 1)[controller.getClues().getRowCluesLength() - 1 - k];
      }
      Label row = new Label(resulting_string);
      row.setTextAlignment(TextAlignment.RIGHT);
      gridPane.add(row, 0, i);
    }
    for (int j = 1; j < controller.getClues().getWidth() + 1; j++) {
      int count = 0;
      String resulting_string = "";
      for (int i = 0; i < controller.getClues().getColCluesLength(); i++) {
        if (controller.getClues().getColClues(j - 1)[i] != 0) {
          count++;
        }
      }
      resulting_string +=
          controller.getClues().getColClues(j - 1)[controller.getClues().getColCluesLength() - 1];
      for (int l = 1; l < count; l++) {
        resulting_string +=
            "\n"
                + controller.getClues()
                    .getColClues(j - 1)[controller.getClues().getColCluesLength() - 1 - l];
      }
      Label col = new Label(resulting_string);
      col.setTextAlignment(TextAlignment.CENTER);
      gridPane.add(col, j, 0);
    }
    for (int i = 1; i < controller.getClues().getHeight() + 1; i++) {
      for (int j = 1; j < controller.getClues().getWidth() + 1; j++) {
        int row = i - 1;
        int col = j - 1;
        Button box = new Button();
        box.setPrefSize(20, 20);
        if (controller.isShaded(row, col)) {
          box.setStyle("-fx-background-color: #87CEFA");
        }
        if (controller.isEliminated(row, col)) {
          box.setText("X");
        }
        box.setOnMouseClicked(
            event -> {
              if (event.getButton() == MouseButton.PRIMARY) {
                controller.toggleShaded(row, col);
              }
              if (event.getButton() == MouseButton.SECONDARY) {
                controller.toggleEliminated(row, col);
              }
            });
        gridPane.add(box, j, i);
      }
    }
    return gridPane;
  }
}
