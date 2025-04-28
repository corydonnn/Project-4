import java.util.ArrayList;

public class Batch {
  private ArrayList<Character> characters;

  //the batch is just an arraylist and needs one to be initialized
  public Batch() {
    characters = new ArrayList<Character>();
  }

  public Batch(ArrayList<Character> characters) {
    this.characters = characters;
  }


  public ArrayList<Character> getBatch() {
    return characters;
  }

  public void addCharacter(Character c) {
    characters.add(c);
  }

  public void removeCharacter(Character c) {
    characters.remove(c);
  }

  public int getNumCharacters() {
    return characters.size();
  }

//checks if each character is alive, if all are dead it is defeated
  public boolean isBatchDefeated() {
    for (Character character : characters) {
      if (character.isAlive()) {
        return false;
      }
    }
    return true;
  }

//display function
  public void displayBatchInfo() {
    for (Character character : characters) {
      System.out.println(character.toString());
    }
  }
  
//displays a full menu of every character in the party 
  public void choosePartyDisplay() {
    System.out.println("Choose a player: ");
    for (int i = 0; i < characters.size(); i++) {
      System.out.println("\t" + i + ": " + characters.get(i).getName());
    }
  }

//probably the only important part of the code when it comes to implementation:
//this code basically is the same |attack| command in the Moves bar except it allows you to
//select a character from your party based on their index then their move and then the character it is attacking
  public void selectCharacterAttackCharacter(int i, String movename, Character target) {
    Move moved = new Move(movename);
    (moved).Attack(characters.get(i), new Move(movename), target);
  }

  @Override
  public String toString() {
    return characters.toString();
  }

}