import java.util.ArrayList;

public class Character {


  //stat initialization

  private String name;
  //meta stats
  public int level;
  private String rarity;
  private String role;

  //combat stats
  public int health;
  public int defense;
  private int attack;



  //move storage (i don't know if we wanted to code an autobattler but for now i'm just gonna do the 4 move turn based rpg thing)
  public Move move1;
  public Move move2;
  public Move move3;
  public Move move4;
  public ArrayList<Move> moves = new ArrayList<Move>();

  //placeholder for valueless
  public Character() {
    this.name = "Unfrosted Cookie";
    this.health = 100;
    this.attack = 10;
    this.defense = 10;
    this.level = 1;
    this.rarity = "Common";
    this.role = "DPS";
    this.move1 = new Move();
    moves.add(move1);
  }

  //setter
  public Character(String name, int health, int defense, int attack, int level, String rarity, String role, Move move1, Move move2, Move move3, Move move4){
    this.name = name;
    this.health = health;
    this.attack = attack;
    this.defense = defense;
    this.level = level;
    this.rarity = rarity;
    this.role = role;
    this.move1 = move1;
    this.move2 = move2;
    this.move3 = move3;
    this.move4 = move4;
    this.moves.add(move1);
    this.moves.add(move2);
    this.moves.add(move3);
    this.moves.add(move4);
  }

  public String getName() {
    return this.name;
  }

  public int getHealth() {
    return this.health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public int getDefense() {
    return this.defense;
  }

  public int getAttack() {
    return this.attack;
  }

  public void getMoves() {
    System.out.println("0. " + moves.get(0).getName() + "\n1. " + moves.get(1).getName() + "\n2. " + moves.get(2).getName() + "\n3. " + moves.get(3).getName());
  }

  public int getLevel() { //gets character level
    return this.level;
  }
  

  // public int getNumMoves() {
  //   return moves.size();
  // }

  // public void addMove(String move) {
  //   moves.add(move);
  // }

  public void takeDamage(int damage) {
    this.health -= damage;
    if (this.health < 0) {
      this.health = 0;
    }
  }

  public void takeATKRed(int atkred) {
    this.attack -= atkred;
    if (this.attack < 5) {
      this.attack = 5;
    }
  }

  public void takeDEFRed(int defred) {
    this.defense -= defred;
    if (this.defense < 0) {
      this.defense = 0;
    }
  }

  public boolean isAlive() {
    return this.health > 0;
  }

  // @Override
  // public String toString() {
  //   return name + " - Health: " + health;
  // }

  public void displayMoveInfo(int movenum) { //displays info for all moves? going to change to singular to get the code working
    if (movenum < 0 || movenum > 3){
      System.out.println("Invalid Input");
    }
    else {
      System.out.println(moves.get(movenum).name);
    }
  }

  

  // public void chooseMoveDisplay() {
  //   System.out.println("Choose a move: ");
  //   for (int i = 0; i < moves.size(); i++) {
  //     System.out.println("\t" + i + ": " + moves.get(i).toString());
  //   }
  // }

  public Move selectMove(int i) {
    return moves.get(i);
  }

  public void performMove(Move move, Character target) {
    move.Attack(this, move, target);
  }

  public void getCharterStatistics(Character cookie) {
    System.out.println("Name: " + cookie.getName());
    System.out.println("Health: " + cookie.getHealth());
    System.out.println("Attack: " + cookie.getAttack());
  }
}