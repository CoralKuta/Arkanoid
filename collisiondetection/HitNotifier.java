// ID: 208649186

package collisiondetection;

/**
 * @author Coral Kuta.
 * An interface for representation of notifiers from the Listener Pattern.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to the hit events.
     *
     * @param hl - the hit listener.
     */
    void addHitListener(HitListener hl);


    /**
     * Remove hl as a listener from the hit events.
     *
     * @param hl - the hit listener.
     */
    void removeHitListener(HitListener hl);

}
