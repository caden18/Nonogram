package com.comp301.a08nonograms.model;

import com.comp301.a08nonograms.PuzzleLibrary;
import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private int activePuzzle;
  private List<Clues> clues;
  private Board board;
  private List<ModelObserver> observers;

  public ModelImpl(List<Clues> clues) {
    this.activePuzzle = 0;
    this.clues = clues;
    this.board =
        new BoardImpl(clues.get(activePuzzle).getWidth(), clues.get(activePuzzle).getHeight());
    this.observers = new ArrayList<>();
    if (clues == null) {
      throw new IllegalArgumentException("Clues is empty");
    }
  }

  @Override
  public int getPuzzleCount() {
    return clues.size();
  }

  @Override
  public int getPuzzleIndex() {
    return activePuzzle;
  }

  @Override
  public void setPuzzleIndex(int index) {
    if (index < 0 || index >= clues.size()) {
      throw new IllegalArgumentException("Invalid index");
    }
    this.activePuzzle = index;
    this.board =
        new BoardImpl(clues.get(activePuzzle).getWidth(), clues.get(activePuzzle).getHeight());
    notifyObservers();
  }

  @Override
  public void addObserver(ModelObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException("Empty observer");
    }
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException("Empty observer");
    }
    observers.remove(observer);
  }

  @Override
  public boolean isSolved() {
    int[][] row_clues =
        new int[clues.get(activePuzzle).getHeight()][clues.get(activePuzzle).getRowCluesLength()];
    int[][] col_clues =
        new int[clues.get(activePuzzle).getWidth()][clues.get(activePuzzle).getColCluesLength()];
    // Rows
    for (int i = 0; i < row_clues.length; i++) {
      int consecutive_shades = 0;
      int loadIn = 0;
      for (int j = 0; j < clues.get(activePuzzle).getWidth(); j++) {
        if (isShaded(i, j)) {
          consecutive_shades++;
        } else {
          if (consecutive_shades != 0) {
            row_clues[i][loadIn] = consecutive_shades;
            consecutive_shades = 0;
            loadIn++;
          }
        }
        if (consecutive_shades != 0 && j == clues.get(activePuzzle).getWidth() - 1) {
          row_clues[i][loadIn] = consecutive_shades;
          consecutive_shades = 0;
          loadIn++;
        }
      }
      int fill = clues.get(activePuzzle).getRowCluesLength() - loadIn;
      if (loadIn < clues.get(activePuzzle).getRowCluesLength()) {
        for (int h = 0; h < fill; h++) {
          for (int m = clues.get(activePuzzle).getRowCluesLength() - 2; m >= 0; m--) {
            row_clues[i][m + 1] = row_clues[i][m];
          }
        }
        for (int k = 0; k < fill; k++) {
          row_clues[i][k] = 0;
        }
      }
    }
    // Columns
    for (int i = 0; i < col_clues.length; i++) {
      int consecutive_shades = 0;
      int loadIn = 0;
      for (int j = 0; j < clues.get(activePuzzle).getHeight(); j++) {
        if (isShaded(j, i)) {
          consecutive_shades++;
        } else {
          if (consecutive_shades != 0) {
            col_clues[i][loadIn] = consecutive_shades;
            consecutive_shades = 0;
            loadIn++;
          }
        }
        if (consecutive_shades != 0 && j == clues.get(activePuzzle).getHeight() - 1) {
          col_clues[i][loadIn] = consecutive_shades;
          consecutive_shades = 0;
          loadIn++;
        }
      }
      int fill = clues.get(activePuzzle).getColCluesLength() - loadIn;
      if (loadIn < clues.get(activePuzzle).getColCluesLength()) {
        for (int h = 0; h < fill; h++) {
          for (int m = clues.get(activePuzzle).getColCluesLength() - 2; m >= 0; m--) {
            col_clues[i][m + 1] = col_clues[i][m];
          }
        }
        for (int k = 0; k < fill; k++) {
          col_clues[i][k] = 0;
        }
      }
    }
    for (int i = 0; i < row_clues.length; i++) {
      for (int j = 0; j < row_clues[i].length; j++) {
        if (row_clues[i][j] != clues.get(activePuzzle).getRowClues(i)[j]) {
          return false;
        }
      }
    }
    for (int i = 0; i < col_clues.length; i++) {
      for (int j = 0; j < col_clues[i].length; j++) {
        if (col_clues[i][j] != clues.get(activePuzzle).getColClues(i)[j]) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public boolean isShaded(int row, int col) {
    if (row < 0
        || col < 0
        || row > clues.get(activePuzzle).getHeight()
        || col > clues.get(activePuzzle).getWidth()) {
      throw new RuntimeException("row and column coordinate is out of bounds");
    }
    return board.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    if (row < 0
        || col < 0
        || row > clues.get(activePuzzle).getHeight()
        || col > clues.get(activePuzzle).getWidth()) {
      throw new RuntimeException("row and column coordinate is out of bounds");
    }
    return board.isEliminated(row, col);
  }

  @Override
  public boolean isSpace(int row, int col) {
    if (row < 0
        || col < 0
        || row > clues.get(activePuzzle).getHeight()
        || col > clues.get(activePuzzle).getWidth()) {
      throw new RuntimeException("row and column coordinate is out of bounds");
    }
    return board.isSpace(row, col);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    if (row < 0
        || col < 0
        || row > clues.get(activePuzzle).getHeight()
        || col > clues.get(activePuzzle).getWidth()) {
      throw new RuntimeException("row and column coordinate is out of bounds");
    }
    board.toggleCellShaded(row, col);
    notifyObservers();
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    if (row < 0
        || col < 0
        || row > clues.get(activePuzzle).getHeight()
        || col > clues.get(activePuzzle).getWidth()) {
      throw new RuntimeException("row and column coordinate is out of bounds");
    }
    board.toggleCellEliminated(row, col);
    notifyObservers();
  }

  @Override
  public void clear() {
    board.clear();
    notifyObservers();
  }

  @Override
  public int getWidth() {
    return clues.get(activePuzzle).getWidth();
  }

  @Override
  public int getHeight() {
    return clues.get(activePuzzle).getHeight();
  }

  @Override
  public int[] getRowClues(int index) {
    return clues.get(activePuzzle).getRowClues(index);
  }

  @Override
  public int[] getColClues(int index) {
    return clues.get(activePuzzle).getColClues(index);
  }

  @Override
  public int getRowCluesLength() {
    return clues.get(activePuzzle).getRowCluesLength();
  }

  @Override
  public int getColCluesLength() {
    return clues.get(activePuzzle).getColCluesLength();
  }

  private void notifyObservers() {
    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }
}
