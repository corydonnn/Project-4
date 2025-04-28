import java.util.*;

public class Move {

  //move data
  ArrayList<String> names = new ArrayList<String>(Arrays.asList("Bash", "Fireball", "Water Spell", "Earthquake", "Draco Meteor"));
  String[] types = {"singleAttack", "defenseReduction", "attackReduction", "singleAttack", "singleAttack"};
  int[] scaleFactors = {10, 3, 5, 20, 50}; //defined as scale factor instead of damage so we don't need a seperate one for skills
  
  //make extra sure to keep the stats in order or it will explode

  
  //move variables
  public String name;
  public int damage;
  private String type;  // private 


  //move constructors
  public Move(){
    this.name = "Sit";
    this.damage = 0;
    this.type = "singleAttack";
  }
  
  public Move(String name) {
    this.name = name;
    this.damage = getScaleFactor(name);
    this.type = getType(name);
  }
  
  public int getScaleFactor(String name) {
    return scaleFactors[names.indexOf(name)]; //finds the scale factor with the same index of the inputted name
  }

  public String getType(String name) {
    return types[names.indexOf(name)]; //finds the scale factor with the same index of the inputted name
  }

  public String getName() {
    return this.name;
  }

  


  public void Attack(Character attacker, Move move, Character target) {
    if (move.getType(name).equals("singleAttack")) {
        int defenseroll = (int) (Math.random() * target.getDefense());
        int netDamage = Math.max(0, move.getScaleFactor(name) * attacker.getLevel() - defenseroll); // Prevent negative damage
        
        System.out.println(target.getName() + " was hit with " + move.getName() + 
                           " for " + netDamage + " damage (Defense roll: " + defenseroll + ")!");
        
        //target.setHealth(target.getHealth() - netDamage); // assuming setHealth & getHealth exist *we already have a takedamage function smiles
        target.takeDamage(netDamage);
    }
    else if(move.type.equals("defenseReduction")){
      System.out.println(target.getName() + " was hit with " + move.getName() + " and had its DEF permenently reduced by  " + move.getScaleFactor(name) * attacker.getLevel() + "!");
      target.takeDEFRed(move.getScaleFactor(name) * attacker.getLevel());
    }
    else if (move.type.equals("attackReduction")){
      System.out.println(target.getName() + " was hit with " + move.getName() + " and had its ATK permenently reduced by  " + move.getScaleFactor(name) * attacker.getLevel() + "!");
      target.takeATKRed(move.getScaleFactor(name) * attacker.getLevel());
    }
    else {
      System.out.println("Invalid action!");
    }
  }

  @Override
  public String toString() {
    return name + " - damage scale: " + damage + " - type: " + type;
  }

}