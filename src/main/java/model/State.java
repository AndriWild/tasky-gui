package model;

public enum State {
  TODO("#00ffff"), DOING("#7fffd4"), DONE("#f0ffff"), REVIEW("#f5f5dc");

  private String color;

  private State(String color){
  this.color = color;
  }

  public String color(){
    return color;
  }

}
