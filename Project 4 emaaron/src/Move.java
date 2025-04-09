public class Move {
  private String name;
  private int damage;

  public Move(String name, int damage) {
    this.name = name;
    this.damage = damage;
  }

  public String getName() {
    return name;
  }

  public int getDamage() {
    return damage;
  }

  public void execute(Character target) {
    System.out.println(target.getName() + " was hit with " + name + " for " + damage + " damage!");
    target.takeDamage(damage);
  }

  @Override
  public String toString() {
    return name + " - Damage: " + damage;
  }
}