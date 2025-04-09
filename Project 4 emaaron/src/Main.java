import java.util.*;

public class Main {
  public static void main(String[] args) {
    // create scanner object
    Scanner sc = new Scanner(System.in);

    // create moves, then characters, then party for player
    ArrayList<Move> m1 = new ArrayList<Move>();
    m1.add(new Move("Be annoying", 10));
    m1.add(new Move("Do nothing", 20));
    ArrayList<Move> m2 = new ArrayList<Move>();
    m2.add(new Move("Pointless interjection", 10));
    m2.add(new Move("Take credit for your idea", 20));
    Character c1 = new Character("Karl", 100, m1);
    Character c2 = new Character("Katrina", 100, m2);
    Party playerParty = new Party();
    playerParty.addCharacter(c1);
    playerParty.addCharacter(c2);

    // create moves, then characters, then party for opponent
    Character c3 = new Character("JTodd", 100, new ArrayList<Move>(m1));
    Character c4 = new Character("That Morganton Dude", 100, new ArrayList<Move>(m2));
    Party opponentParty = new Party();
    opponentParty.addCharacter(c3);
    opponentParty.addCharacter(c4);

    // while parties are still alive
    while (!playerParty.isPartyDefeated() && !opponentParty.isPartyDefeated()) {
      // choose player character
      playerParty.choosePartyDisplay();
      int respC = Integer.parseInt(sc.nextLine());
      Character c = playerParty.selectCharacter(respC);
      System.out.println("\033[2J\033[H"); // clears the output
      // choose player move
      c.chooseMoveDisplay();
      int respM = Integer.parseInt(sc.nextLine());
      Move m = c.selectMove(respM);
      System.out.println("\033[2J\033[H"); // clears the output
      // choose target opponent character
      opponentParty.choosePartyDisplay();
      int respO = Integer.parseInt(sc.nextLine());
      Character oppTarget = opponentParty.selectCharacter(respO);
      System.out.println("\033[2J\033[H"); // clears the output
      // player performs move on opponent
      c.performMove(m, oppTarget);
      System.out.println(oppTarget);
      // randomly choose an opponent
      int randNum = (int) (Math.random() * opponentParty.getNumCharacters());
      Character opp = opponentParty.selectCharacter(randNum);
      // choose random move
      randNum = (int) (Math.random() * opp.getNumMoves());
      Move oppMove = opp.selectMove(randNum);
      // randomly choose character to attack
      randNum = (int) (Math.random() * playerParty.getNumCharacters());
      Character playerTarget = playerParty.selectCharacter(randNum);
      // opponent performs move on player
      opp.performMove(oppMove, playerTarget);
      System.out.println(c);
    }
    sc.close();
  }
}