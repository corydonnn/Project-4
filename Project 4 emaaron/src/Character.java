import java.util.ArrayList;

public class Character {
  private String name;
  private int health;
  private ArrayList<Move> moves;

  public Character(String name, int health) {
    this.name = name;
    this.health = health;
    this.moves = new ArrayList<>();
  }

  public Character(String name, int health, ArrayList<Move> moves) {
    this.name = name;
    this.health = health;
    this.moves = moves;
  }

  public String getName() {
    return name;
  }

  public int getHealth() {
    return health;
  }

  public ArrayList<Move> getMoves() {
    return moves;
  }

  public int getNumMoves() {
    return moves.size();
  }

  public void addMove(Move move) {
    moves.add(move);
  }

  public void takeDamage(int damage) {
    this.health -= damage;
    if (this.health < 0) {
      this.health = 0;
    }
  }

  public boolean isAlive() {
    return this.health > 0;
  }

  @Override
  public String toString() {
    return name + " - Health: " + health;
  }

  public void displayMoveInfo() {
    for (Move move : moves) {
      System.out.println(move);
    }
  }

  public void chooseMoveDisplay() {
    System.out.println("Choose a move: ");
    for (int i = 0; i < moves.size(); i++) {
      System.out.println("\t" + i + ": " + moves.get(i).toString());
    }
  }

  public Move selectMove(int i) {
    return moves.get(i);
  }

  public void performMove(Move move, Character target) {
    move.execute(target);
  }

}