
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player {

    private String name;
    private int score;
    private Tile[] tiles;
    private Tile lastTilePlayed;
    private int roundsWon;

    public Player() {
        this.name = "";
        this.score = 0;
        this.tiles = new Tile[]{new Tile(1, 5), new Tile(2, 4), new Tile(3, 3), new Tile(5, 2), new Tile(7, 1)};
        this.lastTilePlayed = new Tile();
        this.roundsWon = 0;
    }

    public Player(String name, int score, Tile[] tiles, Tile lastTilePlayed, int roundsWon) {
        this.name = name;
        this.score = score;
        this.tiles = tiles;
        this.lastTilePlayed = lastTilePlayed;
        this.roundsWon = roundsWon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }

    public Tile getLastTilePlayed() {
        return lastTilePlayed;
    }

    public void setLastTilePlayed(Tile lastTilePlayed) {
        this.lastTilePlayed = lastTilePlayed;
    }
    
    public void resetLastTile(){
        this.lastTilePlayed = new Tile();
    }

    public int getRoundsWon() {
        return roundsWon;
    }

    public void setRoundsWon(int roundsWon) {
        this.roundsWon = roundsWon;
    }

    public void addScore(int addScore) {
        this.score += addScore;
    }

    public void removeTile(Tile tile) {
        Tile[] temp = new Tile[this.tiles.length - 1];
        for (int i = 0, j = 0; i < this.tiles.length; i++) {
            if (tiles[i].getValue() == tile.getValue()) 
                continue;
            temp[j++] = tiles[i];
        }
        this.tiles = temp;
        //        reference: https://www.geeksforgeeks.org/remove-an-element-at-specific-index-from-an-array-in-java/
    }

    public void wonRound() {
        this.roundsWon += 1;
    }
    
    
    public void resetTiles(){
        this.tiles = new Tile[]{new Tile(1, 5), new Tile(2, 4), new Tile(3, 3), new Tile(5, 2), new Tile(7, 1)};
    }
}