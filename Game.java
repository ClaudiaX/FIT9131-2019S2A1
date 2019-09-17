
import java.util.Scanner;

/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.mainControl();
    }

    private Player computer;
    private Player player;
    private RNG rng;

    public Game() {
        computer = new Player();
        player = new Player();
        rng = new RNG();
    }

    public void displayMainMenu() {
        System.out.println("Welcome to 21 With Primes!");
        System.out.println("Please select from the following options:");
        System.out.println("Press 1 to register a player");
        System.out.println("Press 2 to start a new game");
        System.out.println("Press 3 to view a help menu");
        System.out.println("Press 4 to exit");
    }

    public void displayHelpMenu() {
        System.out.println("This game is 21 With Primes against the computer.");
        System.out.println("To play the game, each player, i.e. human and computer,is given a set of 5 tiles.");
        System.out.println("Each tile has an value and associated score. The following are scores awarded for different tiles in the game:");
        System.out.println("Tile Value of 1 --> Score of 5");
        System.out.println("Tile Value of 2 --> Score of 4");
        System.out.println("Tile Value of 3 --> Score of 3");
        System.out.println("Tile Value of 5 --> Score of 2");
        System.out.println("Tile Value of 7 --> Score of 1");
        System.out.println("No other tile value can be played other than the above pimes! Each player can use each of their 5 tiles only ONCE in each round!");
        System.out.println("For each round, each player will play ONE tile, with the tile value value adding to the game total for that round.");
        System.out.println("Provided game total is less than or equal to 21, the player will get the score for using that tile. Otherwise, no score will be allocated!");
        System.out.println("Once any player makes the game total equal to or firstly pass the 21, this round ends here and process the following deductions.");
        System.out.println("Any player who has NOT used the tile with the value of 5, will get a penalty of -3 scores!");
        System.out.println("The player who gained the highest scores when the game total stoped, will get 5 score!");
        System.out.println("After all deductions and awards, the player with the highest scores will be the winner of this round!");
        System.out.println("After all rounds end, the player with the more rounds winner, will be the final WINNER of this game!");
    }

    public void mainControl() {
        boolean start = true;
        while (start) {
            this.displayMainMenu();
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                this.registerPlayer();
                break;
                case 2:
                this.startGame();
                break;
                case 3:
                this.displayHelpMenu();
                break;
                case 4:
                start = false;
                System.exit(0);
                break;
            }
        }
    }

    public void startGame() {
        if (this.player.getName().length() == 0) 
            System.out.println("Please register plear first!");
        else {
            //            set round number
            Scanner sc = new Scanner(System.in);
            int totalGameNumber = 0;
            do {
                System.out.println("Please enter the number of rounds you wish to play: (1-10)");
                totalGameNumber = sc.nextInt();
            } while (totalGameNumber < 1 || totalGameNumber > 10);
            //            initial player info
            player.setRoundsWon(0);
            computer.setRoundsWon(0);
            //            start round
            for (int i = 0; i < totalGameNumber; i++) 
                startRound();
            //            display winner
            System.out.println(String.format("Computer won %s rounds.", computer.getRoundsWon()));
            System.out.println(String.format("%s won %s rounds.", player.getName(), player.getRoundsWon()));
            if (computer.getRoundsWon() > player.getRoundsWon()) 
                System.out.println("The winner of the game is Computer!");
            else if (player.getRoundsWon() > computer.getRoundsWon()) 
                System.out.println(String.format("The winner of the game is %s!", player.getName()));
            else 
                System.out.println("This is a draw!");
        }
    }

    public void startRound() {
        //        set first player: 0-computer, 1-player
        int firstResult = this.decideFirst();
        //        initialize
        player.resetTiles();
        player.setScore(0);
        computer.resetTiles();
        computer.setScore(0);

        int totalTile = 0;
        int round = 1;
        while (totalTile < 21) {
            //            display tiles
            String playerTiles = " ";
            String computerTiles = " ";
            for (int i = 0; i < player.getTiles().length; i++) {
                playerTiles = playerTiles + player.getTiles()[i].getValue() + " ";
                computerTiles = computerTiles + computer.getTiles()[i].getValue() + " ";
            }
            System.out.println(String.format("Round %s both players have %s tails, %s: {%s} %s: {%s}", round, 6 - round, player.getName(), playerTiles, "Computer", computerTiles));
            //            select tile

            if ( firstResult == 0 ) { // computer first
                this.computerTurn();
                totalTile += computer.getLastTilePlayed().getValue();
                System.out.println(String.format("Computer plays tile %s. Game total is now %s. Computer player score is %s.", computer.getLastTilePlayed().getValue(), totalTile, computer.getScore()));
                if (totalTile >= 21) 
                    displayRoundWinner();
                else {
                    this.playerTurn();
                    totalTile += player.getLastTilePlayed().getValue();
                    System.out.println(String.format("%s plays tile %s. Game total is now %s. %s player score is %s.", player.getName(), player.getLastTilePlayed().getValue(), totalTile, player.getName(), player.getScore()));
                    if (totalTile >= 21) 
                        displayRoundWinner();
                }
            } else { // player first
                this.playerTurn();
                totalTile += player.getLastTilePlayed().getValue();
                System.out.println(String.format("%s plays tile %s. Game total is now %s. %s player score is %s.", player.getName(), player.getLastTilePlayed().getValue(), totalTile, player.getName(), player.getScore()));
                if (totalTile >= 21) 
                    displayRoundWinner();
                else {
                    this.computerTurn();
                    totalTile += computer.getLastTilePlayed().getValue();
                    System.out.println(String.format("Computer plays tile %s. Game total is now %s. Computer player score is %s.", computer.getLastTilePlayed().getValue(), totalTile, computer.getScore()));
                    if (totalTile >= 21) 
                        displayRoundWinner();
                }
            }
            round++;
        }
    }

    public void registerPlayer() {
        String inputName = "";
        Scanner sc = new Scanner(System.in);
        do 
        {
            System.out.print("Please enter your name (name should be between 3 and 10 characters): ");
            inputName = sc.nextLine().trim();
        } 
        while (inputName.length() < 3 || inputName.length() > 10);
        System.out.println(String.format("Welcome to this game, %s!", inputName));
        player.setName(inputName);
        computer.setName("Computer");
    }

    public int decideFirst() {
        this.rng.setMinimunValus(0);
        this.rng.setMaximumValue(1);
        int firstResult = rng.generateRandom();
        return firstResult;
    }

    public void computerTurn() {
        rng.setMinimunValus(0);
        rng.setMaximumValue(computer.getTiles().length-1);
        int index = rng.generateRandom();
        computer.setLastTilePlayed(computer.getTiles()[index]);
        computer.addScore(computer.getLastTilePlayed().getScore());
        computer.removeTile(computer.getLastTilePlayed());
    }

    public void playerTurn() {
        Scanner sc = new Scanner(System.in);
        int value = 0;
        player.resetLastTile();
        do {
            System.out.print("Please select tile value from tiles: ");
            value = sc.nextInt();
            for (int i = 0; i < player.getTiles().length; i++) 
                if (player.getTiles()[i].getValue() == value) 
                    player.setLastTilePlayed(player.getTiles()[i]);
        } while (player.getLastTilePlayed().getValue() == 0 );
        player.addScore(player.getLastTilePlayed().getScore());
        player.removeTile(player.getLastTilePlayed());
    }

    public void displayRoundWinner() {
        System.out.println("Round Over");
        this.checkFive();
        if (computer.getScore() > player.getScore()) {
            computer.wonRound();
            computer.addScore(5);
        } else if (player.getScore() > computer.getScore()) {
            player.wonRound();
            player.addScore(5);
        }
        System.out.println("Final Score:");
        System.out.println(String.format("Computer is %s.", computer.getScore()));
        System.out.println(String.format("%s is %s.", player.getName(), player.getScore()));
    }

    public void checkFive() {
        for (int i = 0; i < player.getTiles().length; i++) 
            if (player.getTiles()[i].getValue() == 5) {
                player.addScore(-3);
                System.out.println(String.format("%s hasn't used the tile with the value of 5, gets a penalty of -3.", player.getName()));
            }
        for (int i = 0; i < computer.getTiles().length; i++)
            if (computer.getTiles()[i].getValue() == 5) {
                computer.addScore(-3);
                System.out.println("Computer hasn't used the tile with the value of 5, gets a penalty of -3.");
            }
    }

}