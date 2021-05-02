package view;

public enum GridPosition {
  ID(0, "ID"), TITLE(1, "Title"), DESCRIPTION(2, "Desc"), DATE(3, "Date"), STATE(4, "State");

  int index;
  String name;

  private GridPosition(int index, String name) {
    this.index = index;
    this.name = name;

  }

}
