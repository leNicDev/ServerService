package de.lenic.serverservice.spigot.concurrent;

/**
 * A {@link Promise} allows to return values in an async context.
 * Create a new Promise and call the supply method to return the value.
 * When the receive method has been called previously, the registered {@link Receiver} will receive this value.
 *
 * @param <T> The type of the returned value
 */
public class Promise<T> {

    private Receiver<T> receiver;


    public void receive(Receiver<T> receiver) {
        this.receiver = receiver;
    }

    public void supply(T value) {
        if (receiver != null)
            receiver.receive(value);
    }


    public interface Receiver<T> {
        void receive(T value);
    }

}
