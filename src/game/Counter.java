// ID: 208649186

package game;

/**
 * @author Coral Kuta.
 * A class for representation of a Counter that count things such as score, gamelevels, balls...
 * A Counter can increase and decrease it's values, and return it's current value.
 */
public class Counter {
    //Fields
    private int value;

    /**
     * Constructor.
     *
     * @param number - the starting number of the counter.
     */
    public Counter(int number) {
        this.value = number;
    }


    /**
     * Constructor for counter starting from 0.
     */
    public Counter() {
        this.value = 0;
    }



    /**
     * Add number to current count.
     *
     * @param number - the number added.
     */
    public void increase(int number) {
        setValue(this.getValue() + number);
    }


    /**
     * Subtract number from current count.
     *
     * @param number - the number subtracted.
     */
    public void decrease(int number) {
        setValue(this.getValue() - number);
    }


    /**
     * Getter.
     *
     * @return the current count.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Setter.
     *
     * @param newVal - the new value to set.
     */
    private void setValue(int newVal) {
        this.value = newVal;
    }

}
