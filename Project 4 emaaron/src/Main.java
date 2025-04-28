import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
  
    //debug code
    //initiazliation for move data
    Move bash = new Move("Bash");
    Move fireball = new Move("Fireball");
    Move waterspell = new Move("Water Spell");
    Move earthquake = new Move("Earthquake");
    Character testCookie = new Character();
    // Character evilCookie = new Character("evil", 50, 20, 20, 2, "rare", "glass cannon", bash, fireball, waterspell, earthquake);
    testCookie.displayMoveInfo(0);
    // evilCookie.displayMoveInfo(1);



  }
}