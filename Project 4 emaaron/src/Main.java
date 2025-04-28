import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
  
    //batch initialization
    Batch playerBatch = new Batch();
    Batch enemyBatch = new Batch();

    //initiazliation for move data
    Move bash = new Move("Bash");
    Move fireball = new Move("Fireball");
    Move waterspell = new Move("Water Spell");
    Move earthquake = new Move("Earthquake");
    Move dracoMeteor = new Move("Draco Meteor");
    Move sit = new Move("Sit");

    //character data initilization
    Character unfrostedCookie = new Character("Unfrosted Cookie", 50, 20, 20, 2, "rare", "generalist", bash, sit, sit, sit);
    Character evilCookie = new Character("Evil Cookie", 1, 20, 50, 2, "rare", "glass cannon", bash, sit, sit, sit);
    Character pyroCookie = new Character("Scorched Cookie", 50, 20, 20, 2, "rare", "debuffer", fireball, sit, sit, sit);
    Character dragonCookie = new Character("Dragon Emperor Cookie", 50, 20, 20, 2, "rare", "glass cannon", dracoMeteor, sit, sit, sit);
    Character oversaltedCookie = new Character("Oversalted Cookie", 100, 30, 00, 2, "rare", "tank", sit, sit, sit, sit);

    Character[] characters = {unfrostedCookie, evilCookie, pyroCookie, dragonCookie, oversaltedCookie}; //character array so that we can call it for strings later (evil)

    //actual real code
    
    //start of game loop is here

    //Initializing the game setting up the player’s party.
    int[] choices = {0,0,0};
    System.out.println("Your Party is empty. Please select to add character to your party. (1-5)");
    for (int i = 0; i<3; i++){
      System.out.println("1. Unfrosted Cookie \n2. Evil Cookie \n3. Scorched Cookie \n4. Dragon Emperor Cookie \n5. Oversalted Cookie");
      int choice = sc.nextInt();
      //ensures that 1-5 and not already used
      while (choice > 5 || choice <= 0 || choice==choices[0] || choice==choices[1] || choice==choices[2]) {
        if (choice > 5 || choice <= 0){
          System.out.println("Invalid choice");
        }
        else if (choice==choices[0] || choice==choices[1] || choice==choices[2]) {
          System.out.println("Cookie already used");
        }
        choice = sc.nextInt();
      }

      choices[i] = choice-1;
      System.out.println("You have added " + characters[choice-1].getName() + " to your party.");
      playerBatch.addCharacter(characters[choice-1]); //adds the character to the player batch
    }
    System.out.println("Your party is full. You have selected: ");
    for (int i = 0; i<3; i++){
      System.out.println(characters[choices[i]].getName());
    }

    //randomizing enemy selection
    System.out.println("Randomizing enemy selection...");
    System.out.println("Enemies selected! \nYou will be fighting: ");
    for (int i = 0; i<3; i++){
      int enemynum = (int) (Math.random() * 5);
      System.out.println("Enemy selected: " + characters[enemynum].getName());
      enemyBatch.addCharacter(characters[enemynum]); //adds the character to the enemy batch
    }


    System.out.println("Starting battle... (Type 100 to access Menu before your round)");
    //combat loop
    while (playerBatch.isBatchDefeated() == false && enemyBatch.isBatchDefeated() == false) { //while both batches are not defeated

      //player turn
      playerBatch.choosePartyDisplay(); //text included in function
      int playerChoice = sc.nextInt();
      //catches out of bounds and Menu
      while (playerChoice >= playerBatch.getNumCharacters() || playerChoice < 0) {
        if (playerChoice == 100) {
          System.out.println("200: Your Cookies, 300: Enemy Cookies");
          playerChoice = sc.nextInt();

          //displays all the moves and character statistics for each character
          if (playerChoice == 200){
            for (Character a: playerBatch.getBatch()){
              a.getCharterStatistics(a);
              a.getMoves();
            }
          }
          if (playerChoice == 300){
            for (Character a: enemyBatch.getBatch()) {
              a.getCharterStatistics(a);
              a.getMoves();
            }
          }
          else {
            System.out.println("invalid choice, try again");
          }
        }
        else {
          System.out.println("invalid choice, try again");
        }
        playerChoice = sc.nextInt();
      }

      System.out.println("You have selected " + playerBatch.getBatch().get(playerChoice).getName() + " to attack with.");
      System.out.println("Please select a move: ");
      playerBatch.getBatch().get(playerChoice).getMoves(); //text included in function
      int moveChoice = sc.nextInt();

      //catches any out of bounds and asks for another
      while (moveChoice >= 4 || moveChoice < 0) {
        System.out.println("invalid choice, try again");
        moveChoice = sc.nextInt();
      }

      System.out.println("You have selected " + playerBatch.getBatch().get(playerChoice).moves.get(moveChoice).getName() + " to attack with.");
      System.out.println("Please select a target: ");
      enemyBatch.choosePartyDisplay(); //text included in function
      int targetChoice = sc.nextInt();

      //catches any out of bounds and requests another
      while (targetChoice >= enemyBatch.getNumCharacters() || targetChoice < 0) {
        System.out.println("invalid choice, try again");
        targetChoice = sc.nextInt();
      }

      System.out.println("You have selected " + enemyBatch.getBatch().get(targetChoice).getName() + " to attack.");
      playerBatch.selectCharacterAttackCharacter(playerChoice, playerBatch.getBatch().get(playerChoice).moves.get(moveChoice).getName(), enemyBatch.getBatch().get(targetChoice)); //calls the attack function for the player batch

      //after move is completed, either output death or enemy health
      if (!enemyBatch.getBatch().get(targetChoice).isAlive()) {
        System.out.println(enemyBatch.getBatch().get(targetChoice).getName() + " has died.");
        enemyBatch.removeCharacter(enemyBatch.getBatch().get(targetChoice));
      }
      else {
        System.out.println(enemyBatch.getBatch().get(targetChoice).getName() + " has " + enemyBatch.getBatch().get(targetChoice).getHealth());
      }
      
      
      
      //assuming game is not over 
      if (playerBatch.isBatchDefeated() == false && enemyBatch.isBatchDefeated() == false) {
        //enemy turn
        System.out.println("Enemy turn!");
        int randomEnemyChoice = (int) (Math.random() * enemyBatch.getNumCharacters()); //randomly selects an enemy to attack with
        System.out.println("Enemy " + enemyBatch.getBatch().get(randomEnemyChoice).getName() + " is attacking!");
        moveChoice = (int) (Math.random() * enemyBatch.getBatch().get(randomEnemyChoice).moves.size()); //randomly selects a move for the enemy to use
        System.out.println("Chosen move: " + enemyBatch.getBatch().get(randomEnemyChoice).moves.get(moveChoice).getName());
        System.out.println("Choosing target...");
        int enemyTargetChoice = (int) (Math.random() * playerBatch.getNumCharacters()); //randomly selects a target for the enemy to attack based on the player batch size
        System.out.println("Target chosen: " + playerBatch.getBatch().get(enemyTargetChoice).getName());
        //status update on move hit
        
        
        //after move is completed, either output death or friend health
        if (!playerBatch.getBatch().get(enemyTargetChoice).isAlive()) {
          System.out.println(playerBatch.getBatch().get(enemyTargetChoice).getName() + " has died.");
          playerBatch.removeCharacter(playerBatch.getBatch().get(enemyTargetChoice));
        }
        else {
          System.out.println(playerBatch.getBatch().get(enemyTargetChoice).getName() + " has " + playerBatch.getBatch().get(enemyTargetChoice).getHealth());
        }
        
      }
      //end of game state: win or lose
      if (playerBatch.isBatchDefeated() == true) {
        System.out.println("You have lost the game! Better luck next time");
      }
      else if (enemyBatch.isBatchDefeated()==true) {
        System.out.println("You have won the game! Congraduulations!");
      }
    }








  }
}
