package com.comp301.a08nonograms.model;

public class CluesImpl implements Clues {
  private int[][] rowClues;
  private int[][] colClues;

  public CluesImpl(int[][] rowClues, int[][] colClues) {
    this.rowClues = rowClues;
    this.colClues = colClues;
    if (this.rowClues == null) {
      throw new IllegalArgumentException("row clues are empty");
    }
    if (this.colClues == null) {
      throw new IllegalArgumentException("column clues are empty");
    }
  }

  @Override
  public int getWidth() {
    return this.colClues.length;
  }

  @Override
  public int getHeight() {
    return this.rowClues.length;
  }

  @Override
  public int[] getRowClues(int index) {
    return rowClues[index];
  }

  @Override
  public int[] getColClues(int index) {
    return colClues[index];
  }

  @Override
  public int getRowCluesLength() {
    return rowClues[0].length;
  }

  @Override
  public int getColCluesLength() {
    return colClues[0].length;
  }
}
