
/**
 * Write a description of class Tile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tile {
    private int score;
    private int value;

    public Tile() {
        this.score = 0;
        this.value = 0;
    }

    public Tile(int value, int score) {
        this.score = score;
        this.value = value;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
