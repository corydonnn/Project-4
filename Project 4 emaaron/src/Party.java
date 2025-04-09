import java.util.ArrayList;

public class Party {
  private ArrayList<Character> characters;

  public Party() {
    characters = new ArrayList<Character>();
  }

  public Party(ArrayList<Character> characters) {
    this.characters = characters;
  }

  public ArrayList<Character> getParty() {
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

  public boolean isPartyDefeated() {
    for (Character character : characters) {
      if (character.isAlive()) {
        return false;
      }
    }
    return true;
  }

  public void displayPartyInfo() {
    for (Character character : characters) {
      System.out.println(character);
    }
  }

  public void choosePartyDisplay() {
    System.out.println("Choose a player: ");
    for (int i = 0; i < characters.size(); i++) {
      System.out.println("\t" + i + ": " + characters.get(i).getName());
    }
  }

  public Character selectCharacter(int i) {
    return characters.get(i);
  }

  @Override
  public String toString() {
    return characters.toString();
  }

}