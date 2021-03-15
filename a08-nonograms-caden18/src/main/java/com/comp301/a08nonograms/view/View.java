package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class View implements FXComponent {
  private final Controller controller;

  public View(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();
    // Button View
    ButtonView buttonView = new ButtonView(controller);
    layout.getChildren().add(buttonView.render());
    // Board View
    BoardView boardView = new BoardView(controller);
    layout.getChildren().add(boardView.render());
    if (controller.isSolved()) {
      Label winner = new Label("You solved it! Great job!");
      winner.setTextAlignment(TextAlignment.CENTER);
      winner.setFont(new Font("Cambria", 32));
      layout.getChildren().add(winner);
    }
    return layout;
  }
}
