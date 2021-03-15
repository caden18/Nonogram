package com.comp301.a08nonograms.model;

public class BoardImpl implements Board {
  enum cellState {
    SHADED,
    ELIMINATED,
    SPACE
  }

  private cellState[][] board;

  public BoardImpl(int width, int height) {
    this.board = new cellState[height][width];
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = cellState.SPACE;
      }
    }
    if (width == 0) {
      throw new IllegalArgumentException("width cannot be 0");
    }
    if (height == 0) {
      throw new IllegalArgumentException("height cannot be 0");
    }
  }

  @Override
  public boolean isShaded(int row, int col) {
    if (board[row][col].equals(cellState.SHADED)) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isEliminated(int row, int col) {
    if (board[row][col].equals(cellState.ELIMINATED)) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isSpace(int row, int col) {
    if (board[row][col].equals(cellState.SPACE)) {
      return true;
    }
    return false;
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    if (board[row][col].equals(cellState.SHADED)) {
      board[row][col] = cellState.SPACE;
      return;
    }
    board[row][col] = cellState.SHADED;
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    if (board[row][col].equals(cellState.ELIMINATED)) {
      board[row][col] = cellState.SPACE;
      return;
    }
    board[row][col] = cellState.ELIMINATED;
  }

  @Override
  public void clear() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = cellState.SPACE;
      }
    }
  }
}
