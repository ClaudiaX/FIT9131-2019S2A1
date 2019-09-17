
/**
 * Write a description of class RNG here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RNG {
    private int maximumValue;
    private int minimumValus;

    public RNG() {
        this.maximumValue = 0;
        this.minimumValus = 0;
    }

    public RNG(int maximumValue, int minimunValus) {
        this.maximumValue = maximumValue;
        this.minimumValus = minimunValus;
    }

    public int getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(int maximumValue) {
        this.maximumValue = maximumValue;
    }

    public int getMinimunValus() {
        return minimumValus;
    }

    public void setMinimunValus(int minimunValus) {
        this.minimumValus = minimunValus;
    }

    public int generateRandom() {
        int random = (int) (Math.random() * (maximumValue - minimumValus + 1) + minimumValus);
        return random;
    }
}
