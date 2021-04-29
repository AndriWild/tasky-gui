package gui;

public enum ControlNames {
  ID(0, "ID"), TITLE(1, "Title"), DESCRIPTION(2, "Desc"), DATE(3, "Date"), STATE(4, "State");

  int index;
  String name;

  private ControlNames(int index, String name) {
    this.index = index;
    this.name = name;

  }

}
